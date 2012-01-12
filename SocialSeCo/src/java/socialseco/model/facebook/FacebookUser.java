/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.facebook;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import socialseco.dao.facebookProperties.*;
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
    
    @ManyToMany(cascade=CascadeType.ALL) 
    private List<FacebookWork> works;
    @Column(length=5000)
    private String bio;
    private String gender;
    private String religion;
    
    @ManyToMany(cascade=CascadeType.ALL) 
    //@GenericGenerator(name="uuid-gen", strategy = "uuid")
    //@CollectionId(columns = @Column(name = "COL_ID"), type = @Type(type = "string"), generator = "uuid-gen") 
    private List<FacebookEducation> education;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookSport> sports;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookActivity> activities;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookGroup> groups;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookInterest> interests;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookLike> likes;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookMovie> movies;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookMusic> music;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookTelevision> television;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookGame> games;
    
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookBook> books;

    
    public FacebookUser() {
        
        works = new ArrayList<FacebookWork>();
        education = new ArrayList<FacebookEducation>();
        sports = new ArrayList<FacebookSport>();
        activities = new ArrayList<FacebookActivity>();
        groups = new ArrayList<FacebookGroup>();
        interests = new ArrayList<FacebookInterest>();
        likes = new ArrayList<FacebookLike>();
        movies = new ArrayList<FacebookMovie>();
        music = new ArrayList<FacebookMusic>();
        television = new ArrayList<FacebookTelevision>();
        games = new ArrayList<FacebookGame>();
        books = new ArrayList<FacebookBook>();
          
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
    
            //remove duplicates
            Set<FacebookWork> w = new LinkedHashSet<FacebookWork>();
            w.addAll(user.getWorks());
            user.getWorks().clear();
            user.getWorks().addAll(w);
            
            //check if exists
            for(FacebookWork work: user.getWorks())
            {
                WorkDAO dao = new WorkDAO();
                FacebookWork read = dao.readFacebookWorkByEmployerAndPosition(work.getEmployer(), work.getPosition());
                work.setValuesFrom(read);
            }
            //set
            setWorks(user.getWorks());
            
            
            //set
            for(FacebookEducation edu: user.getEducation())
            {
                Set<FacebookConcentration> con = new LinkedHashSet<FacebookConcentration>();
                con.addAll(edu.getConecentration());
                edu.getConecentration().clear();
                edu.getConecentration().addAll(con);
                
                for(FacebookConcentration conc: edu.getConecentration())
                {
                    ConcentrationDAO dao = new ConcentrationDAO();
                    FacebookConcentration read = dao.readFacebookConcentrationByName(conc.getName());
                    conc.setValues(read);
                }
            }
            setEducation(user.getEducation());
            
            
            //remove duplicates
            Set<FacebookSport> s = new LinkedHashSet<FacebookSport>();
            s.addAll(user.getSports());
            user.getSports().clear();
            user.getSports().addAll(s);
            
            //check if exists
            for(FacebookSport spo: user.getSports())
            {
                SportDAO dao = new SportDAO();
                FacebookSport read = dao.readFacebookSportByName(spo.getName());
                spo.setValuesFrom(read);
            } 
            //set
            setSports(user.getSports());
            
            
            //remove duplicates
            Set<FacebookActivity> a = new LinkedHashSet<FacebookActivity>();
            a.addAll(user.getActivities());
            user.getActivities().clear();
            user.getActivities().addAll(a);
            
            //check if exists
            for(FacebookActivity obj: user.getActivities())
            {
                ActivityDAO dao = new ActivityDAO();
                FacebookActivity read = dao.readFacebookActivityByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setActivities(user.getActivities());
            
            //remove duplicates
            Set<FacebookGroup> gr = new LinkedHashSet<FacebookGroup>();
            gr.addAll(user.getGroups());
            user.getGroups().clear();
            user.getGroups().addAll(gr);
            
            //check if exists
            for(FacebookGroup obj: user.getGroups())
            {
                GroupDAO dao = new GroupDAO();
                FacebookGroup read = dao.readFacebookGroupByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setGroups(user.getGroups());
            
            //remove duplicates
            Set<FacebookInterest> i = new LinkedHashSet<FacebookInterest>();
            i.addAll(user.getInterests());
            user.getInterests().clear();
            user.getInterests().addAll(i);
            
            for(FacebookInterest obj: user.getInterests())
            {
                InterestDAO dao = new InterestDAO();
                FacebookInterest read = dao.readFacebookInterestByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setInterests(user.getInterests());
            
            //remove duplicates
            Set<FacebookLike> l = new LinkedHashSet<FacebookLike>();
            l.addAll(user.getLikes());
            user.getLikes().clear();
            user.getLikes().addAll(l);
            
            for(FacebookLike obj: user.getLikes())
            {
                LikeDAO dao = new LikeDAO();
                FacebookLike read = dao.readFacebookLikeByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setLikes(user.getLikes());
            
            
            //remove duplicates
            Set<FacebookMovie> mo = new LinkedHashSet<FacebookMovie>();
            mo.addAll(user.getMovies());
            user.getMovies().clear();
            user.getMovies().addAll(mo);
            
            for(FacebookMovie obj: user.getMovies())
            {
                MovieDAO dao = new MovieDAO();
                FacebookMovie read = dao.readFacebookMovieByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setMovies(user.getMovies());
            
            //remove duplicates
            Set<FacebookMusic> mu = new LinkedHashSet<FacebookMusic>();
            mu.addAll(user.getMusic());
            user.getMusic().clear();
            user.getMusic().addAll(mu);
            
            for(FacebookMusic obj: user.getMusic())
            {
                MusicDAO dao = new MusicDAO();
                FacebookMusic read = dao.readFacebookMusicByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setMusic(user.getMusic());
            
            //remove duplicates
            Set<FacebookTelevision> t = new LinkedHashSet<FacebookTelevision>();
            t.addAll(user.getTelevision());
            user.getTelevision().clear();
            user.getTelevision().addAll(t);
            
            for(FacebookTelevision obj: user.getTelevision())
            {
                TelevisionDAO dao = new TelevisionDAO();
                FacebookTelevision read = dao.readFacebookTelevisionByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
            //set
            setTelevision(user.getTelevision());
            
            //remove duplicates
            Set<FacebookGame> ga = new LinkedHashSet<FacebookGame>();
            ga.addAll(user.getGames());
            user.getGames().clear();
            user.getGames().addAll(ga);
            
            for(FacebookGame obj: user.getGames())
            {
                GameDAO dao = new GameDAO();
                FacebookGame read = dao.readFacebookGameByName(obj.getName());
                obj.setValuesFrom(read);
            }
            //set
            setGames(user.getGames());
            
            
            //remove duplicates
            Set<FacebookBook> b = new LinkedHashSet<FacebookBook>();
            b.addAll(user.getBooks());
            user.getBooks().clear();
            user.getBooks().addAll(b);
            
            for(FacebookBook obj: user.getBooks())
            {
                BookDAO dao = new BookDAO();
                FacebookBook read = dao.readFacebookBookByName(obj.getName());
                obj.setValuesFrom(read);
            }
             
            //set
            setBooks(user.getBooks());
            
            
        }
    }

    /**
     * @return the works
     */
    public List<FacebookWork> getWorks() {
        return works;
    }

    /**
     * @param works the works to set
     */
    public void setWorks(List<FacebookWork> works) {
        this.works = works;
    }

    /**
     * @return the education
     */
    public List<FacebookEducation> getEducation() {
        return education;
    }

    /**
     * @param education the education to set
     */
    public void setEducation(List<FacebookEducation> education) {
        this.education = education;
    }

    /**
     * @return the sports
     */
    public List<FacebookSport> getSports() {
        return sports;
    }

    /**
     * @param sports the sports to set
     */
    public void setSports(List<FacebookSport> sports) {
        this.sports = sports;
    }

    /**
     * @return the activities
     */
    public List<FacebookActivity> getActivities() {
        return activities;
    }

    /**
     * @param activities the activities to set
     */
    public void setActivities(List<FacebookActivity> activities) {
        this.activities = activities;
    }

    /**
     * @return the groups
     */
    public List<FacebookGroup> getGroups() {
        return groups;
    }

    /**
     * @param groups the groups to set
     */
    public void setGroups(List<FacebookGroup> groups) {
        this.groups = groups;
    }

    /**
     * @return the interests
     */
    public List<FacebookInterest> getInterests() {
        return interests;
    }

    /**
     * @param interests the interests to set
     */
    public void setInterests(List<FacebookInterest> interests) {
        this.interests = interests;
    }

    /**
     * @return the likes
     */
    public List<FacebookLike> getLikes() {
        return likes;
    }

    /**
     * @param likes the likes to set
     */
    public void setLikes(List<FacebookLike> likes) {
        this.likes = likes;
    }

    /**
     * @return the movies
     */
    public List<FacebookMovie> getMovies() {
        return movies;
    }

    /**
     * @param movies the movies to set
     */
    public void setMovies(List<FacebookMovie> movies) {
        this.movies = movies;
    }

    /**
     * @return the music
     */
    public List<FacebookMusic> getMusic() {
        return music;
    }

    /**
     * @param music the music to set
     */
    public void setMusic(List<FacebookMusic> music) {
        this.music = music;
    }

    /**
     * @return the television
     */
    public List<FacebookTelevision> getTelevision() {
        return television;
    }

    /**
     * @param television the television to set
     */
    public void setTelevision(List<FacebookTelevision> television) {
        this.television = television;
    }

    /**
     * @return the games
     */
    public List<FacebookGame> getGames() {
        return games;
    }

    /**
     * @param games the games to set
     */
    public void setGames(List<FacebookGame> games) {
        this.games = games;
    }

    /**
     * @return the books
     */
    public List<FacebookBook> getBooks() {
        return books;
    }

    /**
     * @param books the books to set
     */
    public void setBooks(List<FacebookBook> books) {
        this.books = books;
    }
}
