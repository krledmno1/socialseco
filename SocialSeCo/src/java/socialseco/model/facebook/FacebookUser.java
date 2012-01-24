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
import socialseco.dao.facebookProperties.*;
import socialseco.model.User;

/**
 *
 * @author damian
 */
@Entity
public class FacebookUser
        extends User {

    
    public static int fieldNum = 17;
    
    protected String facebookId;
    private String fullName;
    private String hometown;
    private String location;
    
    
    //@GenericGenerator(name="uuid-gen", strategy = "uuid")
    //@CollectionId(columns = @Column(name = "COL_ID"), type = @Type(type = "string"), generator = "uuid-gen") 
    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookWork> works;
    @Column(length=5000)
    private String bio;
    private String gender;
    private String religion;
    
    
    //@GenericGenerator(name="uuid-gen", strategy = "uuid")
    //@CollectionId(columns = @Column(name = "COL_ID"), type = @Type(type = "string"), generator = "uuid-gen") 
    @ManyToMany(cascade=CascadeType.ALL)
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

    @ManyToMany(cascade=CascadeType.ALL)
    private List<FacebookLanguage> languages;
    
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
        languages = new ArrayList<FacebookLanguage>();
          
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
    
    
    public String flattenFeature(int i)
    {
        switch(i)
        {
            case 0:
            {
              return flattenActivities(""); 
            }
            case 1:
            {
              return flattenBio("");
            }
            case 2:
            {
              return flattenBooks("");
            }
            case 3:
            {
              return flattenEducation("");
            }
            case 4:
            {
              return flattenGames("");
            }
            case 5:
            {
              return flattenGroups("");
            }
            case 6:
            {
              return flattenHometown("");
            }
            case 7:
            {
              return flattenInterests("");
            }
            case 8:
            {
              return flattenLanguages("");
            }
            case 9:
            {
              return flattenLikes("");
            }
            case 10:
            {
              return flattenLocation("");
            }
            case 11:
            {
              return flattenMovies("");
            }
            case 12:
            {
              return flattenMusic("");
            }
            case 13:
            {
              return flattenReligion("");
            }
            case 14:
            {
              return flattenSports("");
            }
            case 15:
            {
              flattenTelevision("");
            }
            case 16:
            {
              flattenWork("");
            }
        }
        return null;
    }
    
    
    public String flattenHometown(String output)
    {
        output += getHometown()!=null ? getHometown():"";
        output += " ";
        return output;
    }
    public String flattenLocation(String output)
    {
        output += getLocation()!=null ? getLocation():"";
        output += " ";
        return output;
    }
    public String flattenWork(String output)
    {
        for(FacebookWork obj: getWorks())
        {
            output += obj.getEmployer();
            output += " ";
            output += obj.getPosition();
            output += " ";
        }
        return output;
    }
    public String flattenBio(String output)
    {
        output += getBio()!=null ? getBio():"";
        output += " ";
        return output;
       
    }
    public String flattenReligion(String output)
    {
        output += getReligion()!=null ? getReligion():"";
        output += " ";
        return output;
    }
     
    public String flattenEducation(String output)
    {
        for(FacebookEducation obj: getEducation())
        {
            output += obj.getSchool();
            output += " ";
            output += obj.getType();
            output += " ";
            
            for(FacebookConcentration con: obj.getConecentration())
            {    
                output += con.getName();
                output += " ";
            }   
        }
        return output;
    }
    public String flattenSports(String output)
    {
        for(FacebookSport obj: getSports())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    } 
    public String flattenActivities(String output)
    {
        for(FacebookActivity obj: getActivities())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenGroups(String output)
    {
        for(FacebookGroup obj: getGroups())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenInterests(String output)
    {
        for(FacebookInterest obj: getInterests())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenLikes(String output)
    {
        for(FacebookLike obj: getLikes())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenMovies(String output)
    {
        for(FacebookMovie obj: getMovies())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenMusic(String output)
    {
        for(FacebookMusic obj: getMusic())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenTelevision(String output)
    {
        for(FacebookTelevision obj: getTelevision())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenGames(String output)
    {
        for(FacebookGame obj: getGames())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenBooks(String output)
    {
        for(FacebookBook obj: getBooks())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
    }
    public String flattenLanguages(String output)
    {
        for(FacebookLanguage obj: getLanguages())
        {
            output += obj.getName();
            output += " ";
        }
        return output;
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
    
            setWorks(user.getWorks());
            
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
            
            setLanguages(user.getLanguages());
            
            
        }
    }

    public void removeDuplicates()
    {
             //remove duplicates
            Set<FacebookWork> w = new LinkedHashSet<FacebookWork>();
            w.addAll(getWorks());
            getWorks().clear();
            getWorks().addAll(w);
        
            
            Set<FacebookConcentration> conAll = new LinkedHashSet<FacebookConcentration>();
            for(FacebookEducation edu: getEducation())
            {
                Set<FacebookConcentration> con = new LinkedHashSet<FacebookConcentration>();
                con.addAll(edu.getConecentration());
                edu.getConecentration().clear();
                edu.getConecentration().addAll(con);
                
                conAll.addAll(con);
            }
            
            //remove duplicates across schools 
            for(FacebookEducation edu: getEducation())
            {
                for(FacebookConcentration con: edu.getConecentration())
                {
                    if(conAll.contains(con))
                        conAll.remove(con);
                    else
                        edu.getConecentration().remove(con);
                }
            }
           
            /*
            
            //remove duplicates
            Set<FacebookSport> s = new LinkedHashSet<FacebookSport>();
            s.addAll(getSports());
            getSports().clear();
            getSports().addAll(s);
            
            //remove duplicates
            Set<FacebookActivity> a = new LinkedHashSet<FacebookActivity>();
            a.addAll(getActivities());
            getActivities().clear();
            getActivities().addAll(a);
        
             //remove duplicates
            Set<FacebookGroup> gr = new LinkedHashSet<FacebookGroup>();
            gr.addAll(getGroups());
            getGroups().clear();
            getGroups().addAll(gr);
        
            //remove duplicates
            Set<FacebookInterest> i = new LinkedHashSet<FacebookInterest>();
            i.addAll(getInterests());
            getInterests().clear();
            getInterests().addAll(i);
        
            //remove duplicates
            //Set<FacebookLike> l = new LinkedHashSet<FacebookLike>();
            //l.addAll(getLikes());
            //getLikes().clear();
            //getLikes().addAll(l);
            
            
             //remove duplicates
            Set<FacebookMovie> mo = new LinkedHashSet<FacebookMovie>();
            mo.addAll(getMovies());
            getMovies().clear();
            getMovies().addAll(mo);
            
            //remove duplicates
            Set<FacebookMusic> mu = new LinkedHashSet<FacebookMusic>();
            mu.addAll(getMusic());
            getMusic().clear();
            getMusic().addAll(mu);
            
            
            //remove duplicates
            Set<FacebookTelevision> t = new LinkedHashSet<FacebookTelevision>();
            t.addAll(getTelevision());
            getTelevision().clear();
            getTelevision().addAll(t);
        
            //remove duplicates
            Set<FacebookGame> ga = new LinkedHashSet<FacebookGame>();
            ga.addAll(getGames());
            getGames().clear();
            getGames().addAll(ga);
            
            //remove duplicates
            Set<FacebookBook> b = new LinkedHashSet<FacebookBook>();
            b.addAll(getBooks());
            getBooks().clear();
            getBooks().addAll(b);
            
            //remove duplicates
            Set<FacebookLanguage> la = new LinkedHashSet<FacebookLanguage>();
            la.addAll(getLanguages());
            getLanguages().clear();
            getLanguages().addAll(la);
            */
    }
    
    public void updateReferences()
    {
        //check if exists
            for(FacebookWork work: getWorks())
            {
                WorkDAO dao = new WorkDAO();
                FacebookWork read = dao.readFacebookWorkByEmployerAndPosition(work.getEmployer(), work.getPosition());
                work.setValuesFrom(read);
            }
           
            
          /*
            for(FacebookEducation edu: getEducation())
            {
                
                
                for(FacebookConcentration conc: edu.getConecentration())
                {
                    ConcentrationDAO dao = new ConcentrationDAO();
                    FacebookConcentration read = dao.readFacebookConcentrationByName(conc.getName());
                    conc.setValues(read);
                }
            }
            
            
           */ 
            /*
            //check if exists
            for(FacebookSport spo: getSports())
            {
                SportDAO dao = new SportDAO();
                FacebookSport read = dao.readFacebookSportByName(spo.getName());
                spo.setValuesFrom(read);
            } 
           
            
            
            
            //check if exists
            for(FacebookActivity obj: getActivities())
            {
                ActivityDAO dao = new ActivityDAO();
                FacebookActivity read = dao.readFacebookActivityByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
           
            
            //check if exists
            for(FacebookGroup obj: getGroups())
            {
                GroupDAO dao = new GroupDAO();
                FacebookGroup read = dao.readFacebookGroupByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
            
            
            for(FacebookInterest obj: getInterests())
            {
                InterestDAO dao = new InterestDAO();
                FacebookInterest read = dao.readFacebookInterestByName(obj.getName());
                obj.setValuesFrom(read);
            }
           
            
            
            //for(FacebookLike obj: getLikes())
            //{
            //    LikeDAO dao = new LikeDAO();
            //    FacebookLike read = dao.readFacebookLikeByName(obj.getName());
            //    obj.setValuesFrom(read);
            //}
           
            
           
            for(FacebookMovie obj: getMovies())
            {
                MovieDAO dao = new MovieDAO();
                FacebookMovie read = dao.readFacebookMovieByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
            
            
            for(FacebookMusic obj: getMusic())
            {
                MusicDAO dao = new MusicDAO();
                FacebookMusic read = dao.readFacebookMusicByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
            
            
            for(FacebookTelevision obj: getTelevision())
            {
                TelevisionDAO dao = new TelevisionDAO();
                FacebookTelevision read = dao.readFacebookTelevisionByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
           
           
            
            for(FacebookGame obj: getGames())
            {
                GameDAO dao = new GameDAO();
                FacebookGame read = dao.readFacebookGameByName(obj.getName());
                obj.setValuesFrom(read);
            }
            
           
            
            for(FacebookBook obj: getBooks())
            {
                BookDAO dao = new BookDAO();
                FacebookBook read = dao.readFacebookBookByName(obj.getName());
                obj.setValuesFrom(read);
            }
             
            
            for(FacebookLanguage obj: getLanguages())
            {
                LanguageDAO dao = new LanguageDAO();
                FacebookLanguage read = dao.readFacebookLanguageByName(obj.getName());
                obj.setValuesFrom(read);
            }
          */
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

    /**
     * @return the languages
     */
    public List<FacebookLanguage> getLanguages() {
        return languages;
    }

    /**
     * @param languages the languages to set
     */
    public void setLanguages(List<FacebookLanguage> languages) {
        this.languages = languages;
    }
}
