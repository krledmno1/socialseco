/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao;

import java.util.List;
import org.hibernate.criterion.Restrictions;
import socialseco.model.UserSimilarity;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserSimilarityDAO
    extends GenericDAO<UserSimilarity, Long> {
    
    public UserSimilarity readByUsers(LinkedinUser linUser, FacebookUser fbkUser) {
        List<UserSimilarity> t = getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.eq("facebookUser", fbkUser))
                .add(Restrictions.eq("linkedinUser", linUser))
                .list();
        
        if(t == null || t.isEmpty())
            return null;
        return t.get(0);
    }
    
    public UserSimilarity readByFacebookUsers(FacebookUser fbkUser) {
        List<UserSimilarity> t = getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.eq("facebookUser", fbkUser))
                .list();
        
        if(t == null || t.isEmpty())
            return null;
        return t.get(0);
    }
    
    public UserSimilarity readByLinkedinUsers(LinkedinUser linUser) {
        List<UserSimilarity> t = getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.eq("linkedinUser", linUser))
                .list();
        
        if(t == null || t.isEmpty())
            return null;
        return t.get(0);
    }
    
}
