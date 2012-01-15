/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.linkedin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import socialseco.model.linkedin.LinkedinDate;
import socialseco.model.linkedin.LinkedinEducation;
import socialseco.model.linkedin.LinkedinLanguage;
import socialseco.model.linkedin.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserExtractor {
    private static final String PROTECTED_RESOURCE_URL =
            "http://api.linkedin.com/v1/people/~/connections:(id,first-name,last-name,languages,educations)";
    
    private LinkedInAuthenticator authenticator;
    
    public UserExtractor(LinkedInAuthenticator authenticator){
        this.authenticator = authenticator;
    }
    
    public List<LinkedinUser> extractUsers()
            throws ParserConfigurationException, UnsupportedEncodingException,
                    SAXException, IOException {
        List<LinkedinUser> ret = new ArrayList<LinkedinUser>();
        LinkedinUser user;
        
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        oauthRequest = authenticator.sign(oauthRequest);
        org.scribe.model.Response oauthResponse = oauthRequest.send();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        InputStream is = new ByteArrayInputStream(oauthResponse.getBody().getBytes("UTF-8"));
        System.out.println(is.toString());
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        NodeList personList = doc.getElementsByTagName("person");
        
        for (int temp=0; temp < personList.getLength(); temp++) {
            Node personNode = personList.item(temp);
            if (personNode.getNodeType() == Node.ELEMENT_NODE) {
                Element personElement = (Element) personNode;
                
                // LinkedinId, Name, Surname and Industry
                String id = getTagValue("id", personElement);
                String first_name = getTagValue("first-name", personElement);
                String last_name = getTagValue("last-name", personElement);
                String industry = getTagValue("industry", personElement);
                
                // Languages
                List<LinkedinLanguage> languages = new ArrayList<LinkedinLanguage>();
                NodeList languageList = personElement.getElementsByTagName("language");
                for (int tempLang=0; tempLang < languageList.getLength(); tempLang++) {
                    Node languageNode = languageList.item(tempLang);
                    if (languageNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element languageElement = (Element) languageNode;
                        
                        String languageId = getTagValue("id", languageElement);
                        
                        NodeList language_language = languageElement.getElementsByTagName("language");
                        if (language_language == null) continue;
                        
                        Node language_language_item = language_language.item(0);
                        if (language_language_item == null) continue;
                        
                        if (language_language_item.getNodeType() == Node.ELEMENT_NODE) {
                            Element language_language_element = (Element) language_language_item;
                            String language_name = getTagValue("name", language_language_element);
                            
                            LinkedinLanguage language = new LinkedinLanguage();
                            language.setLinkedinId(languageId);
                            language.setName(language_name);
                            languages.add(language);
                        }
                    }
                }
                
                // Education
                List<LinkedinEducation> educations = new ArrayList<LinkedinEducation>();
                NodeList educationList = personElement.getElementsByTagName("education");
                for (int tempLang=0; tempLang < educationList.getLength(); tempLang++) {
                    Node educationNode = educationList.item(tempLang);
                    if (educationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element educationElement = (Element) educationNode;
                        
                        String educationId = getTagValue("id", educationElement);
                        
                        String schoolName = getTagValue("school-name", educationElement);
                        String fieldOfStudy = getTagValue("field-of-study", educationElement);
                        String degree = getTagValue("degree", educationElement);
                        String activities = getTagValue("activities", educationElement);
                        String notes = getTagValue("notes", educationElement);
                        
                        LinkedinEducation education = new LinkedinEducation();
                        education.setLinkedinId(educationId);
                        education.setSchoolName(schoolName);
                        education.setFieldOfStudy(fieldOfStudy);
                        education.setDegree(degree);
                        education.setActivities(activities);
                        education.setNotes(notes);
                        
                        NodeList startDateList = educationElement.getElementsByTagName("start-date");
                        if(startDateList.getLength() > 0){
                            Node startDateNode = startDateList.item(0);
                            if (startDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element startDateElement = (Element) startDateNode;
                                String year = getTagValue("year", startDateElement);
                                String month = getTagValue("month", startDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate startDate = new LinkedinDate();
                                    startDate.setYear(year);
                                    if(month != null && !month.isEmpty())
                                        startDate.setMonth(month);
                                    education.setStartDate(startDate);
                                }
                            }
                        }
                        
                        educations.add(education);
                    }
                }
                
                user = new LinkedinUser();
                user.setLinkedinId(id);
                user.setName(first_name);
                user.setSurname(last_name);
                user.setIndustry(industry);
                user.setLanguages(languages);
                user.setEducations(educations);
                
                ret.add(user);
            }
        }
        
        return ret;
    }
    
    public String extractUsers2()
            throws ParserConfigurationException, UnsupportedEncodingException,
                    SAXException, IOException {
        List<LinkedinUser> ret = new ArrayList<LinkedinUser>();
        LinkedinUser user;
        
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        oauthRequest = authenticator.sign(oauthRequest);
        org.scribe.model.Response oauthResponse = oauthRequest.send();
        
        return oauthResponse.getBody();
    }
                    
    private String getTagValue(String tag, Element element) {
        NodeList elementByTag = element.getElementsByTagName(tag);
        if (elementByTag == null) return null;
        
        Node item_0 = elementByTag.item(0);
        if (item_0 == null) return null;
	
        NodeList nlList = item_0.getChildNodes();
        if(nlList == null || nlList.getLength() == 0) return null;
        Node nValue = (Node) nlList.item(0);
	if(nValue == null) return null;
        return nValue.getNodeValue();
    }
}
