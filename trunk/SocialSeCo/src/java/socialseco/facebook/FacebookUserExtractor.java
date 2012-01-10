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
        for(int i = 1; i<50;i++)
        {
            url = baseUrl;
            url += users.data[i].getId();
            url += "?access_token=";
            url += auth.getAccessToken();
            json = getJsonFromUrl(url);
            
           
         
            JsonUser user = gson.fromJson(json, JsonUser.class);
            returnUsers.add(user);
            
            
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
