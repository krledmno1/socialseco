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
    
    private String platform; 
    private String method;
    private int num;

    public Comparator() {
        
    }
    
    public Question compare(Question q, Object o)
    {
        Question ret = null;
        
        if(platform.equals("facebook"))
        {
            if(method.equals("letterPairSimilarity"))
                ret=compareBagOfWords(q, (List<FacebookUser>)o, getNum());
        }
        if(platform.equals("facebook"))
        {
            if(method.equals("letterPairSimilarity"))
                ret=compareBagOfWords(q, (ArrayList<LinkedinUser>)o, getNum());
        }
        
        return ret;
    }
    
    private Question compareBagOfWords(Question q, List<FacebookUser> users, int num)
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
    
    private Question compareBagOfWords(Question q, ArrayList<LinkedinUser> users, int num)
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
        
        String output = "";
        
        output += user.getBio();
        output += " ";
        
        output += user.getHometown();
        output += " ";
        
        output += user.getGender();
        output += " ";
        
        output += user.getLocation();
        output += " ";
        
        output += user.getReligion();
        output += " ";
        
        for(FacebookActivity obj: user.getActivities())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookBook obj: user.getBooks())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookGame obj: user.getGames())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookGroup obj: user.getGroups())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookInterest obj: user.getInterests())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookLike obj: user.getLikes())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookMovie obj: user.getMovies())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookMusic obj: user.getMusic())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookSport obj: user.getSports())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookTelevision obj: user.getTelevision())
        {
            output += obj.getName();
            output += " ";
        }
        
        for(FacebookEducation obj: user.getEducation())
        {
            output += obj.getSchool();
            output += " ";
            output += obj.getType();
            output += " ";
            
            for(FacebookConcentration con: obj.getConecentration())
            {    
                output += con.getName();
                output += " ";
            }   
        }
        
        for(FacebookWork obj: user.getWorks())
        {
            output += obj.getEmployer();
            output += " ";
            output += obj.getPosition();
            output += " ";
        }
       
        for(FacebookLanguage obj: user.getLanguages())
        {
            output += obj.getName();
            output += " ";
        }
        
        output = preprocess(output);
        
        
        return output;
        
    }
    
    private String flatten(LinkedinUser user)
    {
        String output = "";
        
        //flatten linkedin
        
        output = preprocess(output);
        return output;
    }
    
    private String preprocess(String output) {
        output = output.toLowerCase();
        while(output.contains("  "))
        {
            output.replaceAll("  ", " ");
        }
        return output;
    }
    
    private String preprocess2(String output) {
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
    
}

 
  