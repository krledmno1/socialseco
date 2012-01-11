/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookMovie;

/**
 *
 * @author krle
 */
public class MovieDAO extends GenericDAO<FacebookMovie,String>{
     public FacebookMovie readFacebookMovieByName(String name) {
     getSession().beginTransaction();
        
        FacebookMovie obj = (FacebookMovie) getSession()
                .createCriteria(FacebookMovie.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

    
}
