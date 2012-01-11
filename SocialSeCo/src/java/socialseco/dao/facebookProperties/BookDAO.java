/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookBook;

/**
 *
 * @author krle
 */
public class BookDAO extends GenericDAO<FacebookBook,String>{
    
    
    public FacebookBook readFacebookBookByName(String name) {
        getSession().beginTransaction();
        
        FacebookBook obj = (FacebookBook) getSession()
                .createCriteria(FacebookBook.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

    
}
