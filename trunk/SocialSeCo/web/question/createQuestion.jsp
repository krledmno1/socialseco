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
        <script>
            function addColumn()
            {
                var table = document.getElementById("schema");
                
                var refChild = document.getElementById("button");
                var schemaNode = document.createElement("th");
                var thNode = document.createElement("input")
                thNode.setAttribute("type", "text");
                thNode.setAttribute("name", "schema_"+ table.childNodes().item(0).childNodes().length-1);
                thNode.addEventListener("onchange", addRow, false);
                schemaNode.appendChild(thNode);
                var br = document.createElement("br");
                schemaNode.appendChild(br);
                
                var thNode2 = document.createElement("input");
                thNode2.setAttribute("type", "text");
                thNode2.setAttribute("name", "type_"+ table.childNodes().item(0).childNodes().length-1);
                thNode2.addEventListener("onchange", addRow, false);
                schemaNode.appendChild(thNode2);
                
                table.childNodes().item(0).insertBefore(schemaNode, refChild);
                for (i=1;i<=table.childNodes().length();i++)
                {
                   
                    var newTd = document.createElement("td");
                    var newInput =  document.createElement("input");
                    newInput.setAttribute("type", "text");
                    newInput.setAttribute("name", "cell_"+ i +"_"+ table.childNodes().item(i).childNodes().length);
                    newInput.addEventListener("onChange", addRow, false);
                    newTd.appendChild(newInput);
                    table.childNodes().item(i).appendChild(newTd);
                }
                
                var num = document.getElementById("dimc").getAttribute("value");
                num++;
                document.getElementById("dimc").setAttribute("value", num);
            }
            
            function addRow()
            {
                var table = document.getElementById("schema");
                if(table.lastChild==this.parentNode)
                {
  
                   
                   
                        var newTR = document.createElement("tr");
                        for(i=0;i<table.firstChild.childNodes().length()-1; i++)
                        {
                            var newTD = document.createElement("td");
                            var newInput =  document.createElement("input");
                            newInput.setAttribute("type", "text");
                            newInput.setAttribute("name", "cell_"+ table.childNodes().length() +"_"+ i);
                            newInput.addEventListener("onChange", addRow, false);
                            newTD.appendChild(newInput);
                            newTR.appendChild(newTD);
                        }
                        table.appendChild(newTR);
                        
                        var num = document.getElementById("dimr").getAttribute("value");
                        num++;
                        document.getElementById("dimr").setAttribute("value", num);
                    
                }
            }
        </script>
    </head>
    <body>
        <h1>Fill in question info or import</h1>
         <div id="nav">
            <a href="../">Back</a>
         </div>
        <div id="question">
            <form action="processQuestion.jsp" method="post">
                <div>Operation ID: <input type="text" value ="" name="opid"/> * </div>
                <div>Question: <input type="text" value ="" name="question"/> * </div>
                <div>Description: <input type="text" value ="" name="desc"/> </div>
                <div>Schema: * </div>
                <table id="schema">
                    <tr>
                        <th id="button">
                            <input type="button" value="add" name="columnAdder" onclick="addColumn" /> 
                        </th>
                        
                    </tr>
                    <tr>
                        
                        
                    </tr>
                </table>
                
                <div> <input type="submit" name="submit" value="insert" /> </div>
                <div><input id="dimc" type="hidden" name="cols" value="0" /> </div>
                <div><input id="dimr" type="hidden" name="rows" value="0" /> </div>
                
                <div> <p>* - mandatory fields  </p> </div>
            </form>
        </div>
        <br/>
        <br/>
        <div id="import">
            <form action="importQuestion.jsp" method="post" enctype="multipart/form-data">
                <input type="file" name="importFile" size="40" />
                <input type="submit" name="other" value ="import"/>
            </form>
            
        </div>
        
        
        <h1> Already created questions </h1>
        <%
        if(!empty)
        {
        %>
        
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
        <%}
        else
       {%>
       
       <div> There are no questions in current session </div>
       
       <% } %>
    </body>
</html>
