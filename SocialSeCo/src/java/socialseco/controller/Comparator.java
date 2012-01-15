/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;
import socialseco.model.question.Instance;
import socialseco.model.question.Question;
import socialseco.model.question.Schema;

/**
 *
 * @author krle
 */
public class Comparator {

    public Comparator() {
        
    }
    
    public Question compareBagOfWords(Question q, List<FacebookUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(FacebookUser user:users)
        {
            String userBag = flatten(user);
            Double score = evaluateLetterPairSimilarity(questionBag,userBag);
            userEvals.put(user.getId(), score);
        }
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
    
    public Question compareBagOfWords(Question q, ArrayList<LinkedinUser> users, int num)
    {
        String questionBag = flatten(q);
        Map<Long,Double> userEvals = new HashMap<Long, Double>();
        
        for(LinkedinUser user:users)
        {
            String userBag = flatten(user);
            Double score = evaluateLetterPairSimilarity(questionBag,userBag);
            userEvals.put(user.getId(), score);
        }
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
    
    
    
    private String flatten(Question q)
    {
        String output="";
        
        output = q.getQuestion();
        output += " ";
        output += q.getDescription();
        output += " ";
       
        List<String> keys = q.getQuestionSchema().getKeys();
        for(String k:keys)
        {
            output += q.getQuestionSchema().getValue(k);
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

    
    private String flatten(FacebookUser user)
    {
        return null;    //TO DO
    }
    
    private String flatten(LinkedinUser user)
    {
        return null;    //TO DO
    }
    
    
    private String preprocess(String output) {
        output = output.toLowerCase();
        while(output.contains("  "))
        {
            output.replaceAll("  ", " ");
        }
        
        String [] words = output.split(" ");
        Stemmer stemmer = new Stemmer();
        
        output = "";
        for(int i = 0;i<words.length;i++)
        {
            if(words[i]!=null)
            {
                char [] word = words[i].toCharArray();
                boolean cond = true;
                for(int j = 0; j<word.length;j++)
                {
                    if(!Character.isLetter(word[i]))
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
    
}

 
  