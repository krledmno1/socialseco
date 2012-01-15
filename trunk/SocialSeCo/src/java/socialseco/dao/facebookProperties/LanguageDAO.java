/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookLanguage;

/**
 *
 * @author krle
 */
public class LanguageDAO extends GenericDAO<FacebookLanguage,Long>{
     public FacebookLanguage readFacebookLanguageByName(String name) {
     getSession().beginTransaction();
        
        FacebookLanguage obj = (FacebookLanguage) getSession()
                .createCriteria(FacebookLanguage.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
