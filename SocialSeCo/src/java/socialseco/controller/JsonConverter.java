/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import socialseco.facebook.Json.JsonUser;
import socialseco.model.facebook.*;

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
                FacebookWork work = new FacebookWork();
                
                if(user.getWork()[i].getEmployer()!=null)
                    work.setEmployer(user.getWork()[i].getEmployer().getName().toLowerCase());
                if(user.getWork()[i].getPosition()!=null)
                    work.setPosition(user.getWork()[i].getPosition().getName().toLowerCase());

                fbUser.getWorks().add(work);
            }
        }
        if(user.getBio()!=null)
        {
            if(user.getBio().length()>4999)
            {
                fbUser.setBio(user.getBio().substring(0, 4999));
            }
        }
        fbUser.setGender(user.getGender());
        fbUser.setReligion(user.getReligion());
        
        if(user.getEducation()!=null)
        {
            
            for(i=0;i<user.getEducation().length;i++)
            {
                FacebookEducation edu = new FacebookEducation();
                
                
                if(user.getEducation()[i].getSchool()!=null)
                    edu.setSchool(user.getEducation()[i].getSchool().getName().toLowerCase());
                if(user.getEducation()[i].getConcentration()!=null)
                {
                    for(j=0;j<user.getEducation()[i].getConcentration().length;j++)
                    {
                        FacebookConcentration con = new FacebookConcentration();
                        //con.setId(Long.parseLong(user.getEducation()[i].getConcentration()[j].getId()));
                        con.setName(user.getEducation()[i].getConcentration()[j].getName().toLowerCase());
                        edu.getConecentration().add(con);
                    }
                }
                edu.setType(user.getEducation()[i].getType().toLowerCase());

                fbUser.getEducation().add(edu);
            }
        }
        if(user.getSports()!=null)
        {
            for(i=0;i<user.getSports().length;i++)
            {
                FacebookSport spo = new FacebookSport();
               // spo.setId(Long.parseLong(user.getSports()[i].getId()));
                spo.setName(user.getSports()[i].getName().toLowerCase());
                fbUser.getSports().add(spo);
            }
        }
        
        if(user.getAct()!=null)
        {
            for(i=0;i<user.getAct().data.length;i++)
            {
                FacebookActivity act = new FacebookActivity();
               // act.setId(Long.parseLong(user.getAct().data[i].getId()));                
                act.setName(user.getAct().data[i].getName().toLowerCase());
                fbUser.getActivities().add(act);
            }
        }
        
        
        if(user.getGro()!=null)
        {
            for(i=0;i<user.getGro().data.length;i++)
            {
                FacebookGroup gro = new FacebookGroup();
               // gro.setId(Long.parseLong(user.getGro().data[i].getId()));
                gro.setName(user.getGro().data[i].getName().toLowerCase());
                fbUser.getGroups().add(gro);
            }
        }
        
        if(user.getInte()!=null)
        {
            for(i=0;i<user.getInte().data.length;i++)
            {
                FacebookInterest inte = new FacebookInterest();
            //    inte.setId(Long.parseLong(user.getInte().data[i].getId()));
                inte.setName(user.getInte().data[i].getName().toLowerCase());
                fbUser.getInterests().add(inte);
            }
        }
        
        if(user.getLike()!=null)
        {
            for(i=0;i<user.getLike().data.length;i++)
            {
                FacebookLike like = new FacebookLike();
              //  like.setId(Long.parseLong(user.getLike().data[i].getId()));
                like.setName(user.getLike().data[i].getName().toLowerCase());
                fbUser.getLikes().add(like);
            }
        }
        
        
        if(user.getMov()!=null)
        {
            for(i=0;i<user.getMov().data.length;i++)
            {
                FacebookMovie mov = new FacebookMovie();
             //   mov.setId(Long.parseLong(user.getMov().data[i].getId()));
                mov.setName(user.getMov().data[i].getName().toLowerCase());
                fbUser.getMovies().add(mov);
            }
        }
        
        if(user.getMus()!=null)
        {
            for(i=0;i<user.getMus().data.length;i++)
            {
                FacebookMusic mus = new FacebookMusic();
             //   mus.setId(Long.parseLong(user.getMus().data[i].getId()));
                mus.setName(user.getMus().data[i].getName().toLowerCase());
                fbUser.getMusic().add(mus);
            }
        }
        
        if(user.getTel()!=null)
        {
            for(i=0;i<user.getTel().data.length;i++)
            {
                FacebookTelevision tel = new FacebookTelevision();
           //     tel.setId(Long.parseLong(user.getTel().data[i].getId()));
                tel.setName(user.getTel().data[i].getName().toLowerCase());
                fbUser.getTelevision().add(tel);
            }
        }
        
        
        if(user.getGam()!=null)
        {
            for(i=0;i<user.getGam().data.length;i++)
            {
                FacebookGame gam = new FacebookGame();
            //    gam.setId(Long.parseLong(user.getGam().data[i].getId()));
                gam.setName(user.getGam().data[i].getName().toLowerCase());
                fbUser.getGames().add(gam);
            }
        }
        
        if(user.getBook()!=null)
        {
            for(i=0;i<user.getBook().data.length;i++)
            {
                FacebookBook book = new FacebookBook();
            //    book.setId(Long.parseLong(user.getBook().data[i].getId()));
                book.setName(user.getBook().data[i].getName().toLowerCase());
                fbUser.getBooks().add(book);
            }
        }
        
        
         if(user.getLanguages()!=null)
        {
            for(i=0;i<user.getLanguages().length;i++)
            {
                FacebookLanguage lan = new FacebookLanguage();
               // spo.setId(Long.parseLong(user.getSports()[i].getId()));
                lan.setName(user.getLanguages()[i].getName().toLowerCase());
                fbUser.getLanguages().add(lan);
            }
        }
        
        
        return fbUser;
    }
    
}
