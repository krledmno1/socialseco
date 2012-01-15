<%-- 
    Document   : importQuestion
    Created on : Jan 14, 2012, 2:13:45 AM
    Author     : krle
--%>

<%@page import="java.io.DataInputStream"%>
<%@page import="java.util.ArrayList"%>
<%@page import="socialseco.model.question.Question"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
List<Question> questions = (List<Question>)session.getAttribute("questions");

if(questions==null)
{
    questions = new ArrayList<Question>();
}


String contentType = request.getContentType();
if ((contentType != null)) 
{
    if(contentType.indexOf("multipart/form-data") >= 0)
    {           

                DataInputStream in = new DataInputStream(request.getInputStream());
                
                int formDataLength = request.getContentLength();
                byte dataBytes[] = new byte[formDataLength];
                int byteRead = 0;
                int totalBytesRead = 0;
                
                while (totalBytesRead < formDataLength) 
                {
                        byteRead = in.read(dataBytes, totalBytesRead, formDataLength);
                        totalBytesRead += byteRead;
                }
                
                String json = new String(dataBytes);
               
                int begin = json.indexOf('{');
                int end = json.lastIndexOf('}');
                json = json.substring(begin, end+1);
                
                Question q = Question.parseQuestion(json);
                q.populateMappings(json);
                
                questions.add(q);
               
    }
}

session.setAttribute("questions", questions);
response.sendRedirect("questionList.jsp");

%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
    </body>
</html>
