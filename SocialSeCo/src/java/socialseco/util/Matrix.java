/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.util;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author damian
 */
@Entity
public class Matrix
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    protected Integer xx, yy;
    protected long[][] matrixArray;

    public Matrix() {
        matrixArray = null;
        xx = null; yy = null;
    }
    
    public Matrix(int x, int y) {
        xx = x; yy = y;
        matrixArray = new long[xx][yy];
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public long get(int x, int y) {
        if(matrixArray == null & xx != null && yy != null)
            matrixArray = new long[xx][yy];
        return matrixArray[x][y];
    }

    public void set(int x, int y, long valor) {
        if(matrixArray == null & xx != null && yy != null)
            matrixArray = new long[xx][yy];
        matrixArray[x][y] = valor;
    }
    
    public int getX() {
        return xx;
    }
    public void setX(Integer xx) {
        this.xx = xx;
        if(matrixArray == null & xx != null && yy != null)
            matrixArray = new long[xx][yy];
    }
    public void setY(Integer yy) {
        this.yy = yy;
        if(matrixArray == null & xx != null && yy != null)
            matrixArray = new long[xx][yy];
    }

    public int getY() {
        return yy;
    }
    
    public long[][] getMatrixArray(){
        return matrixArray;
    }
    public void setMatrixArray(long[][] matrixArray){
        this.matrixArray = matrixArray;
    }
}
