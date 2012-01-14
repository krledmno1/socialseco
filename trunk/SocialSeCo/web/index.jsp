<%-- 
    Document   : index
    Created on : 17-Dec-2011, 16:08:00
    Author     : damian
--%>

<%@page import="socialseco.linkedin.UserExtractor"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.model.User"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html xmlns:fb="http://ogp.me/ns/fb#">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Social Seco</title>
    </head>
    <body>
     
        
        
        <!-- LINKEDIN -->
        <h1>Social Seco</h1>
        <h2>LinkedIn</h2>
        <% if(session.getAttribute("linkedin_authenticator") == null) { %>
        <div id="link">
            <a href="./linkedin/authenticationStart.jsp">Authenticate Linkedin</a>
        </div>
        <% } else { %>
        <div id="link">
            <a href="./linkedin/usersExtraction.jsp">Extract User from LinkedIn</a>
        </div>
        <% } %>
        
        <div id="link">
            <a href="./linkedin/usersList.jsp">Saved LinkedIn Users</a>
        </div>
        
        <br/>
        <br/>
        
        <!-- FACEBOOK -->
        <h2>Facebook</h2>
        <% if(session.getAttribute("facebook_authenticator") == null) { %>
        <div id="link">
            <a href="./facebook/authenticationStart.jsp">Authenticate Facebook</a>
        <% } else { %>
        <div id="link">
            <a href="./facebook/usersExtraction.jsp">Extract User from Facebook</a>
        </div>
        <% } %>
        
        <div id="link">
            <a href="./facebook/usersList.jsp">Saved Facebook Users</a>
        </div>
        
        <br/>
        <br/>
        
        <!-- QUESTIONS -->
        <h2>Questions</h2>
        <div id="link">
            <a href="./question/createQuestion.jsp">Create question</a>
        </div>
        <div id="link">
            <a href="./question/questionList.jsp">Saved questions</a>
        </div>
        
    </body>
</html>
