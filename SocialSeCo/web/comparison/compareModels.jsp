<%-- 
    Document   : compareModels
    Created on : Jan 15, 2012, 1:26:36 AM
    Author     : krle
--%>

<%@page import="socialseco.controller.Comparator"%>
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
        
        <%
        String error = session.getAttribute("error")!=null ? session.getAttribute("error").toString() : null ;
        if(error != null)
        {            
        %>
        <br/>
        <div style="color: red"> <%=error %> </div>
        <% } %>
        <br/>
        <h2> Platform selection </h2>
        <form action="comparisonResults.jsp" method="post">
            <select name="platform">
                <option value="<%=Comparator.facebookPlatform%>" >Facebook</option>
                <option value="<%=Comparator.linkedinPlatform%>" >LinkedIn</option>
            </select>
            
        <h2> Method selection </h2>
            <select name="method">
                <option value="<%=Comparator.letterPairSimilarity%>" >Letter pair similarity</option>
                <option value="<%=Comparator.maxWordSimilarity%>" >Max Word Similarity</option>
                <option value="<%=Comparator.instanceBasedMatching%>" >Instance based matching</option>
                <option value="<%=Comparator.tf_idfBasedMatching%>" >TF-IDF based matching</option>
            </select>
        
        <h2> Number of recommendations </h2>
        <div>Top <input type="number" name="num" min="1" max="100" /> friends </div>
        
        <h2> Question selection </h2>  
        <%
        if(!empty)
        {
        %>
        
        <%  int i = 0;
            for(Question q:obj){ %>
        <input type="radio" name="question" value="<%=i%>"> Question <%=i+1%><br>
        <br/>
        <% i++; %>
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
            
            <input type="submit" name="submit" value="Recommend" />
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
