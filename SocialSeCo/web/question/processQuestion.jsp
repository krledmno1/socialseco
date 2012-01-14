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

if(questions!=null)
{
    questions = new ArrayList<Question>();
}

String opId = session.getAttribute("opid").toString();
String question = session.getAttribute("question").toString();
String desc = session.getAttribute("desc").toString();

int cols = Integer.parseInt(session.getAttribute("cols").toString());
int rows = Integer.parseInt(session.getAttribute("rows").toString());

if(!opId.isEmpty() && !question.isEmpty() && cols!=0 && rows!=0)
{
    Question q = new Question();
    q.setOperationID(opId);
    q.setQuestion(question);
    q.setDescription(desc);
    Schema questionSchema = new Schema();
    List<Instance> questionInstances = new ArrayList<Instance>();
    
    for(int i = 0; i<cols;i++)
    {
        for(int j = 0; j<rows;j++)
        {
            if(i==0)
            {
                
                String name = session.getAttribute("schema_"+String.valueOf(i)).toString();
                String type = session.getAttribute("type_"+String.valueOf(i)).toString();
                questionSchema.setValue(name, type);
            }
            else
            {
                String name = questionSchema.getKeys().get(i);
                String value = session.getAttribute("cell_"+String.valueOf(i)+"_"+String.valueOf(j)).toString();;
                Instance ins = new Instance();
                ins.setValue(name, value);
                questionInstances.add(ins);
            }

        }
    }
    q.setQuestionSchema(questionSchema);
    q.setQuestionInstances(questionInstances);
    
    questions.add(q);
    
    session.setAttribute("questions", questions);
    response.sendRedirect("createQuestion.jsp");
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
