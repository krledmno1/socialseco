/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookGame;

/**
 *
 * @author krle
 */
public class GameDAO extends GenericDAO<FacebookGame,Long>{
     public FacebookGame readFacebookGameByName(String name) {
         getSession().beginTransaction();
        
        FacebookGame obj = (FacebookGame) getSession()
                .createCriteria(FacebookGame.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

    
}
