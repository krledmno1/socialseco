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
import socialseco.model.linkedin.*;

/**
 *
 * @author damian
 */
public class UserExtractor {
    private static final String PROTECTED_RESOURCE_URL =
            "http://api.linkedin.com/v1/people/~/connections:(id,first-name,last-name,headline,location,industry,summary,associations,honors,languages,educations,positions,publications,patents,certifications,skills)";
    
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
                String headline = getTagValue("headline", personElement);
                String industry = getTagValue("industry", personElement);
                String summary = getTagValue("summary", personElement);
                String associations = getTagValue("associations", personElement);
                String honors = getTagValue("honors", personElement);
                String location = null;
                String locationContryCode = null;
                
                NodeList locationList = personElement.getElementsByTagName("location");
                if(locationList.getLength() > 0){
                    Node locationNode = locationList.item(0);
                    if (locationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element locationElement = (Element) locationNode;
                        location = getTagValue("name", locationElement);
                        
                        NodeList countryList = locationElement.getElementsByTagName("country");
                        if(countryList.getLength() > 0){
                            Node countryNode = countryList.item(0);
                            if (countryNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element countryElement = (Element) countryNode;
                                locationContryCode = getTagValue("code", countryElement);
                            }
                        }
                    }
                }
                
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
                                String day = getTagValue("day", startDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate startDate = new LinkedinDate();
                                    startDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        startDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            startDate.setDay(day);
                                    }
                                    education.setStartDate(startDate);
                                }
                            }
                        }
                        
                        NodeList endDateList = educationElement.getElementsByTagName("end-date");
                        if(endDateList.getLength() > 0){
                            Node endDateNode = endDateList.item(0);
                            if (endDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element endDateElement = (Element) endDateNode;
                                String year = getTagValue("year", endDateElement);
                                String month = getTagValue("month", endDateElement);
                                String day = getTagValue("day", endDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate endDate = new LinkedinDate();
                                    endDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        endDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            endDate.setDay(day);
                                    }
                                    education.setEndDate(endDate);
                                }
                            }
                        }
                        
                        educations.add(education);
                    }
                }
                
                // Position
                List<LinkedinPosition> positions = new ArrayList<LinkedinPosition>();
                NodeList positionList = personElement.getElementsByTagName("position");
                for (int idx=0; idx < positionList.getLength(); idx++) {
                    Node positionNode = positionList.item(idx);
                    if (positionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element positionElement = (Element) positionNode;
                        
                        String positionId = getTagValue("id", positionElement);
                        
                        String title = getTagValue("title", positionElement);
                        String position_summary = getTagValue("summary", positionElement);
                        String isCurrent = getTagValue("is-current", positionElement);
                        
                        LinkedinPosition position = new LinkedinPosition();
                        position.setLinkedinId(positionId);
                        position.setTitle(title);
                        position.setSummary(position_summary);
                        position.setIsCurrent(isCurrent);
                        
                        NodeList startDateList = positionElement.getElementsByTagName("start-date");
                        if(startDateList.getLength() > 0){
                            Node startDateNode = startDateList.item(0);
                            if (startDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element startDateElement = (Element) startDateNode;
                                String year = getTagValue("year", startDateElement);
                                String month = getTagValue("month", startDateElement);
                                String day = getTagValue("day", startDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate startDate = new LinkedinDate();
                                    startDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        startDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            startDate.setDay(day);
                                    }
                                    position.setStartDate(startDate);
                                }
                            }
                        }
                        
                        NodeList endDateList = positionElement.getElementsByTagName("end-date");
                        if(endDateList.getLength() > 0){
                            Node endDateNode = endDateList.item(0);
                            if (endDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element endDateElement = (Element) endDateNode;
                                String year = getTagValue("year", endDateElement);
                                String month = getTagValue("month", endDateElement);
                                String day = getTagValue("day", endDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate endDate = new LinkedinDate();
                                    endDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        endDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            endDate.setDay(day);
                                    }
                                    position.setEndDate(endDate);
                                }
                            }
                        }
                        
                        NodeList companyList = positionElement.getElementsByTagName("company");
                        if(companyList.getLength() > 0){
                            Node companyNode = companyList.item(0);
                            if (companyNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element companyElement = (Element) companyNode;
                                String company_id = getTagValue("id", companyElement);
                                String company_name = getTagValue("name", companyElement);
                                String company_type = getTagValue("type", companyElement);
                                String company_size = getTagValue("size", companyElement);
                                String company_industry = getTagValue("industry", companyElement);
                                String company_ticker = getTagValue("ticker", companyElement);
                                
                                LinkedinCompany company = new LinkedinCompany();
                                company.setLinkedinId(company_id);
                                company.setName(company_name);
                                company.setType(company_type);
                                company.setSize(company_size);
                                company.setIndustry(company_industry);
                                company.setTicker(company_ticker);
                                
                                position.setCompany(company);
                            }
                        }
                        
                        positions.add(position);
                    }
                }
                
                // Publications
                List<LinkedinPublication> publications = new ArrayList<LinkedinPublication>();
                NodeList publicationList = personElement.getElementsByTagName("publication");
                for (int idx=0; idx < publicationList.getLength(); idx++) {
                    Node publicationNode = publicationList.item(idx);
                    if (publicationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element publicationElement = (Element) publicationNode;
                        
                        String publicationId = getTagValue("id", publicationElement);
                        
                        String publicationTitle = getTagValue("title", publicationElement);
                        String publicationPublisher = getTagValue("publisher", publicationElement);
                        String publicationUrl = getTagValue("url", publicationElement);
                        String publicationSummary = getTagValue("summary", publicationElement);
                        
                        LinkedinPublication publication = new LinkedinPublication();
                        publication.setLinkedinId(publicationId);
                        publication.setTitle(publicationTitle);
                        publication.setPublisher(publicationPublisher);
                        publication.setUrl(publicationUrl);
                        publication.setSummary(publicationSummary);
                        
                        NodeList dateList = publicationElement.getElementsByTagName("date");
                        if(dateList.getLength() > 0){
                            Node dateNode = dateList.item(0);
                            if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element dateElement = (Element) dateNode;
                                String year = getTagValue("year", dateElement);
                                String month = getTagValue("month", dateElement);
                                String day = getTagValue("day", dateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate date = new LinkedinDate();
                                    date.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        date.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            date.setDay(day);
                                    }
                                    publication.setDate(date);
                                }
                            }
                        }
                        
                        publications.add(publication);
                    }
                }
                
                // Patents
                List<LinkedinPatent> patents = new ArrayList<LinkedinPatent>();
                NodeList patentList = personElement.getElementsByTagName("patent");
                for (int idx=0; idx < patentList.getLength(); idx++) {
                    Node patentNode = patentList.item(idx);
                    if (patentNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element patentElement = (Element) patentNode;
                        
                        String patentId = getTagValue("id", patentElement);
                        
                        String patentTitle = getTagValue("title", patentElement);
                        String patentSummary = getTagValue("summary", patentElement);
                        String patentNumber = getTagValue("number", patentElement);
                        String patentUrl = getTagValue("url", patentElement);
                        
                        LinkedinPatent patent = new LinkedinPatent();
                        patent.setLinkedinId(patentId);
                        patent.setTitle(patentTitle);
                        patent.setSummary(patentSummary);
                        patent.setNumber(patentNumber);
                        patent.setUrl(patentUrl);
                        
                        NodeList dateList = patentElement.getElementsByTagName("date");
                        if(dateList.getLength() > 0){
                            Node dateNode = dateList.item(0);
                            if (dateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element dateElement = (Element) dateNode;
                                String year = getTagValue("year", dateElement);
                                String month = getTagValue("month", dateElement);
                                String day = getTagValue("day", dateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate date = new LinkedinDate();
                                    date.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        date.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            date.setDay(day);
                                    }
                                    patent.setDate(date);
                                }
                            }
                        }
                        
                        patents.add(patent);
                    }
                }
                
                // Certifications
                List<LinkedinCertification> certifications = new ArrayList<LinkedinCertification>();
                NodeList certificationList = personElement.getElementsByTagName("certification");
                for (int idx=0; idx < certificationList.getLength(); idx++) {
                    Node certificationNode = certificationList.item(idx);
                    if (certificationNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element certificationElement = (Element) certificationNode;
                        
                        String certificationId = getTagValue("id", certificationElement);
                        
                        String certificationName = getTagValue("name", certificationElement);
                        String certificationNumber = getTagValue("number", certificationElement);
                        
                        LinkedinCertification certification = new LinkedinCertification();
                        certification.setLinkedinId(certificationId);
                        certification.setName(certificationName);
                        certification.setNumber(certificationNumber);
                        
                        NodeList startDateList = certificationElement.getElementsByTagName("start-date");
                        if(startDateList.getLength() > 0){
                            Node startDateNode = startDateList.item(0);
                            if (startDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element startDateElement = (Element) startDateNode;
                                String year = getTagValue("year", startDateElement);
                                String month = getTagValue("month", startDateElement);
                                String day = getTagValue("day", startDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate startDate = new LinkedinDate();
                                    startDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        startDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            startDate.setDay(day);
                                    }
                                    certification.setStartDate(startDate);
                                }
                            }
                        }
                        
                        NodeList endDateList = certificationElement.getElementsByTagName("end-date");
                        if(endDateList.getLength() > 0){
                            Node endDateNode = endDateList.item(0);
                            if (endDateNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element endDateElement = (Element) endDateNode;
                                String year = getTagValue("year", endDateElement);
                                String month = getTagValue("month", endDateElement);
                                String day = getTagValue("day", endDateElement);
                                
                                if(year != null && !year.isEmpty()){
                                    LinkedinDate endDate = new LinkedinDate();
                                    endDate.setYear(year);
                                    if(month != null && !month.isEmpty()){
                                        endDate.setMonth(month);
                                        if(day != null && !day.isEmpty())
                                            endDate.setDay(day);
                                    }
                                    certification.setEndDate(endDate);
                                }
                            }
                        }
                        
                        certifications.add(certification);
                    }
                }
                
                // Skills
                List<LinkedinSkill> skills = new ArrayList<LinkedinSkill>();
                NodeList skillList = personElement.getElementsByTagName("skill");
                for (int idx=0; idx < skillList.getLength(); idx++) {
                    Node skillNode = skillList.item(idx);
                    if (skillNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element skillElement = (Element) skillNode;
                        
                        String skillId = getTagValue("id", skillElement);
                        
                        LinkedinSkill skill = new LinkedinSkill();
                        skill.setLinkedinId(skillId);
                        
                        NodeList subskillList = skillElement.getElementsByTagName("skill");
                        if(subskillList.getLength() > 0){
                            Node subskillNode = subskillList.item(0);
                            if (subskillNode.getNodeType() == Node.ELEMENT_NODE) {
                                Element subskillElement = (Element) subskillNode;
                                String skillName = getTagValue("name", subskillElement);
                                if (skillName == null || skillName.isEmpty()) continue;
                                skill.setName(skillName);
                            }
                        }
                        
                        skills.add(skill);
                    }
                }
                
                user = new LinkedinUser();
                user.setLinkedinId(id);
                user.setName(first_name);
                user.setSurname(last_name);
                user.setHeadline(headline);
                user.setLocation(location);
                user.setLocationCountryCode(locationContryCode);
                user.setIndustry(industry);
                user.setSummary(summary);
                user.setAssociations(associations);
                user.setHonors(honors);
                user.setLanguages(languages);
                user.setEducations(educations);
                user.setPositions(positions);
                user.setPublications(publications);
                user.setPatents(patents);
                user.setCertifications(certifications);
                user.setSkills(skills);
                
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
