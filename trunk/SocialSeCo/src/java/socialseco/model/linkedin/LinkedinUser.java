/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.linkedin;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import socialseco.model.User;

/**
 *
 * @author damian
 */
@Entity
public class LinkedinUser
        extends User {

    protected String linkedinId;
    protected String industry;
    protected String headline;
    protected String summary;
    protected String honors;
    
    @ManyToMany(cascade=CascadeType.ALL)
    protected List<LinkedinLanguage> languages;
    
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinEducation> educations;
    
    public LinkedinUser(){
        languages = new ArrayList<LinkedinLanguage>();
    }
    
    public String getLinkedinId() {
        return linkedinId;
    }

    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }
    
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getHonors() {
        return honors;
    }
    public void setHonors(String honors) {
        this.honors = honors;
    }
    
    public List<LinkedinLanguage> getLanguages(){
        return this.languages;
    }
    public void setLanguages(List<LinkedinLanguage> languages){
        this.languages = languages;
    }
    public void addLanguage(LinkedinLanguage language){
        this.languages.add(language);
    }
    
    public List<LinkedinEducation> getEducations(){
        return this.educations;
    }
    public void setEducations(List<LinkedinEducation> educations){
        this.educations = educations;
    }
    public void addEducation(LinkedinEducation education){
        this.educations.add(education);
    }
    
    @Override
    public void setValuesFrom(User user){
        super.setValuesFrom(user);
        if (user instanceof LinkedinUser){
            LinkedinUser linkedinUser = (LinkedinUser)user;
            setLinkedinId(linkedinUser.getLinkedinId());
            setIndustry(linkedinUser.getIndustry());
            setHeadline(linkedinUser.getHeadline());
            setSummary(linkedinUser.getSummary());
            setHonors(linkedinUser.getHonors());
            
            setLanguages(linkedinUser.getLanguages());
            setEducations(linkedinUser.getEducations());
        }
    }
}
