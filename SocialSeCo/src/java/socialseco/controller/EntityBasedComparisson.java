/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import socialseco.dao.MatrixDAO;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;
import socialseco.model.question.Question;
import socialseco.model.util.Matrix;

/**
 *
 * @author krle
 */
public class EntityBasedComparisson {
    
   
    List<String> userFields;
    List<String> questionFields;
    double [][] comparisonMatrix;
    int userFeatures;
    int questionFeatures;
    int users;
    Matrix m;

    public EntityBasedComparisson() {
      questionFields = new ArrayList<String>();
      userFields = new ArrayList<String>();
    }
    
    public void buildMatrix(Question q, String platform)
    {
        Long id = 0l;
        if(platform.equals(Comparator.facebookPlatform))
        {
            userFeatures = FacebookUser.fieldNum;
            id = 0l;
            
            userFields.add("Activities");
            userFields.add("Bio");
            userFields.add("Books");
            userFields.add("Education");
            userFields.add("Games");
            userFields.add("Groups");
            userFields.add("Hometown");
            userFields.add("Interests");
            userFields.add("Languages");
            userFields.add("Likes");
            userFields.add("Location");
            userFields.add("Movies");
            userFields.add("Music");
            userFields.add("Religion");
            userFields.add("Sports");
            userFields.add("Television");
            userFields.add("Work");
            
        }
        if(platform.equals(Comparator.linkedinPlatform))
        {
            userFeatures = LinkedinUser.fieldNum;
            id = 1l;
            
            //add linkedin user fields
            for(int i = 0;i<LinkedinUser.fieldNum;i++)
                userFields.add(LinkedinUser.fields[i]);
        }
        
        
        questionFeatures = q.getNumOfFields();
        comparisonMatrix = new double[userFeatures][questionFeatures];
        
        for(String name:q.getQuestionSchema().getKeys())
        {
            questionFields.add(name);
        }
        
        //take values from the database for question fields that exist
        MatrixDAO dao = new MatrixDAO();
        m = dao.readMatrixByIdWithoutTransaction(id);
        if(m==null)
        {
            m= new Matrix(userFeatures, questionFeatures);
            m.init(userFields,questionFields);
            users=0;
            init();
        }
        else
        {
            users=m.getUserNum();
            init(m);
        }
    }
    
    
    public Map<Long,Double> compare(Question q, List<FacebookUser> users)
    {
        buildMatrix(q,Comparator.facebookPlatform);
        learn(q,users);
        int [] maxmatch = findBestMatches();
        
        
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        for(FacebookUser u:users)
        {
            System.out.println("Instance Base Matching with user " + u.getName());
            for(int j = 0; j<questionFeatures;j++)
            {
                //Compare each questin feature only with  
                //best match in user features
                String user = u.flattenFeature(maxmatch[j]);
                user = Comparator.preprocess(user);
                
                String question = q.flattenFeature(j);
                question = Comparator.preprocess(question);
                
                //compare
                double value=0;
                if(user!=null && !user.isEmpty() && question!=null && !question.isEmpty() )
                {
                    value = MaxWordSimilarity.compareMinDistanceWords(question, user);
                }
                userEvals.put(u.getId(), value);


            }
        }
        
        return userEvals;
 
        
    }
    
    public Map<Long,Double> compare(Question q, ArrayList<LinkedinUser> users)
    {
        buildMatrix(q,Comparator.linkedinPlatform);
        learn(q,users);
        int [] maxmatch = findBestMatches();
        
        
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        for(LinkedinUser u:users)
        {
            System.out.println("Instance Base Matching with user " + u.getName());
            for(int j = 0; j<questionFeatures;j++)
            {
                //Compare each questin feature only with  
                //best match in user features
                String user = u.flattenField(LinkedinUser.fields[maxmatch[j]]);
                user = Comparator.preprocess(user);
                
                String question = q.flattenFeature(j);
                question = Comparator.preprocess(question);
                
                //compare
                double value=0;
                if(user!=null && !user.isEmpty() && question!=null && !question.isEmpty() )
                {
                    value = MaxWordSimilarity.compareMinDistanceWords(question, user);
                }
                userEvals.put(u.getId(), value);


            }
        }
        
        return userEvals;
 
        
    }
    
    
    
    
    private int[] findBestMatches()
    {
        int [] max = new int[questionFeatures];
        for(int j = 0;j<questionFeatures;j++)
        {
            max[j] = 0;
            double maxval=0;
            for(int i = 0;i<userFeatures;i++)
            {
                if(maxval<comparisonMatrix[i][j])
                {
                    maxval = comparisonMatrix[i][j];
                    max[j] = i;
                }
            }
        }
        return max;
    }
    
    
    ////////////////////////////////////////////////
    //      Learn
    /////////////////////////////////////////////////
    
