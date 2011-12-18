<%-- 
    Document   : authenticationVerify
    Created on : 18-Dec-2011, 14:09:27
    Author     : damian
--%>

<%@page import="socialseco.linkedin.UserExtractor"%>
<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="org.scribe.model.Verb"%>
<%@page import="org.scribe.model.OAuthRequest"%>
<%@page import="javax.xml.parsers.SAXParser"%>
<%@page import="javax.xml.parsers.SAXParserFactory"%>
<%@page import="org.xml.sax.Attributes"%>
<%@page import="org.xml.sax.SAXException"%>
<%@page import="org.xml.sax.helpers.DefaultHandler"%>

<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String oauth_token = request.getParameter("oauth_token");
String oauth_verifier = request.getParameter("oauth_verifier");
Object obj = session.getAttribute("linkedin_authenticator");
if(obj == null || oauth_token == null || oauth_token.isEmpty() || oauth_verifier == null || oauth_verifier.isEmpty()){
    response.sendRedirect("./authenticationStart.jsp");
}

LinkedInAuthenticator authenticator = (LinkedInAuthenticator)obj;
authenticator.setOAuthToken(oauth_token);
authenticator.setOAuthVerifier(oauth_verifier);
authenticator.verify();

response.sendRedirect("../");
%>