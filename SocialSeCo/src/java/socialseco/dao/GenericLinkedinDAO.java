/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author damian
 */
public class GenericLinkedinDAO<T, ID extends Serializable>
    extends GenericDAO<T, ID> {
    
    public List<T> readByLinkedinId(String linkedinId) {
        getSession().beginTransaction();
        
        List<T> t = getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.eq("linkedinId", linkedinId))
                .list();
        
        getSession().getTransaction().commit();
        
        return t;
    }
    
}
