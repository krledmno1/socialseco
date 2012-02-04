/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.List;
import socialseco.dao.UserDAO;
import socialseco.dao.UserSimilarityDAO;
import socialseco.model.User;
import socialseco.model.UserSimilarity;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserSimilarityController {
    
    private int similarityBoundary = 75;
    
    public List<User> processAllUsers(){
        UserSimilarityDAO similarityDAO = new UserSimilarityDAO();
        UserDAO userDAO = new UserDAO();
        userDAO.beginConversation();
        List<User> similarUsers = new ArrayList<User>();
        
        
        for(FacebookUser fbkUser:userDAO.readAllFacebookUsers_without_transaction()) {
            for(LinkedinUser linUser:userDAO.readAllLinkedinUsersWithoutTransaction()) {
                boolean similar = processUsers(linUser, fbkUser, similarityDAO);
                if(similar) similarUsers.add(fbkUser);
            }
        }
        userDAO.endConversation();
        return similarUsers;
    }
    
    public boolean processUsers(LinkedinUser linUser, FacebookUser fbkUser,
                                UserSimilarityDAO dao) {
        int similarity = getSimilarity(linUser, fbkUser);
        if(similarity >= similarityBoundary) {
            UserSimilarity userSimilarity = dao.readByUsers(linUser, fbkUser);
            
            if(userSimilarity == null){
                userSimilarity = new UserSimilarity();
                userSimilarity.setFacebookUser(fbkUser);
                userSimilarity.setLinkedinUser(linUser);
            }
            
            userSimilarity.setSimilarity(similarity);
            dao.persistWithoutTransaction(userSimilarity);
            return true;
        } else {
            return false;
        }
        
    }
    
    // The returned value is between 0 and 100
    public int getSimilarity(LinkedinUser linUser, FacebookUser fbkUser){
        int similarity = 0;
        
        if(linUser.getName() != null && fbkUser.getName() != null) {
            if(linUser.getName().equals(fbkUser.getName())) {
                if(linUser.getSurname().equals(fbkUser.getSurname())) {
                    similarity = 75;
                }
            }
        }
        return similarity;
    }
    
}
