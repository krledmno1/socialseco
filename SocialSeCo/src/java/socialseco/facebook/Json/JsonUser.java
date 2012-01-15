 /* To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.facebook.Json;

import socialseco.facebook.Json.Properties.*;


/**
 *
 * @author krle
 */
public class JsonUser extends JsonUserSimple {
    
    private String first_name;
    private String last_name;
    private String link;
    private String username;
    private Hometown hometown;
    private Location location;
    private Work [] work; 
    private Language [] languages;
    private String bio;
    private String gender;
    private String religion;
    private Education [] education;
    private Sports [] sports;
    private JsonActivities act;
    private JsonGroups gro;
    private JsonInterests inte;
    private JsonLikes like;
    private JsonMovies mov;
    private JsonMusic mus;
    private JsonTelevision tel;
    private JsonGames gam;
    private JsonBooks book;
    
    public JsonUser() {
    }

    /**
     * @return the mov
     */
    public JsonMovies getMov() {
        return mov;
    }

    /**
     * @param mov the mov to set
     */
    public void setMov(JsonMovies mov) {
        this.mov = mov;
    }

    /**
     * @return the mus
     */
    public JsonMusic getMus() {
        return mus;
    }

    /**
     * @param mus the mus to set
     */
    public void setMus(JsonMusic mus) {
        this.mus = mus;
    }

    /**
     * @return the tel
     */
    public JsonTelevision getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(JsonTelevision tel) {
        this.tel = tel;
    }

    /**
     * @return the gam
     */
    public JsonGames getGam() {
        return gam;
    }

    /**
     * @param gam the gam to set
     */
    public void setGam(JsonGames gam) {
        this.gam = gam;
    }

    /**
     * @return the book
     */
    public JsonBooks getBook() {
        return book;
    }

    /**
     * @param book the book to set
     */
    public void setBook(JsonBooks book) {
        this.book = book;
    }

    /**
     * @return the languages
     */
    public Language[] getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(Language[] languages) {
        this.languages = languages;
    }
   
   
 public class Language extends JsonEntity
 {

        public Language() {
        }
     
 }
    
    
    
   
    public class Hometown extends JsonEntity
    {

        public Hometown() {
        }
        
    }
    
    public class Location extends JsonEntity
    {

        public Location() {
        }
       
    }

    public class Sports extends JsonEntity
    {

        public Sports() {
        }
        
    }
    
    public class Work
    {

        public Work() {
        }
        
        private Employer employer;
        private Location location;
        private Position position;
        /**
         * @return the employer
         */
        public Employer getEmployer() {
            return employer;
        }

        /**
         * @param employer the employer to set
         */
        public void setEmployer(Employer employer) {
            this.employer = employer;
        }

        /**
         * @return the location
         */
        public Location getLocation() {
            return location;
        }

        /**
         * @param location the location to set
         */
        public void setLocation(Location location) {
            this.location = location;
        }

        /**
         * @return the position
         */
        public Position getPosition() {
            return position;
        }

        /**
         * @param position the position to set
         */
        public void setPosition(Position position) {
            this.position = position;
        }
        
        public class Employer extends JsonEntity
        {

            public Employer() {
            }
          
        }
        public class Position extends JsonEntity
        {
            
        }
    }

    public class Education
    {
        private School school;
        private String type;
        private Concentration [] concentration;

        public Education() {
        }

        
        /**
         * @return the school
         */
        public School getSchool() {
            return school;
        }

        /**
         * @param school the school to set
         */
        public void setSchool(School school) {
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
         * @return the concentration
         */
        public Concentration[] getConcentration() {
            return concentration;
        }

        /**
         * @param concentration the concentration to set
         */
        public void setConcentration(Concentration[] concentration) {
            this.concentration = concentration;
        }

        /**
         * @return the concentration
         */
        
        
        public class School extends JsonEntity
        {

            public School() {
            }
            
        }
        
        public class Concentration extends JsonEntity
        {

            public Concentration() {
            }
                    
        }
    }

    
    
    
    
    /**
     * @return the first_name
     */
    public String getFirst_name() {
        return first_name;
    }

    /**
     * @param first_name the first_name to set
     */
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    /**
     * @return the last_name
     */
    public String getLast_name() {
        return last_name;
    }

    /**
     * @param last_name the last_name to set
     */
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the hometown
     */
    public Hometown getHometown() {
        return hometown;
    }

    /**
     * @param hometown the hometown to set
     */
    public void setHometown(Hometown hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * @return the work
     */
    public Work[] getWork() {
        return work;
    }

    /**
     * @param work the work to set
     */
    public void setWork(Work[] work) {
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
    public Education[] getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(Education[] education) {
        this.education = education;
    }
    
     /**
     * @return the sports
     */
     public Sports[] getSports() {
        return sports;
    }

    /**
     * @param sports the sports to set
     */
    public void setSports(Sports[] sports) {
        this.sports = sports;
    }

    /**
     * @return the act
     */
    public JsonActivities getAct() {
        return act;
    }

    /**
     * @param act the act to set
     */
    public void setAct(JsonActivities act) {
        this.act = act;
    }

    /**
     * @return the gro
     */
    public JsonGroups getGro() {
        return gro;
    }

    /**
     * @param gro the gro to set
     */
    public void setGro(JsonGroups gro) {
        this.gro = gro;
    }

    /**
     * @return the inte
     */
    public JsonInterests getInte() {
        return inte;
    }

    /**
     * @param inte the inte to set
     */
    public void setInte(JsonInterests inte) {
        this.inte = inte;
    }

    /**
     * @return the like
     */
    public JsonLikes getLike() {
        return like;
    }

    /**
     * @param like the like to set
     */
    public void setLike(JsonLikes like) {
        this.like = like;
    }

}