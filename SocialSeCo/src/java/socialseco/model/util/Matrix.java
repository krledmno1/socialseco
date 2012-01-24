/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.util;

import java.io.Serializable;
import java.util.List;
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
    private String [] userFields;
    private String [] questionFields;
    private Integer userNum;
    
    protected double[][] matrixArray;

    public Matrix() {
        matrixArray = null;
        xx = null; yy = null;
    }
    
    public Matrix(int x, int y) {
        xx = x; yy = y;
        userFields = new String[xx];
        questionFields = new String[yy];
        matrixArray = new double[xx][yy];
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public double get(int x, int y) {
        if(matrixArray == null && xx != null && yy != null)
            matrixArray = new double[xx][yy];
        return matrixArray[x][y];
    }

    public void set(int x, int y, double valor) {
        if(matrixArray == null && xx != null && yy != null)
            matrixArray = new double[xx][yy];
        matrixArray[x][y] = valor;
    }
    
    public int getX() {
        return xx;
    }
    public void setX(Integer xx) {
        this.xx = xx;
        if(matrixArray == null && xx != null && yy != null)
            matrixArray = new double[xx][yy];
    }
    public void setY(Integer yy) {
        this.yy = yy;
        if(matrixArray == null && xx != null && yy != null)
            matrixArray = new double[xx][yy];
    }

    public int getY() {
        return yy;
    }
    
    public double[][] getMatrixArray(){
        return matrixArray;
    }
    public void setMatrixArray(double[][] matrixArray){
        this.matrixArray = matrixArray;
    }

    /**
     * @return the userNum
     */
    public Integer getUserNum() {
        return userNum;
    }

    /**
     * @param userNum the userNum to set
     */
    public void setUserNum(Integer userNum) {
        this.userNum = userNum;
    }
    
    public void  addQuestionFeature(String name, double[] vals)
    {
        int newyy= yy+1;
        double[][] newMatrix = new double[xx][newyy];
        for(int i = 0; i<xx; i++)
        {
            for(int j = 0; j<newyy; j++)
            {
                if(j!=newyy-1)
                {
                    newMatrix[i][j]= matrixArray[i][j];
                }
                else
                {
                    newMatrix[i][j] = vals[i];
                }
            }
            
        }
        
        String [] newQuestionFields = new String[newyy];
        for(int j = 0; j<yy; j++)
        {
            newQuestionFields[j]=getQuestionFields()[j];
        }
        newQuestionFields[yy]=name;
        
        matrixArray = newMatrix;
        setQuestionFields(newQuestionFields);
        yy=newyy;
    }

    public void init(List<String> userFields, List<String> questionFields) {
         for(int i = 0; i<xx; i++)
         {
            this.getUserFields()[i]=userFields.get(i);
            for(int j = 0; j<yy; j++)
            {
                matrixArray[i][j]=0.0;
            }
         }
         for(int j = 0; j<yy; j++)
         {
             this.getQuestionFields()[j]=questionFields.get(j);
         }
         
    }

    /**
     * @return the userFields
     */
    public String[] getUserFields() {
        return userFields;
    }

    /**
     * @param userFields the userFields to set
     */
    public void setUserFields(String[] userFields) {
        this.userFields = userFields;
    }

    /**
     * @return the questionFields
     */
    public String[] getQuestionFields() {
        return questionFields;
    }

    /**
     * @param questionFields the questionFields to set
     */
    public void setQuestionFields(String[] questionFields) {
        this.questionFields = questionFields;
    }
    
}
