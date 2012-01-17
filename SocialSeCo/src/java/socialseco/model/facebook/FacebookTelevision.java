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
public class FacebookTelevision implements Serializable {
    @Id //@GeneratedValue
    private long Id;
//@Column(unique=true)
     private String name;

    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookTelevision)
        {
            if(this.name.equals(((FacebookTelevision)obj).getName()))
            {
                        return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
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

    /**
     * @return the Id
     */
    public long getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(long Id) {
        this.Id = Id;
    }

    void setValuesFrom(FacebookTelevision read) {
        if(read!=null)
        {
            setId(read.getId());
        }
    }
}
