/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.facebook;

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

/**
 *
 * @author krle
 */
public class FacebookAuthenticator {
    
       private String redirectUri = "http://4e99.localtunnel.com";
       private String appID = "170421693057418";
       private String appSecret = "b70fa8c7fd12965d0b32dd7a9ec6d697";
       private String pathUserAuth = "";
       private String pathAppAuth = "";
       private String code = "";
       private String accessToken = "";
       private Long expirationTime;
       private String authUrl;
       private List<String> permissions = new ArrayList<String>();
       

    public FacebookAuthenticator() {
       authUrl = redirectUri + "/SocialSeCo";
       redirectUri += "/SocialSeCo/facebook/authenticationStart.jsp";
        
        permissions.add("user_about_me");
        permissions.add("friends_about_me");
        permissions.add("friends_activities");
        permissions.add("friends_education_history");
        permissions.add("friends_groups");
        permissions.add("friends_hometown");
        permissions.add("friends_interests");
        permissions.add("friends_likes");
        permissions.add("friends_location");
        permissions.add("friends_online_presence");
        permissions.add("friends_questions");
        permissions.add("friends_religion_politics");
        permissions.add("friends_status");
        permissions.add("friends_work_history");
       
    }
    
    public String doUserAuth()
    {
           pathUserAuth = "https://www.facebook.com/dialog/oauth?client_id=";
           pathUserAuth += appID;
           pathUserAuth += "&redirect_uri=";
           pathUserAuth += redirectUri;
           pathUserAuth += "&scope=";
           
           for(int i=0;i<permissions.size();i++)
           {
                pathUserAuth += permissions.get(i);
               if(i<permissions.size()-1)
                   pathUserAuth += ",";
           }
           
           
           return pathUserAuth;
    }
    
    public boolean doAppAuth(String c)
    {
        code = c;
        pathAppAuth = "https://graph.facebook.com/oauth/access_token?client_id=";
        pathAppAuth += appID;
        pathAppAuth += "&redirect_uri=";
        pathAppAuth += redirectUri;
        pathAppAuth += "&client_secret=";
        pathAppAuth += appSecret;
        pathAppAuth += "&code=";
        pathAppAuth += code;
                   
        URL u;
        InputStream is = null;
        DataInputStream dis;
        String output ="";
        try 
        {
            u = new URL(pathAppAuth);
            is = u.openStream();
            dis = new DataInputStream(new BufferedInputStream(is));
            output = dis.readLine();
            
            if(output!=null)
            {
                parseOutput(output);
                return true;
            }
            else
            {
                return false;
            }
        } 
        catch (MalformedURLException ex) 
        {
            Logger.getLogger(FacebookAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex) 
        {
            Logger.getLogger(FacebookAuthenticator.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        return false;
        
    
    }

    private void parseOutput(String output) {
        String [] s = output.split("&");
        accessToken = (s[0].split("="))[1];
        expirationTime = Long.parseLong((s[1].split("="))[1]);
    }

    /**
     * @return the authUrl
     */
    public String getAuthUrl() {
        return authUrl;
    }

    /**
     * @return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }
}
