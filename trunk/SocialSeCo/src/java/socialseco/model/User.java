/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author damian
 */
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class User
        implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String name;
    protected String surname;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public void setValuesFrom(User user){
        setName(user.getName());
        setSurname(user.getSurname());
    }
}