    private void learn(Question q, List<FacebookUser> users)
    {
        for(FacebookUser u:users)
        {
            comapareLearn(q, u);
        }
    }
    
    private void learn(Question q, ArrayList<LinkedinUser> users)
    {
        for(LinkedinUser u:users)
        {
            comapareLearn(q, u);
        }
    }
    
    private void comapareLearn(Question q, FacebookUser u)
    {
        for(int i = 0;i<userFeatures;i++)
        {
            //flatten ith user feature
            if(i == 17){
                System.out.println("Aja!");
            }
            String user = u.flattenFeature(i);
            user = Comparator.preprocess(user);
            
            for(int j = 0; j<questionFeatures;j++)
            {
                //flatten jth question feature
                String question = q.flattenFeature(j);
                question = Comparator.preprocess(question);
                
                //compare
                double value=0;
                if(user!=null && !user.isEmpty() && question!=null && !question.isEmpty())
                {
                    value = MaxWordSimilarity.compareMinDistanceWords(question, user);
                    
                    //update matrix[i][j] with iterative mean
                    users++;
                    comparisonMatrix[i][j] = comparisonMatrix[i][j] + 1/users*(value-comparisonMatrix[i][j]);
                }
                
                
                
                
                
            }
        }
        
        
        //update matrix in DB
        updateDB();
        
    }
    
    
    private void comapareLearn(Question q, LinkedinUser u)
    {
        for(int i = 0;i<userFeatures;i++)
        {
            //flatten ith user feature
       
            String user = u.flattenField(LinkedinUser.fields[i]);
            user = Comparator.preprocess(user);
            
            for(int j = 0; j<questionFeatures;j++)
            {
                //flatten jth question feature
                String question = q.flattenFeature(j);
                question = Comparator.preprocess(question);
                
                //compare
                double value=0;
                if(user!=null && !user.isEmpty() && question!=null && !question.isEmpty())
                {
                    value = MaxWordSimilarity.compareMinDistanceWords(question, user);
                    
                    //update matrix[i][j] with iterative mean
                    users++;
                    comparisonMatrix[i][j] = comparisonMatrix[i][j] + 1/users*(value-comparisonMatrix[i][j]);
                }
                
                
                
                
                
            }
        }
        
        
        //update matrix in DB
        updateDB();
        
    }
    

    private void init() {
       for(int i = 0; i<userFeatures; i++)
         {
            
            for(int j = 0; j<questionFeatures; j++)
            {
                comparisonMatrix[i][j]=0.0;
            }
         }
    }

    private void init(Matrix m) 
    {
        int Z=m.getY();
        for(int i = 0; i<questionFeatures; i++)
         {
            int feature = -1;
            for(int j = 0; j<Z; j++)
            {
                 if(questionFields.get(i).equals(m.getQuestionFields()[j]))
                 {
                     feature = j;
                 }
                
            }
            
            for(int k = 0; k<userFeatures; k++)
            {
                if(feature!=-1)
                {
                    comparisonMatrix[k][i]=m.get(k, feature);
                }
                else
                {
                    comparisonMatrix[k][i]=0;
                }
            }
            
         }
    }

    private void updateDB() 
    {
        int Z = m.getY();
        for(int i = 0; i<questionFeatures; i++)
        {
            int feature = -1;
            for(int j = 0; j<Z; j++)
            {
                 if(questionFields.get(i).equals(m.getQuestionFields()[j]))
                 {
                     feature = j;
                 }
                
            }
            
            
            if(feature!=-1)
            {
                for(int k = 0; k<userFeatures; k++)
                {
                    m.set(k, feature, comparisonMatrix[k][i]);
                }
            }
            else
            {
                double [] vals = new double[userFeatures];
                for(int k = 0; k<userFeatures; k++)
                {
                    vals[k]=comparisonMatrix[k][i];
                }
                m.addQuestionFeature(questionFields.get(i),vals);
            }
            
            
         }
        
        m.setUserNum(users);
        MatrixDAO dao = new MatrixDAO();
        dao.persistWithoutTransaction(m);
        
    }
    
    
    
    
}
