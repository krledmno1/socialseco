/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookSport;

/**
 *
 * @author krle
 */
public class SportDAO extends GenericDAO<FacebookSport,Long>{
     public FacebookSport readFacebookSportByName(String name) {
         getSession().beginTransaction();
        
        FacebookSport obj = (FacebookSport) getSession()
                .createCriteria(FacebookSport.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
