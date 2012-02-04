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
public class LinkedinPatent
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    
    protected String linkedinId;
    @Column(length=500)
    protected String title;
    @Column(columnDefinition="TEXT")
    protected String summary;
    protected Integer number;
    @OneToOne(cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    protected LinkedinDate date;
    @Column(length=1000)
    protected String url;
    
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
    
    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    public void setNumber(String number) {
        if(number == null || number.isEmpty()) return;
        this.number = Integer.parseInt(number);
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
    
    public String flatten(){
        String out = "";
        if(title != null) out += title + " ";
        if(summary != null) out += summary + " ";
        if(number != null) out += number + " ";
        if(date != null) out += date.flatten() + " ";
        if(url != null) out += url + " ";
        
        return out;
    }
}
