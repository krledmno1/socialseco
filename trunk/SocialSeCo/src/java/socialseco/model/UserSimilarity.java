/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model;

import java.io.Serializable;
import javax.persistence.*;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author damian
 */
@Entity
public class UserSimilarity
    implements Serializable {
    
    @Id @GeneratedValue
    protected Long id;
    protected Integer similarity;
    
    @ManyToOne
    protected FacebookUser facebookUser;
    @ManyToOne
    protected LinkedinUser linkedinUser;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getSimilarity() {
        return similarity;
    }
    public void setSimilarity(Integer similarity) {
        this.similarity = similarity;
    }
    
    public FacebookUser getFacebookUser() {
        return facebookUser;
    }
    public void setFacebookUser(FacebookUser facebookUser) {
        this.facebookUser = facebookUser;
    }
    
    public LinkedinUser getLinkedinUser() {
        return linkedinUser;
    }
    public void setLinkedinUser(LinkedinUser linkedinUser) {
        this.linkedinUser = linkedinUser;
    }
}
