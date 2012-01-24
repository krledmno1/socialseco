/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author damian
 */
public class GenericDAO<T, ID extends Serializable> {
    
    private Class<T> persistentClass;
    
    public GenericDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
                                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected Session getSession() {
        return HibernateUtil.getSessionFactory().getCurrentSession();
    }

    
    public void beginConversation() {
        getSession().beginTransaction();
    }

    
    public void endConversation() {
        if(getSession().getTransaction().isActive()){
            getSession().getTransaction().commit();
            getSession().close();
        }
    }
    
    public T readById(ID id, boolean lock) {
        getSession().beginTransaction();
        
        T t = (T)getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.idEq(id))
                .uniqueResult();
        
        getSession().getTransaction().commit();
        
        return t;
    }

    
    public List<T> readAll() {
        getSession().beginTransaction();
        
        List<T> lista = getSession()
                .createCriteria(getPersistentClass())
                .list();
        
        getSession().getTransaction().commit();
        
        return lista;
    }
    
    public List<T> readByName(String name) {
        getSession().beginTransaction();
        
        List<T> t = getSession()
                .createCriteria(getPersistentClass())
                .add(Restrictions.eq("name", name))
                .list();
        
        getSession().getTransaction().commit();
        
        return t;
    }

    
    public T persist(T entity) {
        getSession().beginTransaction();
        
        //getSession().merge(entity);
        getSession().saveOrUpdate(entity);
        
        getSession().getTransaction().commit();
        
        return entity;
    }
    
    public T persistWithoutTransaction(T entity) {
        getSession().saveOrUpdate(entity);
        
        return entity;
    }

    
    public void remove(T entity) {
        getSession().beginTransaction();
        getSession().delete(entity);
        getSession().getTransaction().commit();
    }
    
    protected Class<T> getPersistentClass() {
        return persistentClass;
    }
}
