<%-- 
    Document   : usersExtraction
    Created on : Jan 7, 2012, 2:22:38 PM
    Author     : krle
--%>

<%@page import="socialseco.facebook.Json.JsonUser"%>
<%@page import="socialseco.model.facebook.FacebookUser"%>
<%@page import="socialseco.controller.UserUpdater"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.facebook.FacebookUserExtractor"%>
<%@page import="socialseco.facebook.FacebookAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User extraction</title>
    </head>
    <body>
        <%
Object obj = session.getAttribute("facebook_authenticator");
if(obj == null)
    response.sendRedirect("./authenticationStart.jsp");

FacebookAuthenticator authenticator = (FacebookAuthenticator)obj;
FacebookUserExtractor userExtractor = new FacebookUserExtractor(authenticator);
List<JsonUser> users = userExtractor.extractUsers();

/*
UserUpdater updater = new UserUpdater();
updater.updateUsers(users);

this is to update the DB 

*/
session.setAttribute("usersFB", users);
response.sendRedirect("usersList.jsp");

%>

 
    </body>
</html>
