/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.linkedin;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import socialseco.model.LinkedinUser;

/**
 *
 * @author damian
 */
public class UserExtractor {
    private static final String PROTECTED_RESOURCE_URL =
            "http://api.linkedin.com/v1/people/~/connections:(id,first-name,last-name)";
    
    private LinkedInAuthenticator authenticator;
    
    public UserExtractor(LinkedInAuthenticator authenticator){
        this.authenticator = authenticator;
    }
    
    public List<LinkedinUser> extractUsers()
            throws ParserConfigurationException, UnsupportedEncodingException,
                    SAXException, IOException {
        List<LinkedinUser> ret = new ArrayList<LinkedinUser>();
        LinkedinUser user;
        
        OAuthRequest oauthRequest = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        oauthRequest = authenticator.sign(oauthRequest);
        org.scribe.model.Response oauthResponse = oauthRequest.send();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        
        InputStream is = new ByteArrayInputStream(oauthResponse.getBody().getBytes("UTF-8"));
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();
        NodeList nList = doc.getElementsByTagName("person");
        
        for (int temp=0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                
                String id = getTagValue("id", element);
                String first_name = getTagValue("first-name", element);
                String last_name = getTagValue("last-name", element);
                String industry = getTagValue("industry", element);
                
                user = new LinkedinUser();
                user.setLinkedinId(id);
                user.setName(first_name);
                user.setSurname(last_name);
                user.setIndustry(industry);
                
                ret.add(user);
            }
        }
        
        return ret;
    }
                    
    private String getTagValue(String tag, Element element) {
        NodeList elementByTag = element.getElementsByTagName(tag);
        if (elementByTag == null) return null;
        
        Node item_0 = elementByTag.item(0);
        if (item_0 == null) return null;
	
        NodeList nlList = item_0.getChildNodes();
        Node nValue = (Node) nlList.item(0);
	return nValue.getNodeValue();
    }
}
