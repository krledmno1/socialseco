/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.List;
import socialseco.dao.UserDAO;
import socialseco.model.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserUpdater {
    
    public void updateUsers(List<LinkedinUser> users) {
        UserDAO dao = new UserDAO();
        
        for(LinkedinUser user_it: users){
            LinkedinUser read_user = dao.readLinkedinUsersByLinkedinId(user_it.getLinkedinId());
            
            if(read_user != null) {
                read_user.setValuesFrom(user_it);
                dao.persist(read_user);
            } else {
                dao.persist(user_it);
            }
        }
    }
}
