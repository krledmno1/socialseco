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
public class FacebookConcentration implements Serializable {
    @Id //@GeneratedValue
    private Long id;
    
    
    
    
     private String name;
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookBook)
        {
            if(this.name.equals(((FacebookBook)obj).getName()))
            {
                        return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    void setValues(FacebookConcentration read_con) {
        if(read_con!=null)
        {
            setId(read_con.getId());
        }
        
    }
    
}
