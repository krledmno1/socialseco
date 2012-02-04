/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import com.cybozu.labs.langdetect.LangDetectException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import socialseco.dao.UserDAO;
import socialseco.model.facebook.*;
import socialseco.model.linkedin.LinkedinUser;
import socialseco.model.question.Instance;
import socialseco.model.question.Question;
import socialseco.model.question.Schema;


/**
 *
 * @author krle
 */
public class Comparator {
    
    public static String facebookPlatform = "facebook";
    public static String linkedinPlatform = "linkedin";
    
    public static String letterPairSimilarity = "letterPairSimilarity";
    public static String maxWordSimilarity = "MaxWordSimilarity";
    public static String instanceBasedMatching = "instanceBasedMatching";
    public static String tf_idfBasedMatching = "tf_idfBasedMatching";

    public static LanguageDetector lang;
    
    public static LanguageDetector initDetector()
    {
        if(lang==null)
        {
            try
            {
                lang = new LanguageDetector();
                lang.init(System.getProperty("user.dir") +"/profiles");
            

            }
            catch(Exception ex)
            {
                  System.out.println(System.getProperty("user.dir"));
            }
        }
        return lang;
    }
    
    private String platform; 
    private String method;
    private int num;

    public Comparator() {
        
    }
    
    
    /////////////////////////////////////////////////////
    //          GENERAL COMAPRISSON
    /////////////////////////////////////////////////////
    
    public Question compare(Question q, Object o)
    {
        Question ret = null;
        
        if(platform.equals(facebookPlatform))
        {
            if(method.equals(letterPairSimilarity))
                ret=compareBagOfWords(q, (List<FacebookUser>)o, getNum());
            
            if(method.equals(maxWordSimilarity))
                ret=compareMinDistanceWords(q, (List<FacebookUser>)o, getNum());
            
            if(method.equals(instanceBasedMatching))
                ret=entityBasedComparisson(q, (List<FacebookUser>)o, getNum());
            
            if(method.equals(tf_idfBasedMatching))
                ret=tf_ifdBasedComparisson(q, (List<FacebookUser>)o, getNum());
           
        }
        if(platform.equals(linkedinPlatform))
        {
            if(method.equals(letterPairSimilarity))
                ret=compareBagOfWords(q, (ArrayList<LinkedinUser>)o, getNum());
            
            if(method.equals(maxWordSimilarity))
                ret=compareMinDistanceWords(q, (ArrayList<LinkedinUser>)o, getNum());
            
            if(method.equals(instanceBasedMatching))
                ret=entityBasedComparisson(q, (ArrayList<LinkedinUser>)o, getNum());
            
            if(method.equals(tf_idfBasedMatching))
                ret=tf_ifdBasedComparisson(q, (ArrayList<LinkedinUser>)o, getNum());
        }
        
        return ret;
    }
    
    
    
    ////////////////////////////////////////////////////
    //      Minimal Word Distance
    ////////////////////////////////////////////////////
    private Question compareMinDistanceWords(Question q, List<FacebookUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(FacebookUser user:users)
        {
            System.out.println("Max Word Similarity with user " + user.getName());
            String userBag = flatten(user);
           if(!userBag.isEmpty())
            {
                
             double sum = MaxWordSimilarity.compareMinDistanceWords(questionBag, userBag);
             userEvals.put(user.getId(), sum);
            }
        }
       q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }
    
    
    
     private Question compareMinDistanceWords(Question q, ArrayList<LinkedinUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(LinkedinUser user:users)
        {
            String userBag = flatten(user);
            if(!userBag.isEmpty())
            {
                double sum = MaxWordSimilarity.compareMinDistanceWords(questionBag, userBag);
                userEvals.put(user.getId(), sum);
            }
        }
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }
    
    
     
     
     ////////////////////////////////////////////////////
     //      Pair Letter similarity
     ////////////////////////////////////////////////////
    
    private Question compareBagOfWords(Question q, List<FacebookUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(FacebookUser user:users)
        {
            String userBag = flatten(user);
            if(!userBag.isEmpty())
            {   
                System.out.println("Start comparing");
                Double score = evaluateLetterPairSimilarity(questionBag,userBag);
                System.out.println("Finish comparing");
                
                userEvals.put(user.getId(), score);
            }
        }
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }
    
    private Question compareBagOfWords(Question q, ArrayList<LinkedinUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(LinkedinUser user:users)
        {
            String userBag = flatten(user);
            
            if(!userBag.isEmpty())
            {
            Double score = evaluateLetterPairSimilarity(questionBag,userBag);
            userEvals.put(user.getId(), score);
            }
        }
        q = setRecommendedUsers(q,userEvals,num);
        return q;
    }
    
    ///////////////////////////////////////////////////////////////
    //      Entity based schema comarisson
    ////////////////////////////////////////////////////////////////
    
    private Question entityBasedComparisson(Question q, List<FacebookUser> users, int num)
    {
        EntityBasedComparisson ebc = new EntityBasedComparisson();
        Map<Long,Double> userEvals = ebc.compare(q, users);
        
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }
    
    private Question entityBasedComparisson(Question q, ArrayList<LinkedinUser> users, int num)
    {
        EntityBasedComparisson ebc = new EntityBasedComparisson();
        Map<Long,Double> userEvals = ebc.compare(q, users);
        
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }
    
    
    
    //////////////////////////////////////////////
    //            UTILITIES
    //////////////////////////////////////////////
    
