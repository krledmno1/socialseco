<%-- 
    Document   : usersList
    Created on : 18-Dec-2011, 17:22:30
    Author     : damian
--%>

<%@page import="socialseco.model.linkedin.*"%>
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
        <style>
            .user{
                background: lightgray;
                margin: 10px;
            }
        </style>
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
                <div>Surname: <%= user.getSurname() == null ? "" : user.getSurname() %></div>
                <div>Industry <%= user.getIndustry() == null ? "" : user.getIndustry() %></div>
                <div class="languages">
                <% for(LinkedinLanguage language:user.getLanguages()){ %>
                    <div class="language">
                        <div class="language_name"><%= language.getName() %></div>
                    </div>
                <% } %>
                <% for(LinkedinEducation education:user.getEducations()){ %>
                    <div class="education">
                        School Name:
                        <div class="language_school_name"><%= education.getSchoolName() == null ? "" : education.getSchoolName() %></div>
                        Field of Study:
                        <div class="language_field_of_study"><%= education.getFieldOfStudy() == null ? "" : education.getFieldOfStudy() %></div>
                        Degree:
                        <div class="language_degree"><%= education.getDegree() == null ? "" : education.getDegree() %></div>
                        Activities:
                        <div class="language_activities"><%= education.getActivities() == null ? "" : education.getActivities() %></div>
                        Notes:
                        <div class="language_notes"><%= education.getNotes() == null ? "" : education.getNotes() %></div>
                        Start Date:
                        <div class="language_start_date">
                            <%= education.getStartDate() == null ? "" : education.getStartDate().toString() %>
                        </div>
                        End Date:
                        <div class="language_end_date">
                            <%= education.getEndDate() == null ? "" : education.getEndDate().toString() %>
                        </div>
                    </div>
                <% } %>
                </div>
            </div>
        <% } %>
        </div>
    </body>
</html>
