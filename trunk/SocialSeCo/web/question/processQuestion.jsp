<%-- 
    Document   : processQuestion
    Created on : Jan 14, 2012, 1:05:18 AM
    Author     : krle
--%>


<%@page import="socialseco.model.question.Instance"%>
<%@page import="socialseco.model.question.Schema"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="socialseco.model.question.Question"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
List<Question> questions = (List<Question>)session.getAttribute("questions");

if(questions==null)
{
    questions = new ArrayList<Question>();
}

String opId = request.getParameter("opid")!=null ? request.getParameter("opid").toString() : null;
String question = request.getParameter("question")!=null ? request.getParameter("question").toString() : null;
String desc = request.getParameter("desc")!=null ? request.getParameter("desc").toString() : null;

int cols = Integer.parseInt(request.getParameter("cols").toString());
int rows = Integer.parseInt(request.getParameter("rows").toString());

if(!opId.isEmpty() && !question.isEmpty() && cols!=0 && rows!=0)
{
    Question q = new Question();
    q.setOperationID(opId);
    q.setQuestion(question);
    q.setDescription(desc);
    Schema questionSchema = new Schema();
    List<Instance> questionInstances = new ArrayList<Instance>();
    
    for(int j = 0; j<rows;j++)
    {
        Instance ins = new Instance();
        for(int i = 0; i<cols;i++)
        {
            if(j==0)
            {
                String name = request.getParameter("schema_"+String.valueOf(i))!=null ? request.getParameter("schema_"+String.valueOf(i)).toString() : null;
                String type = request.getParameter("type_"+String.valueOf(i))!=null ? request.getParameter("type_"+String.valueOf(i)).toString() : null;
                questionSchema.setValue(name, type);
            }
           
                String field = questionSchema.getKeys().get(i);
                String value = request.getParameter("cell_"+String.valueOf(j)+"_"+String.valueOf(i))!=null ? request.getParameter("cell_"+String.valueOf(j)+"_"+String.valueOf(i)).toString() : null;
                ins.setValue(field, value);
           

        }
        questionInstances.add(ins);
    }
    q.setQuestionSchema(questionSchema);
    q.setQuestionInstances(questionInstances);
    
    questions.add(q);
    
    session.setAttribute("questions", questions);
    response.sendRedirect("questionList.jsp");
}
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
