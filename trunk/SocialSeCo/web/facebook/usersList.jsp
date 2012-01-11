<%-- 
    Document   : usersList
    Created on : Jan 9, 2012, 1:22:32 AM
    Author     : krle
--%>

<%@page import="socialseco.model.facebook.FacebookUser"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.facebook.Json.JsonUser"%>
<%@page import="socialseco.facebook.Json.Properties.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facebook users</title>
    </head>
   
<% Object obj = request.getAttribute("usersFB");
List<FacebookUser> users =null;
if(obj == null){
    UserDAO dao = new UserDAO();
    users = dao.readAllFacebookUsers();
} else {
    users = (List<FacebookUser>)obj;
}   
    
%>
    <body>
        
        <h1>Facebook Users in the Database</h1>
        
        <div id="nav">
            <a href="../">Back</a>
        </div>
        
        <div id="users">
        <% for(FacebookUser user:users){ %>
            <div class="user">
                <br/>
                <div>Id: <%= user.getId() %></div>
                <div><b>Full Name: <%= user.getFullName() %></b></div>
                <div>First name: <%= user.getName() != null ? user.getName() : ""  %></div>
                <div>Last name: <%= user.getSurname() != null ? user.getSurname() : ""  %></div>
                
                <br/>
                <div>Bio: <%= user.getBio() != null ? user.getBio() : ""  %></div>
                <div>Religion: <%= user.getReligion() != null ? user.getReligion() : ""  %></div>
                <div>Gender: <%= user.getGender() != null ? user.getGender() : ""  %></div>
                <div>Hometown: <%= user.getHometown() != null ? user.getHometown() : ""  %></div>
                <div>Location: <%= user.getLocation() != null ? user.getLocation() : ""  %></div>
                
                <!-- TO DO: for loop for these guys -->
                <br/>
                <div>Education: <%= !user.getEducation().isEmpty() ? user.getEducation().get(0).getSchool() : ""  %></div>
                <div>Work: <%= !user.getWork().isEmpty() ? user.getWork().get(0).getEmployer() : ""  %></div>
               
                <br/>
                <div> Example instances of the fields </div>
                <div>Activities: <%= !user.getActivities().isEmpty()  ? user.getActivities().get(0) : ""  %></div>
                <div>Interests: <%= !user.getInterests().isEmpty() ? user.getInterests().get(0) : ""  %></div>
                <div>Groups: <%= !user.getGroups().isEmpty() ? user.getGroups().get(0) : ""  %></div>
                <div>Likes: <%= !user.getLikes().isEmpty() ? user.getLikes().get(0) : ""  %></div>
                <div>Books: <%= !user.getBooks().isEmpty() ? user.getBooks().get(0) : ""  %></div>
                <div>Games: <%= !user.getGames().isEmpty() ? user.getGames().get(0) : ""  %></div>
                <div>Movies: <%= !user.getMovies().isEmpty() ? user.getMovies().get(0) : ""  %></div>
                <div>Music: <%= !user.getMusic().isEmpty() ? user.getMusic().get(0) : ""  %></div>
                <div>Television: <%= !user.getTelevision().isEmpty() ? user.getTelevision().get(0) : ""  %></div>




                <br/>
               _________________________________________________________________________________________________
            </div>
        <% } %>
        </div>
        
        

        
    </body>
</html>