    public String flatten(Question q)
    {
        String output="";
        
        output = q.getQuestion();
        output += " ";
        output += q.getDescription();
        output += " ";
       
        List<String> keys = q.getQuestionSchema().getKeys();
        for(String k:keys)
        {
            output += k;
            output += " ";
        }
        
        List<Instance> instances = q.getQuestionInstances();
        for(Instance ins:instances)
        {
           for(String kins:ins.getKeys())
           { 
                output += ins.getValue(kins);
                output += " ";
           }
        }
        
        //lower case and stem
        output = preprocess(output);
        
        return output;
    }

    
    public String flatten(FacebookUser user)
    {
        System.out.println("Start flattening");
        String output = "";
        
        output += user.getGender()!=null ? user.getGender():"";
        output += " ";
        
        for(int i = 0;i<FacebookUser.fieldNum;i++) {
            String unchecked = user.flattenFeature(i);
            boolean english = checkLanguage(unchecked);
            if(english) {
                output+=unchecked;
            }
        }
        
        System.out.println("Finish flattening, output= "+ output);
        System.out.println("Start preprocessing");
        output = preprocess(output);
        System.out.println("Stop preprocessing");
        return output;
        
    }
    
    public String flatten(LinkedinUser user)
    {
        String output = "";
        
        for(String field:LinkedinUser.getFields()) {
            String unchecked = user.flattenField(field);
            boolean english = checkLanguage(unchecked);
            if(english) {
                output += unchecked;
            }
        }
        
        output = preprocess(output);
        return output;
    }
    
    public static String preprocess(String output) {
        output = output.toLowerCase();
        
        Pattern pattern = Pattern.compile("\\s+");
        Matcher matcher = pattern.matcher(output);
        boolean check = matcher.find();
        output = matcher.replaceAll(" ");
      
        output = output.trim();
        
        output = stem(output);
                
        output = output.trim();
        
        
        
        return output;
    }
    
    private static String stem(String output) {
        
        
        String [] words = output.split(" ");
        Stemmer stemmer = new Stemmer();
        
        output = "";
        for(int i = 0;i<words.length;i++)
        {
            if(words[i]!=null)
            {
                Stopwords sw = new Stopwords();
                if(!sw.is(words[i]))
                {
                    char [] word = words[i].toCharArray();
                    boolean cond = true;
                    for(int j = 0; j<word.length;j++)
                    {
                        if(!Character.isLetter(word[j]))
                        {
                            cond = false;
                        }
                    }
                    if(cond)
                    {
                        stemmer.add(word, word.length);
                        stemmer.stem();
                        output += stemmer.toString();
                        output += " ";
                    }
                }
            }
        }
        
        
        return output;
    }

    private Double evaluateLetterPairSimilarity(String questionBag, String userBag) {
        
             
        return LetterPairSimilarity.compareStrings(questionBag, userBag);
            
    }

    private Long takeMaxId(Map<Long, Double> userEvals) {
        
        Set<Entry<Long,Double>> set = userEvals.entrySet();
        
        Double max = -1.0;
        Long maxid = 0l;
        for(Entry<Long,Double> entry: set)
        {
            if(max<entry.getValue())
            {
                max = entry.getValue();
                maxid = entry.getKey();
            }
        }
        
        
        return maxid;
    }
    
        private Double takeMax(Map<Long, Double> userEvals) {
        
        Set<Entry<Long,Double>> set = userEvals.entrySet();
        
        Double max = -1.0;
       
        for(Entry<Long,Double> entry: set)
        {
            if(max<entry.getValue())
            {
                max = entry.getValue();
            }
        }
        
        
        return max;
    }
        
    private Question setRecommendedUsers(Question q, Map<Long, Double> userEvals, int num) 
    {
        int k=0;
        if(num>userEvals.size())
        {
            k=userEvals.size();
        }
        else
        {
            k=num;
        }
        
        String [] selected = new String[k];
        String [] confidence = new String[k];
        for(int i =0;i<k;i++)
        {
            Double max = takeMax(userEvals);
            Long maxid = takeMaxId(userEvals);
            userEvals.remove(maxid);
            selected[i]=String.valueOf(maxid);
            confidence[i]=String.valueOf(max);
        }
        q.setSelectedUsers(selected);
        q.setConfidence(confidence);
        
        return q;
    }
    
    
   
        
        
    //////////////////////////////////////////////////////////
    //    Getters & Setters
    //////////////////////////////////////////////////////////
    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the method
     */
    public String getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    private boolean checkLanguage(String unchecked)  
    {
        
        String detect="";
        try
        {
            detect=initDetector().detect(unchecked);
        }
        catch(Exception ex)
        {
            return false;
        }
        if(detect.equals("en"))
            return true;
        else
            return false;
    }

    private Question tf_ifdBasedComparisson(Question q, List<FacebookUser> list, int num) 
    {
        String flattenedQuestion = flatten(q);
        
        String [] flattenedUsers = new String[list.size()];
        List<Long> ids = new ArrayList<Long>();
        int i = 0;
        for(FacebookUser u: list)
        {
            ids.add(u.getId());
            flattenedUsers[i] = flatten(u);
            i++;
        }
        
        Map<Long,Double> userEvals = TFIDFMethod.compare(flattenedQuestion,ids, flattenedUsers);
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
        
    }

    private Question tf_ifdBasedComparisson(Question q, ArrayList<LinkedinUser> arrayList, int num) 
    {
        
        String flattenedQuestion = flatten(q);
        
        String [] flattenedUsers = new String[arrayList.size()];
        List<Long> ids = new ArrayList<Long>();
        int i = 0;
        for(LinkedinUser u: arrayList)
        {
            ids.add(u.getId());
            flattenedUsers[i] = flatten(u);
            i++;
        }
        
        Map<Long,Double> userEvals = TFIDFMethod.compare(flattenedQuestion,ids, flattenedUsers);
        q = setRecommendedUsers(q,userEvals,num);
        
        return q;
    }

   
    
}

 
  
