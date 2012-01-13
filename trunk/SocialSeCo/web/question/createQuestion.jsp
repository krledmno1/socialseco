<%-- 
    Document   : createQuestion
    Created on : Jan 13, 2012, 6:45:44 PM
    Author     : krle
--%>

<%@page import="socialseco.model.question.Instance"%>
<%@page import="socialseco.model.question.KeyValue"%>
<%@page import="socialseco.model.question.KeyValueMapping"%>
<%@page import="java.util.List"%>
<%@page import="socialseco.model.question.Question"%>
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
        <title>Create question</title>
    </head>
    <body>
        <h1>Fill in question info or import</h1>
        <div id="question">
            <form action="processQuestion.jsp" method="post">
                <div>Operation ID: <input type="text" value ="" name="opid"/> </div>
                <div>Question: <input type="text" value ="" name="question"/> </div>
                <div>Description: <input type="text" value ="" name="desc"/> </div>
                
                <div> <input type="submit" name="submit" value="insert" /> </div>
            </form>
        </div>
        <%
        if(!empty)
        {
        %>
        <h1> Already created questions </h1>
        <%for(Question q:obj){ %>
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
        <%}%>
    </body>
</html>
