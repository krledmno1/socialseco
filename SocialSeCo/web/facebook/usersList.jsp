<%-- 
    Document   : usersList
    Created on : Jan 9, 2012, 1:22:32 AM
    Author     : krle
--%>

<%@page import="java.util.List"%>
<%@page import="socialseco.facebook.Json.JsonUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Facebook users</title>
    </head>
   
<% Object obj = session.getAttribute("usersFB");
List<JsonUser> users =null;
if(obj == null){
    //read from database
} else {
    users = (List<JsonUser>)obj;
}   
    
%>
    <body>
        
        <h1>Facebook Users in the Database</h1>
        
        <div id="nav">
            <a href="../">Back</a>
        </div>
        
        <div id="users">
        <% for(JsonUser user:users){ %>
            <div class="user">
                <div>Id: <%= user.getId() %></div>
                <div>Name: <%= user.getName() %></div>
                <div>Work: <%= user.getWork() != null ? user.getWork()[0].getEmployer().getName() : ""  %></div>
               
                </div>
            </div>
        <% } %>
        </div>

        
    </body>
</html>
