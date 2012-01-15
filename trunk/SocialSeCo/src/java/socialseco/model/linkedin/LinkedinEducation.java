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
public class LinkedinEducation
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String linkedinId;
    protected String schoolName;
    protected String fieldOfStudy;
    protected String degree;
    @Column(columnDefinition="TEXT")
    protected String activities;
    @Column(columnDefinition="TEXT")
    protected String notes;
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
    
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
    
    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        this.fieldOfStudy = fieldOfStudy;
    }
    
    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    
    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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
