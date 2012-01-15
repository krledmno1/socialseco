<%-- 
    Document   : compareModels
    Created on : Jan 15, 2012, 1:26:36 AM
    Author     : krle
--%>

<%@page import="socialseco.model.question.Instance"%>
<%@page import="socialseco.model.question.KeyValue"%>
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
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Recommend people</h1>
        <div id="nav">
        <a href="../">Back</a>
        </div>
        <br/>
        <h2> Platform selection </h2>
        <form action="comparisonResults.jsp" method="post">
            <select name="platform">
                <option value="facebook" >Facebook</option>
                <option value="linkedin" >LinkedIn</option>
            </select>
            
            
        <h2> Question selection </h2>  
        <%
        if(!empty)
        {
        %>
        
        <%  int i = 0;
            for(Question q:obj){ %>
        <input type="radio" name="question" value="<%=i+1%>"> Question <%=i++%><br>
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
        ________________________________________________________________________
        <br/>
        <br/>
        
            <%}%>
        <%}
        else
       {%>
       
       <div> There are no questions in current session, please go back and create </div>
       <br/>
        <div id="nav">
        <a href="../">Back</a>
        </div>
       <% } %>
       
       
            
            
        </form>
    </body>
</html>
