/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookTelevision;

/**
 *
 * @author krle
 */
public class TelevisionDAO extends GenericDAO<FacebookTelevision,Long>{
     public FacebookTelevision readFacebookTelevisionByName(String name) {
         getSession().beginTransaction();
        
        FacebookTelevision obj = (FacebookTelevision) getSession()
                .createCriteria(FacebookTelevision.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
