<%-- 
    Document   : comparisonResults
    Created on : Jan 15, 2012, 1:46:33 AM
    Author     : krle
--%>

<%@page import="socialseco.model.User"%>
<%@page import="socialseco.controller.UserUpdater"%>
<%@page import="socialseco.dao.UserDAO"%>
<%@page import="socialseco.model.question.Question"%>
<%@page import="socialseco.controller.Comparator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
List<Question> obj = (List<Question>)session.getAttribute("questions");
if(obj==null)
{
    response.sendRedirect("../question/createQuestion.jsp");
}

String platform = request.getParameter("platform")!=null ? request.getParameter("platform").toString() : null;
String method = request.getParameter("method")!=null ? request.getParameter("method").toString() : null;
String question = request.getParameter("question")!=null ? request.getParameter("question").toString() : null;
String num = request.getParameter("num")!=null ? request.getParameter("num").toString() : null;

if(platform!=null && method!=null && question!=null && num!=null)
{
    
Comparator comparator = new Comparator();
comparator.setMethod(method);
comparator.setPlatform(platform);
comparator.setNum(Integer.parseInt(num));

Question q = obj.get(Integer.parseInt(question));

UserUpdater updater = new UserUpdater();
UserDAO dao = new UserDAO();
dao.beginConversation();
Object o = updater.totalRecall(platform);

Question result = comparator.compare(q, o);

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Recommended people</title>
    </head>
    <body>
        <h1>Recommended people</h1>
        <div id="nav">
        <a href="../">Back</a>
        </div>
        <% int n = result.getSelectedUsers().length;
        for(int i = 0;i<n;i++){ 
        User u = (User)dao.readUsersById(result.getSelectedUsers()[i], platform);
        %>
                <br/>
                <div>Id: <%=result.getSelectedUsers()[i] %>   with confidence: <%=Double.parseDouble(result.getConfidence()[i]) %>  </div>
                <div>Name: <%=u.getName()%> </div>
                <div>Surname: <%=u.getSurname()%> </div>
        <%}%>
    </body>
</html>
<%
dao.endConversation();
}
else
{
    request.setAttribute("error", "Please select all parameters");
    response.sendRedirect("compareModels.jsp");
}
%>