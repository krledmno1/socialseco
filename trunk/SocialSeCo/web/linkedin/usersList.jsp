<%-- 
    Document   : usersList
    Created on : 18-Dec-2011, 17:22:30
    Author     : damian
--%>

<%@page import="socialseco.model.LinkedinUser"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
UserDAO dao = new UserDAO();
List<LinkedinUser> users = dao.readAllLinkedinUsers();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LinkedIn Users in the Database</h1>
        
        <div id="nav">
            <a href="../">Back</a>
        </div>
        
        <div id="users">
        <% for(LinkedinUser user:users){ %>
            <div class="user">
                <div>Id: <%= user.getId() %></div>
                <div>LinkedIn Id: <%= user.getLinkedinId() %></div>
                <div>Name: <%= user.getName() %></div>
                <div>Surname: <%= user.getSurname() %></div>
                <div>Industry <%= user.getIndustry() %></div>
            </div>
        <% } %>
        </div>
    </body>
</html>
