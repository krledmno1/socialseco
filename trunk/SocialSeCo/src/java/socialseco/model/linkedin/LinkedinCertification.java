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
public class LinkedinCertification
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String linkedinId;
    @Column(length=500)
    protected String name;
    @Column(length=500)
    protected String number;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate startDate;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate endDate;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getLinkedinId() {
        return linkedinId;
    }
    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    
    public LinkedinDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LinkedinDate startDate) {
        this.startDate = startDate;
    }
    
    public LinkedinDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LinkedinDate endDate) {
        this.endDate = endDate;
    }
    
}
