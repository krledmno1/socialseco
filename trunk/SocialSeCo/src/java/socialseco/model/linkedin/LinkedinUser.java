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
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    protected List<LinkedinLanguage> languages;
    
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
    
    public List<LinkedinLanguage> getLanguages(){
        return this.languages;
    }
    public void setLanguages(List<LinkedinLanguage> languages){
        this.languages = languages;
    }
    public void addLanguage(LinkedinLanguage language){
        this.languages.add(language);
    }
    
    @Override
    public void setValuesFrom(User user){
        super.setValuesFrom(user);
        if (user instanceof LinkedinUser){
            LinkedinUser linkedinUser = (LinkedinUser)user;
            setIndustry(linkedinUser.getIndustry());
            setLanguages(linkedinUser.getLanguages());
        }
    }
}
