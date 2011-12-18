/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model;

import javax.persistence.Entity;

/**
 *
 * @author damian
 */
@Entity
public class LinkedinUser
        extends User {

    protected String linkedinId;
    protected String industry;

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
    
    @Override
    public void setValuesFrom(User user){
        super.setValuesFrom(user);
        if (user instanceof LinkedinUser){
            LinkedinUser linkedinUser = (LinkedinUser)user;
            setIndustry(linkedinUser.getIndustry());
        }
    }
}
