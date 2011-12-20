<%-- 
    Document   : usersList
    Created on : 18-Dec-2011, 17:22:30
    Author     : damian
--%>

<%@page import="socialseco.model.linkedin.LinkedinLanguage"%>
<%@page import="socialseco.model.linkedin.LinkedinUser"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.linkedin.LinkedInAuthenticator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
Object obj = session.getAttribute("users");
List<LinkedinUser> users;
if(obj == null){
    UserDAO dao = new UserDAO();
    users = dao.readAllLinkedinUsers();
} else {
    users = (List<LinkedinUser>)obj;
}
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
                <div class="languages">
                <% for(LinkedinLanguage language:user.getLanguages()){ %>
                    <div class="language">
                        <div class="language_name"><%= language.getName() %></div>
                    </div>
                <% } %>
                </div>
            </div>
        <% } %>
        </div>
    </body>
</html>
