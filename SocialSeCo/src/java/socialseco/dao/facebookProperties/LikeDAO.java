/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookLike;

/**
 *
 * @author krle
 */
public class LikeDAO extends GenericDAO<FacebookLike,String>{
    
    public FacebookLike readFacebookLikeByName(String name) {
         getSession().beginTransaction();
        
        FacebookLike obj = (FacebookLike) getSession()
                .createCriteria(FacebookLike.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }
}
