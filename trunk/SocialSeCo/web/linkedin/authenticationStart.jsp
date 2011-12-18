<%-- 
    Document   : authenticate
    Created on : 18-Dec-2011, 13:26:36
    Author     : damian
--%>

<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%
LinkedInAuthenticator authenticator = new LinkedInAuthenticator();
String authUrl = authenticator.getAuthenticationURL();
session.setAttribute("linkedin_authenticator", authenticator);
response.sendRedirect(authUrl);
%>
