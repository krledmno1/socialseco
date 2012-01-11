/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookWork;

/**
 *
 * @author krle
 */
public class WorkDAO extends GenericDAO<FacebookWork,Long>{
    public FacebookWork readFacebookWorkByEmployerAndPosition(String employer, String position) {
        getSession().beginTransaction();
        
        FacebookWork obj = (FacebookWork) getSession()
                .createCriteria(FacebookWork.class)
                .add(Restrictions.eq("employer", employer)).add(Restrictions.eq("position",position))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
