<%-- 
    Document   : questionList
    Created on : Jan 14, 2012, 1:50:59 AM
    Author     : krle
--%>

<%@page import="socialseco.model.question.KeyValue"%>
<%@page import="socialseco.model.question.Instance"%>
<%@page import="socialseco.model.question.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
List<Question> obj = (List<Question>)session.getAttribute("questions");
boolean empty = true;
if(obj!=null)
{
    empty = false;
}

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Question list</title>
    </head>
    <body>
           <h1> Already created questions </h1>
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
        
        
            <%}%>
        <%}
        else
       {%>
       
       <div> There are no questions in current session </div>
       
       <% } %>
       
       
    </body>
</html>
