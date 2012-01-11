/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.facebook;

import java.util.ArrayList;
import java.util.List;
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
    private String fullName;
    private String hometown;
    private String location;
    private List<Work> work;
    private String bio;
    private String gender;
    private String religion;
    private List<Education> education;
    private List<String> sports;
    private List<String> activities;
    private List<String> groups;
    private List<String> interests;
    private List<String> likes;
    private List<String> movies;
    private List<String> music;
    private List<String> television;
    private List<String> games;
    private List<String> books;

    public FacebookUser() {
        
        work = new ArrayList<Work>();
        education = new ArrayList<Education>();
        sports = new ArrayList<String>();
        activities = new ArrayList<String>();
        groups = new ArrayList<String>();
        interests = new ArrayList<String>();
        likes = new ArrayList<String>();
        movies = new ArrayList<String>();
        music = new ArrayList<String>();
        television = new ArrayList<String>();
        games = new ArrayList<String>();
        books = new ArrayList<String>();
          
    }

    /**
     * @return the fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * @param fullName the fullName to set
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the work
     */
    public List<Work> getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(List<Work> work) {
        this.work = work;
    }

    /**
     * @return the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * @param bio the bio to set
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the religion
     */
    public String getReligion() {
        return religion;
    }

    /**
     * @param religion the religion to set
     */
    public void setReligion(String religion) {
        this.religion = religion;
    }

    /**
     * @return the education
     */
    public List<Education> getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(List<Education> education) {
        this.education = education;
    }

    /**
     * @return the sports
     */
    public List<String> getSports() {
        return sports;
    }

    /**
     * @param sports the sports to set
     */
    public void setSports(List<String> sports) {
        this.sports = sports;
    }

    /**
     * @return the activities
     */
    public List<String> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<String> activities) {
        this.activities = activities;
    }

    /**
     * @return the groups
     */
    public List<String> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    /**
     * @return the interests
     */
    public List<String> getInterests() {
        return interests;
    }

    /**
     * @param interests the interests to set
     */
    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    /**
     * @return the likes
     */
    public List<String> getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(List<String> likes) {
        this.likes = likes;
    }

    /**
     * @return the movies
     */
    public List<String> getMovies() {
        return movies;
    }

    /**
     * @param movies the movies to set
     */
    public void setMovies(List<String> movies) {
        this.movies = movies;
    }

    /**
     * @return the music
     */
    public List<String> getMusic() {
        return music;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(List<String> music) {
        this.music = music;
    }

    /**
     * @return the television
     */
    public List<String> getTelevision() {
        return television;
    }

    /**
     * @param television the television to set
     */
    public void setTelevision(List<String> television) {
        this.television = television;
    }

    /**
     * @return the games
     */
    public List<String> getGames() {
        return games;
    }

    /**
     * @param games the games to set
     */
    public void setGames(List<String> games) {
        this.games = games;
    }

    /**
     * @return the books
     */
    public List<String> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<String> books) {
        this.books = books;
    }
    
    
    @Entity
    public class Education
    {
        
        private String school;
        private String type;
        private List<String> conecntration;

        public Education() {
            conecntration = new ArrayList<String>();
        }

        /**
         * @return the school
         */
        public String getSchool() {
            return school;
        }

        /**
         * @param school the school to set
         */
        public void setSchool(String school) {
            this.school = school;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * @return the conecntration
         */
        public List<String> getConecntration() {
            return conecntration;
        }

        /**
         * @param conecntration the conecntration to set
         */
        public void setConecntration(List<String> conecntration) {
            this.conecntration = conecntration;
        }

       
        
    }
    @Entity
    public class Work
    {
        private String employer;
        private String position;

        public Work() {
        }

        /**
         * @return the employer
         */
        public String getEmployer() {
            return employer;
        }

        /**
         * @param employer the employer to set
         */
        public void setEmployer(String employer) {
            this.employer = employer;
        }

        /**
         * @return the position
         */
        public String getPosition() {
            return position;
        }

        /**
         * @param position the position to set
         */
        public void setPosition(String position) {
            this.position = position;
        }
        
        
    }

    
    public String getFacebookId() {
        return facebookId;
    }
    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }
    
    
    
    @Override
    public void setValuesFrom(User u){
        super.setValuesFrom(u);
        if (u instanceof FacebookUser){
            FacebookUser user = (FacebookUser)u;
            
            //set-get all atributes
            setFacebookId(user.getFacebookId());
            setFullName(user.getFullName());
            setHometown(user.getHometown());
            setLocation(user.getLocation());
            setBio(user.getBio());
            setGender(user.getGender());
            setReligion(user.getReligion());
            
            setWork(user.getWork());
            setEducation(user.getEducation());
            setSports(user.getSports());
            setActivities(user.getActivities());
            setGroups(user.getGroups());
            setInterests(user.getInterests());
            setLikes(user.getLikes());
            setMovies(user.getMovies());
            setMusic(user.getMusic());
            setTelevision(user.getTelevision());
            setGames(user.getGames());
            setBooks(user.getBooks());
            
            
        }
    }
}
