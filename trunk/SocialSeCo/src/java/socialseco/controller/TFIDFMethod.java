/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;
import socialseco.model.question.Question;

/**
 *
 * @author dd
 */
public class TFIDFMethod {
    
    public static double computeTFIDFScore(Integer noDoc, String term, String crDoc, String[] allDocs) {
        
        double tf = MaxWordSimilarity.compareMinDistanceWords(term, crDoc)/2;
        double noOfDocWithTerm = 1;
        
        for (int i = 0; i < allDocs.length; i++) {
            if (allDocs[i].contains(term)) {
                noOfDocWithTerm++;
            }
        }
        
        //tf-idf(t,f) = tf(t,d)*idf(f)
        double idf = Math.log(noDoc/noOfDocWithTerm); 
        
        return tf*idf;
    }
    
    public static Map<Long,Double> compare(String q, List<Long> ids, String [] users)
    {
         Map<Long,Double> userEvals = new HashMap<Long, Double>();
         
         for(int i = 0;i<users.length;i++)
         {
             double score=0;
             if(users[i]!=null && !users[i].isEmpty() && q!=null && !q.isEmpty() )
             {
                score = computeTFIDFScore(users.length, q, users[i], users);
             }
             userEvals.put(ids.get(i), score);  
            
             
        }
        return userEvals;
    }
    

    
    
    
    
}
