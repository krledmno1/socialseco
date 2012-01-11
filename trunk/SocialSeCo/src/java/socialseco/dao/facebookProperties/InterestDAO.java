/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookInterest;

/**
 *
 * @author krle
 */
public class InterestDAO extends GenericDAO<FacebookInterest,Long>{
     public FacebookInterest readFacebookInterestByName(String name) {
     getSession().beginTransaction();
        
        FacebookInterest obj = (FacebookInterest) getSession()
                .createCriteria(FacebookInterest.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

    
}
