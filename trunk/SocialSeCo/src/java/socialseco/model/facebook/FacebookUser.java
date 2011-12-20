/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.facebook;

import javax.persistence.Entity;
import socialseco.model.User;

/**
 *
 * @author damian
 */
@Entity
public class FacebookUser
        extends User {

    protected String facebookId;
    
    public String getFacebookId() {
        return facebookId;
    }
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
    
}
