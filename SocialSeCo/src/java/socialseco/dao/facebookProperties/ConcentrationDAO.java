/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookConcentration;

/**
 *
 * @author krle
 */
public class ConcentrationDAO extends GenericDAO<FacebookConcentration,Long>{
    public FacebookConcentration readFacebookConcentrationByName(String name) {
         getSession().beginTransaction();
        
        FacebookConcentration obj = (FacebookConcentration) getSession()
                .createCriteria(FacebookConcentration.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
