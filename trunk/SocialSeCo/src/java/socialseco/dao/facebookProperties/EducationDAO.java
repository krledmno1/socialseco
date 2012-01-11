/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao.facebookProperties;

import org.hibernate.criterion.Restrictions;
import socialseco.dao.GenericDAO;
import socialseco.model.facebook.FacebookEducation;

/**
 *
 * @author krle
 */
public class EducationDAO extends GenericDAO<FacebookEducation,Long> {
     public FacebookEducation readFacebookEducationBySchoolAndType(String school, String type) {
        getSession().beginTransaction();
        
        FacebookEducation obj = (FacebookEducation) getSession()
                .createCriteria(FacebookEducation.class)
                .add(Restrictions.eq("school", school)).add(Restrictions.eq("type",type))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return obj;
    }

   
}
