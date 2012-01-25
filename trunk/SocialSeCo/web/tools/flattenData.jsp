<%-- 
    Document   : flattenData
    Created on : Jan 25, 2012, 5:42:18 PM
    Author     : krle
--%>

<%@page import="socialseco.model.question.KeyValue"%>
<%@page import="socialseco.model.question.Instance"%>
<%@page import="socialseco.model.question.Question"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.model.facebook.FacebookUser"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="socialseco.controller.UserUpdater"%>
<%@page import="socialseco.controller.Comparator"%>
<%
List<Question> obj = (List<Question>)session.getAttribute("questions");
boolean empty = false;
if(obj==null)
{
    empty=true;
}


UserUpdater updater = new UserUpdater();
UserDAO dao = new UserDAO();
dao.beginConversation();
Comparator c = new Comparator();

List<FacebookUser> users = (List<FacebookUser>)updater.totalRecall(Comparator.facebookPlatform);



%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tools</title>
    </head>
    <body>
           <h1> Flattened questions </h1>
            <div id="nav">
            <a href="../">Back</a>
             </div>
           <br/>
        <%
        if(!empty)
        {
        %>
        
        <%int i = 1;
        for(Question q:obj){ %>
        <br/>
        <div> <b> Question <%=i++%> </b> </div>
        <br/>
        <div> Operation ID:  <%= q.getOperationID()%> </div>
        <div> Question:  <%= q.getQuestion()%> </div>
        <div> Description: <%=q.getDescription()%></div>
        <table>
            <tr>
                <% for(KeyValue k:q.getQuestionSchema().getValues()){ %>
                <th> <b> <%=k.getKey()%> </b> </th>
                <%}%>
            </tr>
            <% for(Instance ins:q.getQuestionInstances()){ %>
            <tr>
                <% for(KeyValue val: ins.getValues()){ %>
                <td><%=val.getValue() %></td>
                <% } %>
            </tr>
            <% } %>
            
            
        </table>
        
        <div> Flattened data:  <%=c.flatten(q)%> </div>
        
            <%}%>
        <%}
        else
       {%>
       
       <div> There are no questions in current session </div>
       
       <% } %>
       
       <h1> Flattened facebook users </h1>
       <%
       if(users!= null)
       {
        %>
       <% for(FacebookUser user:users){ %>
            <div class="user">
                <br/>
                <div>Id: <%= user.getId() %></div>
                <div><b>Full Name: <%= user.getFullName() %></b></div>
                <div>First name: <%= user.getName() != null ? user.getName() : ""  %></div>
                <div>Last name: <%= user.getSurname() != null ? user.getSurname() : ""  %></div>
                <div> Flattened data: <%=c.flatten(user)  %>
       <%}%>
       <%} else {%>
       
       <div> There are no users in database </div>
       
       
       <%}%>
    </body>
</html>
