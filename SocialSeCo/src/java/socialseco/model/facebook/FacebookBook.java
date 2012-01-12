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
public class FacebookBook implements Serializable {
    @Id @GeneratedValue
    private Long id;
    
    @Column(unique=true)
    private String name;
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookBook)
        {
            if(this.name.equalsIgnoreCase(((FacebookBook)obj).getName()))
            {
                        return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + (this.name != null ? this.name.hashCode() : 0);
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    void setValuesFrom(FacebookBook read) {
        if(read!=null)
        {
            setId(read.getId());
        }
    }
}
