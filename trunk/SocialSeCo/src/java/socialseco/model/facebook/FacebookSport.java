/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.facebook;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author krle
 */
@Entity
public class FacebookSport implements Serializable {
    @Id @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
@Column(unique=true)
     private String name;

    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookSport)
        {
            if(this.name.equalsIgnoreCase(((FacebookSport)obj).getName()))
            {
                        return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    void setValuesFrom(FacebookSport read) {
        if(read!=null)
        {
            setId(read.getId());
        }
    }
}
