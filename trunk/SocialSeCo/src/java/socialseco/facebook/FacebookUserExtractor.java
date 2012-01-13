/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.facebook;

import com.google.gson.Gson;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import socialseco.facebook.Json.JsonUser;
import socialseco.facebook.Json.JsonUserCollection;
import socialseco.facebook.Json.JsonUserSimple;

import socialseco.facebook.Json.Properties.*;
import socialseco.model.facebook.FacebookUser;

/**
 *
 * @author krle
 */
public class FacebookUserExtractor {
    
    FacebookAuthenticator auth;
    private String baseUrl = "https://graph.facebook.com/";
    

    public FacebookUserExtractor(FacebookAuthenticator a) {
        auth = a;
    }
    
    
    public List<JsonUser> extractUsers()
    {
        String url = baseUrl;
        url += "me/friends";
        url += "?access_token=";
        url += auth.getAccessToken();
        
        String json = getJsonFromUrl(url);
        Gson gson = new Gson();
        JsonUserCollection users = gson.fromJson(json, JsonUserCollection.class);
      
        List<JsonUser> returnUsers = new ArrayList<JsonUser>();
        
        //TO DO CHANGE 50!!!
        for(int i = 1; i<10;i++)
        {
            
            //user
            url = baseUrl;
            url += users.data[i].getId();
            url += "?access_token=";
            url += auth.getAccessToken();
            json = getJsonFromUrl(url);
            JsonUser user = gson.fromJson(json, JsonUser.class);
            returnUsers.add(user);
            
         
     
            System.out.println("User:  "+ json);
            
            //interests
            url = baseUrl;
            url += users.data[i].getId();
            url += "/interests";
            url += "?access_token=";
            url += auth.getAccessToken();
            json = getJsonFromUrl(url);
            JsonInterests inte = gson.fromJson(json, JsonInterests.class);
            user.setInte(inte);
            
          
            //likes
            url = baseUrl;
            url += users.data[i].getId();
            url += "/likes";
            url += "?access_token=";
            url += auth.getAccessToken();
            json = getJsonFromUrl(url);
            JsonLikes like = gson.fromJson(json, JsonLikes.class);
            user.setLike(like);
            
            
            //groups
            url = baseUrl;
            url += users.data[i].getId();
            url += "/groups";
            url += "?access_token=";
            url += auth.getAccessToken();
            json = getJsonFromUrl(url);
            JsonGroups gro = gson.fromJson(json, JsonGroups.class);
            user.setGro(gro);
            
             
            //activites
            url = baseUrl;
            url += users.data[i].getId();
            url += "/activities";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonActivities act = gson.fromJson(json, JsonActivities.class);
            user.setAct(act);
            
            
            //movies
            url = baseUrl;
            url += users.data[i].getId();
            url += "/movies";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonMovies mov = gson.fromJson(json, JsonMovies.class);
            user.setMov(mov);
            
            
            //music
            url = baseUrl;
            url += users.data[i].getId();
            url += "/music";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonMusic mus = gson.fromJson(json, JsonMusic.class);
            user.setMus(mus);
            
            
            //television
            url = baseUrl;
            url += users.data[i].getId();
            url += "/television";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonTelevision tel = gson.fromJson(json, JsonTelevision.class);
            user.setTel(tel);
            
            
            //games
            url = baseUrl;
            url += users.data[i].getId();
            url += "/games";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonGames gam = gson.fromJson(json, JsonGames.class);
            user.setGam(gam);
            
            //books
            url = baseUrl;
            url += users.data[i].getId();
            url += "/books";
            url += "?access_token=";
            url += auth.getAccessToken();            
            json = getJsonFromUrl(url);
            JsonBooks book = gson.fromJson(json, JsonBooks.class);
            user.setBook(book);
            
            
        }
        
        return returnUsers;
    }
    
    
    
    
    private String getJsonFromUrl(String url)
    {
        URL u;
        InputStream is = null;
        DataInputStream dis;
        String output ="";
        try 
        {
            u = new URL(url);
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));
            output = dis.readLine();
            
           return output;
        } 
        catch (MalformedURLException ex) 
        {
            Logger.getLogger(FacebookAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(FacebookAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
}
