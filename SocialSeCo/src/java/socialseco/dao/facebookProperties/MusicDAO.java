/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookMusic;

/**
 *
 * @author krle
 */
public class MusicDAO extends GenericDAO<FacebookMusic,Long>{
     public FacebookMusic readFacebookMusicByName(String name) {
     getSession().beginTransaction();
        
        FacebookMusic obj = (FacebookMusic) getSession()
                .createCriteria(FacebookMusic.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
