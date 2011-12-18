<%-- 
    Document   : listUsers
    Created on : 18-Dec-2011, 11:31:19
    Author     : damian
--%>

<%@page import="java.util.List"%>
<%@page import="socialseco.model.User"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
UserDAO dao = new UserDAO();
List<User> users = dao.readAll();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Users</title>
    </head>
    <body>
        <h1>List of Users</h1>
        
        <div id="list_of_users">
            <% for(User userIt:users) { %>
                <div class="user">
                    <div class="user_name"><%= userIt.getName() %></div>
                    <div class="user_surname"><%= userIt.getSurname() %></div>
                </div>
            <% } %>
        </div>
    </body>
</html>
