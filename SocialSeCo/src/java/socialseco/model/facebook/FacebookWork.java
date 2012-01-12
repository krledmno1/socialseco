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
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames={"employer", "position"})
})
public class FacebookWork implements Serializable {
    
    private String employer;
    private String position;
    @Id @GeneratedValue
    private Long id;

    
    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof FacebookWork)
        {
            if(this.employer.equals(((FacebookWork)obj).getEmployer())  &&this.position.equals(((FacebookWork)obj).getPosition()))
            {
                        return true;
            }
        }
        return false;
    }
    
    
    public FacebookWork() {
    }

    /**
     * @return the employer
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * @param employer the employer to set
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    void setValuesFrom(FacebookWork read_work) {
        if(read_work!=null)
            setId(read_work.getId());
    }
        
}
