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
public class LinkedinPosition
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String linkedinId;
    protected String title;
    @Column(columnDefinition="TEXT")
    protected String summary;
    protected String isCurrent;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate startDate;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate endDate;
    @ManyToOne(cascade= CascadeType.PERSIST)
    protected LinkedinCompany company;
    
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
    
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getIsCurrent() {
        return isCurrent;
    }
    public void setIsCurrent(String isCurrent) {
        this.isCurrent = isCurrent;
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
    
    public LinkedinCompany getCompany() {
        return company;
    }
    public void setCompany(LinkedinCompany company) {
        this.company = company;
    }
    
    public String flatten(){
        String out = "";
        if(title != null) out += title + " ";
        if(summary != null) out += summary + " ";
        if(startDate != null) out += startDate.flatten() + " ";
        if(endDate != null) out += endDate.flatten() + " ";
        if(company != null) out += company.flatten() + " ";
        
        return out;
    }
}
