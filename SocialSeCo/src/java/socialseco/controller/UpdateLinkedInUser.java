/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.List;
import socialseco.dao.*;
import socialseco.model.linkedin.*;

/**
 *
 * @author damian
 */
public class UpdateLinkedInUser {
    
    public void updateUsers(List<LinkedinUser> users) {
        UserDAO dao = new UserDAO();
        
        for(LinkedinUser user_it: users){
            LinkedinUser read_user = dao.readLinkedinUsersByLinkedinId(user_it.getLinkedinId());
            
            if(read_user == null)
                read_user = user_it;
            else
                read_user.setValuesFrom(user_it);
            
            // Correct the languages to point to stored once (if some language is
            // some language is not already stored it will be when called to
            // getPersistedLanguage()
            List<LinkedinLanguage> languages = new ArrayList<LinkedinLanguage>();
            
            for(LinkedinLanguage language_it:user_it.getLanguages()) {
                LinkedinLanguage persisted_language = getPersistedLanguage(language_it);
                languages.add(persisted_language);
            }
            
            read_user.setLanguages(languages);
            dao.persist(read_user);
        }
    }
    
    private LinkedinLanguage getPersistedLanguage(LinkedinLanguage language) {
        LinkedinLanguageDAO languageDAO = new LinkedinLanguageDAO();
        List<LinkedinLanguage> read_languages = languageDAO.readByName(language.getName());
        LinkedinLanguage persisted_language;
        
        if(read_languages == null || read_languages.isEmpty()){
            persisted_language = languageDAO.persist(language);
        } else {
            persisted_language = read_languages.get(0);
        }
        return persisted_language;
    }
}
