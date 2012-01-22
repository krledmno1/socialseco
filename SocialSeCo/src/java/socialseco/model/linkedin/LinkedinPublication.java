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
public class LinkedinPublication
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String linkedinId;
    @Column(length=500)
    protected String title;
    @Column(length=500)
    protected String publisher;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate date;
    @Column(length=1000)
    protected String url;
    @Column(columnDefinition="TEXT")
    protected String summary;
    
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
    
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    
    public LinkedinDate getDate() {
        return date;
    }
    public void setDate(LinkedinDate date) {
        this.date = date;
    }
    
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
}
