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
public class FacebookActivity implements Serializable {
    @Id //@GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   // @Column(unique=true)
    private String name;

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

    public void setValuesFrom(FacebookActivity read) {
        if(read!=null)
        {
            setId(read.getId());
        }
    }
        
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookActivity)
        {
            if(this.name.equals(((FacebookActivity)obj).getName()))
            {
                        return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
}
