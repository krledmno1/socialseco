/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;
import socialseco.model.User;

/**
 *
 * @author damian
 */
public class UserDAO
        extends GenericDAO<User, Long> {
    
    public List<LinkedinUser> readAllLinkedinUsers() {
        getSession().beginTransaction();
        
        List<LinkedinUser> list = getSession()
                .createCriteria(LinkedinUser.class)
                .list();
        
        getSession().getTransaction().commit();
        
        return list;
    }
    
    public LinkedinUser readLinkedinUsersByLinkedinId(String linkedinId) {
        getSession().beginTransaction();
        
        LinkedinUser user = (LinkedinUser) getSession()
                .createCriteria(LinkedinUser.class)
                .add(Restrictions.eq("linkedinId", linkedinId))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return user;
    }
    
    public List<FacebookUser> readAllFacebookUsers() {
        getSession().beginTransaction();
        
        List<FacebookUser> list = getSession()
                .createCriteria(FacebookUser.class)
                .list();
        
        getSession().getTransaction().commit();
        
        return list;
    }
}
