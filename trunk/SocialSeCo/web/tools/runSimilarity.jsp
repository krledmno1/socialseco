<%-- 
    Document   : runSimilarity
    Created on : 04-Feb-2012, 16:11:44
    Author     : damian
--%>

<%@page import="socialseco.model.User"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.controller.UserSimilarityController"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="socialseco.dao.UserSimilarityDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
UserSimilarityController controller = new UserSimilarityController();
List<User> similarUsers = controller.processAllUsers();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Similar Users</title>
    </head>
    <body>
        <h1>Similarities found</h1>
        <div id="nav">
            <a href="../">Back</a>
        </div>
        <ul>
            <% for(User user:similarUsers){ %>
            <li><%= user.getName() %> <%= user.getSurname() %></li>
            <% } %>
        </ul>
    </body>
</html>
