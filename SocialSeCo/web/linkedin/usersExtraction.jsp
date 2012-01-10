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
List<LinkedinUser> users = userExtractor.extractUsers();

UserUpdater updater = new UserUpdater();
updater.updateUsers(users);
request.setAttribute("users", users);
response.sendRedirect("usersList.jsp");
%>
