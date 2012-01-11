/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookGroup;

/**
 *
 * @author krle
 */
public class GroupDAO extends GenericDAO<FacebookGroup,Long>{
    public FacebookGroup readFacebookGroupByName(String name) {
         getSession().beginTransaction();
        
        FacebookGroup obj = (FacebookGroup) getSession()
                .createCriteria(FacebookGroup.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
