/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.facebook.Json;

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
    private String bio;
    private String gender;
    private String religion;

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
            
    public class Hometown
    {
        private String id;
        private String name;

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }
    
    public class Location
    {
        private String id;
        private String name;

        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
    }

    
    public class Work
    {
        private Employer employer;
        private Location location;

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
        
        public class Employer
        {
            private String id;
            private String name;

            /**
             * @return the id
             */
            public String getId() {
                return id;
            }

            /**
             * @param id the id to set
             */
            public void setId(String id) {
                this.id = id;
            }

            /**
             * @return the name
             */
            public String getName() {
                return name;
            }

            /**
             * @param name the name to set
             */
            public void setName(String name) {
                this.name = name;
            }
        }
    }
    

    
}
