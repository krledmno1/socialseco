/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.linkedin;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author damian
 */
@Entity
public class LinkedinDate
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected Integer day;
    protected Integer year;
    protected Integer month;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }
    public void setYear(Integer year) {
        this.year = year;
    }
    public void setYear(String year) {
        this.year = Integer.parseInt(year);
    }
    
    public Integer getMonth() {
        return month;
    }
    public void setMonth(Integer month) {
        this.month = month;
    }
    public void setMonth(String month) {
        this.month = Integer.parseInt(month);;
    }
    
    public Integer getDay() {
        return day;
    }
    public void setDay(Integer day) {
        this.day = day;
    }
    public void setDay(String day) {
        this.day = Integer.parseInt(day);
    }

    @Override
    public String toString() {
        String ret = "";
        if(year != null){
            ret += year;
            if(month != null){
                ret = month + "/" + ret;
                if(day != null)
                    ret = day + "/" + ret;
            }
        }
        return ret;
    }
}
