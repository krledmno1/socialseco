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
            
            // The same is done for the companies in the positions.
            for(LinkedinPosition position_it:user_it.getPositions()){
                if(position_it.getCompany() != null){
                    LinkedinCompany persisted_company = getPersistedCompany(position_it.getCompany());
                    position_it.setCompany(persisted_company);
                }
            }
            
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
    
    private LinkedinCompany getPersistedCompany(LinkedinCompany company) {
        LinkedinCompanyDAO companyDAO = new LinkedinCompanyDAO();
        List<LinkedinCompany> read_companies = companyDAO.readByLinkedinId(company.getLinkedinId());
        LinkedinCompany persisted_company;
        
        if(read_companies == null || read_companies.isEmpty()){
            persisted_company = companyDAO.persist(company);
        } else {
            persisted_company = read_companies.get(0);
        }
        return persisted_company;
    }
}
