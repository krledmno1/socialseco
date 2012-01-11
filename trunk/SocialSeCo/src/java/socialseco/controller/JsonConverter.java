/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import socialseco.facebook.Json.JsonUser;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.facebook.FacebookUser.Work;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author krle
 */
public class JsonConverter {
    public static String convertToJson(LinkedinUser user)
    {
        Gson gson = new Gson();
        return gson.toJson(user);
        
    }
    
    public static String convertToJson(FacebookUser user)
    {
        Gson gson = new Gson();
        return gson.toJson(user);
        
        
    }
    
    
    public static List<FacebookUser> convertToFBObjects(List<JsonUser> users)
    {
        List<FacebookUser> fbusers = new ArrayList<FacebookUser>();
        for(JsonUser user:users)
        {
            fbusers.add(convertToFBObject(user));
        }
        return fbusers;
    }
    
    
    public static FacebookUser convertToFBObject(JsonUser user)
    {
        int i,j;
        FacebookUser fbUser = new FacebookUser();
        fbUser.setFacebookId(user.getId());
        fbUser.setName(user.getFirst_name());
        fbUser.setSurname(user.getLast_name());
        fbUser.setFullName(user.getName());
        if(user.getHometown()!=null)
            fbUser.setHometown(user.getHometown().getName());
        if(user.getLocation()!=null)
            fbUser.setLocation(user.getLocation().getName());
        if(user.getWork()!=null)
        {
            for(i=0;i<user.getWork().length;i++)
            {
                FacebookUser.Work work = fbUser.new Work();
                if(user.getWork()[i].getEmployer()!=null)
                    work.setEmployer(user.getWork()[i].getEmployer().getName());
                if(user.getWork()[i].getPosition()!=null)
                    work.setPosition(user.getWork()[i].getPosition().getName());

                fbUser.getWork().add(work);
            }
        }
        
        fbUser.setBio(user.getBio());
        fbUser.setGender(user.getGender());
        fbUser.setReligion(user.getReligion());
        
        if(user.getEducation()!=null)
        {
            
            for(i=0;i<user.getEducation().length;i++)
            {
                FacebookUser.Education edu = fbUser.new Education();
                if(user.getEducation()[i].getSchool()!=null)
                    edu.setSchool(user.getEducation()[i].getSchool().getName());
                if(user.getEducation()[i].getConcentration()!=null)
                {
                    for(j=0;j<user.getEducation()[i].getConcentration().length;j++)
                    {
                        edu.getConecntration().add(user.getEducation()[i].getConcentration()[j].getName());
                    }
                }
                edu.setType(user.getEducation()[i].getType());

                fbUser.getEducation().add(edu);
            }
        }
        if(user.getSports()!=null)
        {
            for(i=0;i<user.getSports().length;i++)
            {
                fbUser.getSports().add(user.getSports()[i].getName());
            }
        }
        
        if(user.getAct()!=null)
        {
            for(i=0;i<user.getAct().data.length;i++)
            {
                fbUser.getActivities().add(user.getAct().data[i].getName());
            }
        }
        
        
        if(user.getGro()!=null)
        {
            for(i=0;i<user.getGro().data.length;i++)
            {
                fbUser.getGroups().add(user.getGro().data[i].getName());
            }
        }
        
        if(user.getInte()!=null)
        {
            for(i=0;i<user.getInte().data.length;i++)
            {
                fbUser.getInterests().add(user.getInte().data[i].getName());
            }
        }
        
        if(user.getLike()!=null)
        {
            for(i=0;i<user.getLike().data.length;i++)
            {
                fbUser.getLikes().add(user.getLike().data[i].getName());
            }
        }
        
        
        if(user.getMov()!=null)
        {
            for(i=0;i<user.getMov().data.length;i++)
            {
                fbUser.getMovies().add(user.getMov().data[i].getName());
            }
        }
        
        if(user.getMus()!=null)
        {
            for(i=0;i<user.getMus().data.length;i++)
            {
                fbUser.getMusic().add(user.getMus().data[i].getName());
            }
        }
        
        if(user.getTel()!=null)
        {
            for(i=0;i<user.getTel().data.length;i++)
            {
                fbUser.getTelevision().add(user.getTel().data[i].getName());
            }
        }
        
        
        if(user.getGam()!=null)
        {
            for(i=0;i<user.getGam().data.length;i++)
            {
                fbUser.getGames().add(user.getGam().data[i].getName());
            }
        }
        
        if(user.getBook()!=null)
        {
            for(i=0;i<user.getBook().data.length;i++)
            {
                fbUser.getBooks().add(user.getBook().data[i].getName());
            }
        }
        return fbUser;
    }
    
}
