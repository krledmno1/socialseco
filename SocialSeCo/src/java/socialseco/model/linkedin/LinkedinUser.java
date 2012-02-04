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

    public static int fieldNum = 14;
    protected static String[] fields =
        {"headline", "location", "locationCountryCode", "industry",
         "summary", "associations", "honors", "languages", "educations",
         "positions", "publications", "patents", "certifications",
         "skills"};
    
    protected String linkedinId;
    @Column(length=500)
    protected String headline;
    protected String location;
    protected String locationCountryCode;
    @Column(length=500)
    protected String industry;
    @Column(columnDefinition="TEXT",length=5000)
    protected String summary;
    @Column(columnDefinition="TEXT")
    protected String associations;
    @Column(columnDefinition="TEXT")
    protected String honors;
    @ManyToMany(cascade=CascadeType.ALL)
    protected List<LinkedinLanguage> languages;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinEducation> educations;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinPosition> positions;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinPublication> publications;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinPatent> patents;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinCertification> certifications;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinRecommendation> recommendations;
    @OneToMany(cascade=CascadeType.ALL)
    protected List<LinkedinSkill> skills;
    
    
    public LinkedinUser(){
        languages = new ArrayList<LinkedinLanguage>();
    }
    
    public String getLinkedinId() {
        return linkedinId;
    }
    public void setLinkedinId(String linkedinId) {
        this.linkedinId = linkedinId;
    }
    
    public String getHeadline() {
        return headline;
    }
    public void setHeadline(String headline) {
        this.headline = headline;
    }
    
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getLocationCountryCode() {
        return locationCountryCode;
    }
    public void setLocationCountryCode(String locationCountryCode) {
        this.locationCountryCode = locationCountryCode;
    }
    
    public String getIndustry() {
        return industry;
    }
    public void setIndustry(String industry) {
        this.industry = industry;
    }
    
    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String getAssociations() {
        return associations;
    }
    public void setAssociations(String associations) {
        this.associations = associations;
    }
    
    public String getHonors() {
        return honors;
    }
    public void setHonors(String honors) {
        this.honors = honors;
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
    
    public List<LinkedinEducation> getEducations(){
        return this.educations;
    }
    public void setEducations(List<LinkedinEducation> educations){
        this.educations = educations;
    }
    public void addEducation(LinkedinEducation education){
        this.educations.add(education);
    }
    
    public List<LinkedinPosition> getPositions(){
        return this.positions;
    }
    public void setPositions(List<LinkedinPosition> positions){
        this.positions = positions;
    }
    public void addPosition(LinkedinPosition position){
        this.positions.add(position);
    }
    
    public List<LinkedinPublication> getPublications(){
        return this.publications;
    }
    public void setPublications(List<LinkedinPublication> publications){
        this.publications = publications;
    }
    public void addPublication(LinkedinPublication publication){
        this.publications.add(publication);
    }
    
    public List<LinkedinPatent> getPatents(){
        return this.patents;
    }
    public void setPatents(List<LinkedinPatent> patents){
        this.patents = patents;
    }
    public void addPatent(LinkedinPatent patent){
        this.patents.add(patent);
    }
    
    public List<LinkedinCertification> getCertifications(){
        return this.certifications;
    }
    public void setCertifications(List<LinkedinCertification> certifications){
        this.certifications = certifications;
    }
    public void addCertification(LinkedinCertification certification){
        this.certifications.add(certification);
    }
    
    public List<LinkedinRecommendation> getRecommendations(){
        return this.recommendations;
    }
    public void setRecommendations(List<LinkedinRecommendation> recommendations){
        this.recommendations = recommendations;
    }
    public void addRecommendation(LinkedinRecommendation recommendation){
        this.recommendations.add(recommendation);
    }
    
    public List<LinkedinSkill> getSkills(){
        return this.skills;
    }
    public void setSkills(List<LinkedinSkill> skills){
        this.skills = skills;
    }
    public void addSkill(LinkedinSkill skill){
        this.skills.add(skill);
    }
    
    @Override
    public void setValuesFrom(User user){
        super.setValuesFrom(user);
        if (user instanceof LinkedinUser){
            LinkedinUser linkedinUser = (LinkedinUser)user;
            setLinkedinId(linkedinUser.getLinkedinId());
            setHeadline(linkedinUser.getHeadline());
            setIndustry(linkedinUser.getIndustry());
            setSummary(linkedinUser.getSummary());
            setAssociations(linkedinUser.getAssociations());
            setHonors(linkedinUser.getHonors());
            setLocation(linkedinUser.getLocation());
            setLocationCountryCode(linkedinUser.getLocationCountryCode());
            
            setLanguages(linkedinUser.getLanguages());
            setEducations(linkedinUser.getEducations());
            setPositions(linkedinUser.getPositions());
            setPublications(linkedinUser.getPublications());
            setPatents(linkedinUser.getPatents());
            setCertifications(linkedinUser.getCertifications());
            setRecommendations(linkedinUser.getRecommendations());
            setSkills(linkedinUser.getSkills());
        }
    }
    
    public static String[] getFields(){
        return fields;
    }
    
    public String flattenField(String field){
        if(field == null || field.isEmpty())
            return "";
        else if(field.equals("headline"))
            return flattenHeadline();
        else if(field.equals("location"))
            return flattenLocation();
        else if(field.equals("locationCountryCode"))
            return flattenLocationCountryCode();
        else if(field.equals("industry"))
            return flattenIndustry();
        else if(field.equals("summary"))
            return flattenSummary();
        else if(field.equals("associations"))
            return flattenAssociations();
        else if(field.equals("honors"))
            return flattenHonors();
        else if(field.equals("languages"))
            return flattenLanguages();
        else if(field.equals("educations"))
            return flattenEducations();
        else if(field.equals("positions"))
            return flattenPositions();
        else if(field.equals("publications"))
            return flattenPublications();
        else if(field.equals("patents"))
            return flattenPatents();
        else if(field.equals("certifications"))
            return flattenCertifications();
        else if(field.equals("skills"))
            return flattenSkills();
        else
            return "";
    }
    
    public String flattenHeadline(){
        return headline;
    }
    public String flattenLocation(){
        return location;
    }
    public String flattenLocationCountryCode(){
        return locationCountryCode;
    }
    public String flattenIndustry(){
        return industry;
    }
    public String flattenSummary(){
        return summary;
    }
    public String flattenAssociations(){
        return associations;
    }
    public String flattenHonors(){
        return honors;
    }
    public String flattenLanguages() {
        String out = "";
        for(LinkedinLanguage language:languages){
            if(language != null) out += language.flatten();
        }
        return out;
    }
    public String flattenEducations() {
        String out = "";
        for(LinkedinEducation education:educations){
            if(education != null) out += education.flatten();
        }
        return out;
    }
    public String flattenPositions() {
        String out = "";
        for(LinkedinPosition position:positions){
            if(position != null) out += position.flatten();
        }
        return out;
    }
    public String flattenPublications() {
        String out = "";
        for(LinkedinPublication publication:publications){
            if(publication != null) out += publication.flatten();
        }
        return out;
    }
    public String flattenPatents() {
        String out = "";
        for(LinkedinPatent patent:patents){
            if(patent != null) out += patent.flatten();
        }
        return out;
    }
    public String flattenCertifications() {
        String out = "";
        for(LinkedinCertification certification:certifications){
            if(certification != null) out += certification.flatten();
        }
        return out;
    }
    public String flattenSkills() {
        String out = "";
        for(LinkedinSkill skill:skills){
            if(skill != null) out += skill.flatten();
        }
        return out;
    }
}
