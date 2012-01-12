<%-- 
    Document   : usersExtraction
    Created on : Jan 7, 2012, 2:22:38 PM
    Author     : krle
--%>

<%@page import="socialseco.facebook.Json.JsonUser"%>
<%@page import="socialseco.model.facebook.FacebookUser"%>
<%@page import="socialseco.controller.*"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.facebook.FacebookUserExtractor"%>
<%@page import="socialseco.facebook.FacebookAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <%
Object obj = session.getAttribute("facebook_authenticator");
if(obj == null)
    response.sendRedirect("./authenticationStart.jsp");

FacebookAuthenticator authenticator = (FacebookAuthenticator)obj;
FacebookUserExtractor userExtractor = new FacebookUserExtractor(authenticator);
List<JsonUser> users = userExtractor.extractUsers();

//DB update
UserUpdater updater = new UserUpdater();
List<FacebookUser> fbUsers = JsonConverter.convertToFBObjects(users);
updater.updateFBUsers(fbUsers);


request.setAttribute("usersFB", fbUsers);
response.sendRedirect("usersList.jsp");

%>

 