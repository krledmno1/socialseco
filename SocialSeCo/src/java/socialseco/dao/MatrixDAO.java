/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.dao;

import org.hibernate.criterion.Restrictions;
import socialseco.model.util.Matrix;

/**
 *
 * @author damian
 */
public class MatrixDAO
    extends GenericDAO<Matrix, Long> {
    
    
    public Matrix readMatrixByIdWithoutTransaction(Long id) {
        
        Matrix m = (Matrix) getSession()
                .createCriteria(Matrix.class)
                .add(Restrictions.eq("id", id))
                .uniqueResult();
        
         
        return m;
    }
}
