/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookActivity;

/**
 *
 * @author krle
 */
public class ActivityDAO extends GenericDAO<FacebookActivity,Long> {
     
    
    public FacebookActivity readFacebookActivityByName(String name) {
        getSession().beginTransaction();
        
        FacebookActivity obj = (FacebookActivity) getSession()
                .createCriteria(FacebookActivity.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

    
}
