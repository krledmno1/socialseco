<%-- 
    Document   : usersExtraction
    Created on : 18-Dec-2011, 16:23:33
    Author     : damian
--%>

<%@page import="socialseco.controller.UserUpdater"%>
<%@page import="socialseco.model.linkedin.*"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%@page import="socialseco.linkedin.UserExtractor"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
Object obj = session.getAttribute("linkedin_authenticator");
if(obj == null)
    response.sendRedirect("./authenticationStart.jsp");

LinkedInAuthenticator authenticator = (LinkedInAuthenticator)obj;
UserExtractor userExtractor = new UserExtractor(authenticator);
String users = userExtractor.extractUsers2();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LinkedIn Users Extracted and Saved</h1>
        
        <div id="nav">
            <a href="../">Back</a>
        </div>
        
        <div id="users">
        <%= users %>
        </div>
    </body>
</html>

