/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.List;
import socialseco.dao.LinkedinLanguageDAO;
import socialseco.dao.UserDAO;

import socialseco.dao.facebookProperties.*;
import socialseco.model.facebook.*;
import socialseco.model.linkedin.LinkedinLanguage;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserUpdater {
    
    
    public Object totalRecall(String platform)
    {
        Object ret = null;
        UserDAO dao = new UserDAO();
        if(platform.equals("facebook"))
        {
            ret = dao.readAllFacebookUsers_without_transaction();
            //TO DO obtain other data: activities, likes...
        }
        if(platform.equals("linkedin"))
        {
            ret = dao.readAllLinkedinUsersWithoutTransaction();
        }
        return ret;
    }
    
    public void updateUsers(List<LinkedinUser> users) {
        UpdateLinkedInUser updater = new UpdateLinkedInUser();
        updater.updateUsers(users);
        /*UserDAO dao = new UserDAO();
        
        for(LinkedinUser user_it: users){
            LinkedinUser read_user = dao.readLinkedinUsersByLinkedinId(user_it.getLinkedinId());
            
            if(read_user != null) {
                LinkedinLanguageDAO languageDAO = new LinkedinLanguageDAO();
                for(LinkedinLanguage language:read_user.getLanguages())
                    languageDAO.remove(language);
                
                read_user.setValuesFrom(user_it);
                dao.persist(read_user);
            } else {
                dao.persist(user_it);
            }
        }*/
    }
    
    
    public void updateFBUsers(List<FacebookUser> users) {
        UserDAO dao = new UserDAO();
        
        for(FacebookUser user_it: users){
            FacebookUser read_user = dao.readFacebookUsersById(user_it.getFacebookId());
            user_it.removeDuplicates();
            user_it.updateReferences();
            
            if(read_user != null) {
                
                read_user.setValuesFrom(user_it);
                dao.persist(read_user);
            } else {
                
                dao.persist(user_it);
            }
        }
    }
    
    
    public Long updateFBActivity(FacebookActivity obj)
    {
        Long ret= null;
        ActivityDAO dao = new ActivityDAO();
        FacebookActivity act = dao.readFacebookActivityByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
                    
    }
    
    public Long updateFBBook(FacebookBook obj)
    {
        Long ret= null;
        BookDAO dao = new BookDAO();
        FacebookBook act = dao.readFacebookBookByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
     
    public Long updateFBConcentration(FacebookConcentration obj)
    {
        Long ret= null;
        ConcentrationDAO dao = new ConcentrationDAO();
        FacebookConcentration act = dao.readFacebookConcentrationByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBEducation(FacebookEducation obj)
    {
        Long ret= null;
        EducationDAO dao = new EducationDAO();
        FacebookEducation act = dao.readFacebookEducationBySchoolAndType(obj.getSchool(),obj.getType());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBGame(FacebookGame obj)
    {
        Long ret= null;
        GameDAO dao = new GameDAO();
        FacebookGame act = dao.readFacebookGameByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBGroup(FacebookGroup obj)
    {
        Long ret= null;
        GroupDAO dao = new GroupDAO();
        FacebookGroup act = dao.readFacebookGroupByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBInterests(FacebookInterest obj)
    {
        Long ret= null;
        InterestDAO dao = new InterestDAO();
        FacebookInterest act = dao.readFacebookInterestByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBLike(FacebookLike obj)
    {
        Long ret= null;
        LikeDAO dao = new LikeDAO();
        FacebookLike act = dao.readFacebookLikeByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    
    public Long updateFBMovie(FacebookMovie obj)
    {
        Long ret= null;
        MovieDAO dao = new MovieDAO();
        FacebookMovie act = dao.readFacebookMovieByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBMusic(FacebookMusic obj)
    {
        Long ret= null;
        MusicDAO dao = new MusicDAO();
        FacebookMusic act = dao.readFacebookMusicByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBSport(FacebookSport obj)
    {
        Long ret= null;
        SportDAO dao = new SportDAO();
        FacebookSport act = dao.readFacebookSportByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBTelevision(FacebookTelevision obj)
    {
        Long ret= null;
        TelevisionDAO dao = new TelevisionDAO();
        FacebookTelevision act = dao.readFacebookTelevisionByName(obj.getName());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
    
    public Long updateFBWork(FacebookWork obj)
    {
        Long ret= null;
        WorkDAO dao = new WorkDAO();
        FacebookWork act = dao.readFacebookWorkByEmployerAndPosition(obj.getEmployer(),obj.getPosition());
        if(act!=null)
            ret=act.getId();
        return ret;
    }
}
