/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.linkedin;

import java.util.Scanner;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

/**
 *
 * @author damian
 */
public class LinkedInAuthenticator {
    
    private OAuthService service;
    private Token requestToken;
    private Token accessToken;
    private String authUrl;
    private String oauth_token;
    private String oauth_verifier;
    private Verifier verifier;
    
    public String getAuthenticationURL(){
        service = new ServiceBuilder()
                        .provider(LinkedInApi.class)
                        .apiKey("6xpow4sbjlgh")
                        .apiSecret("uaw0cnrMHjWpPT8K")
                        .callback("http://localhost:8084/SocialSeCo/linkedin/authenticationVerify.jsp")
                        .build();
        
        requestToken = service.getRequestToken();
        authUrl = service.getAuthorizationUrl(requestToken);
        return authUrl;
    }
    
    public void verify(){
        verifier = new Verifier(oauth_verifier);
        accessToken = service.getAccessToken(requestToken, verifier);
    }
    
    public OAuthRequest sign(OAuthRequest request){
        service.signRequest(accessToken, request);
        return request;
    }
    
    public void setOAuthToken(String oauth_token){
        this.oauth_token = oauth_token;
    }
    public String getOAuthToken(){
        return this.oauth_token;
    }
    
    public void setOAuthVerifier(String oauth_verifier){
        this.oauth_verifier = oauth_verifier;
    }
    public String getOAuthVerifier(){
        return this.oauth_verifier;
    }
}
