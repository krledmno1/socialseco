<%-- 
    Document   : test
    Created on : Jan 19, 2012, 12:55:26 PM
    Author     : krle
--%>
<%@page import="socialseco.controller.Comparator"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="socialseco.controller.LetterPairSimilarity"%>
<%
String first = (String)request.getParameter("first")!=null?(String)request.getParameter("first").toString(): null;
String second = (String)request.getParameter("second")!=null?(String)request.getParameter("second").toString(): null;


if(first!=null && second !=null)
{
Comparator c = new Comparator();
first = c.preprocess(first);
second = c.preprocess(second);

String [] words1  = first.split("\\s");
String [] words2  = second.split("\\s");


List<Double> eval = new ArrayList<Double>();
for(int i = 0;i<words1.length;i++)
{
    Double max=0.0;
    int count = 1;
       for(int j = 0;j<words2.length;j++)
       {
           Double result=  LetterPairSimilarity.compareStrings(words1[i], words2[j]);
           if(max<result)
           {
                max = result;
                count =1;
           }
           else
           {
               if(max.equals(result))
               {
                  count++; 
               }
           }
       }
    eval.add(count*max);
}

Double sum=0.0;
for(Double d:eval)
{
    sum+=d;
}

sum = sum/eval.size();

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        Result: <%=sum %>
        
       <% }
%>
        <form action="test.jsp" method="post">
            <input type="text" name="first" />
            <input type="text" name="second" />
            <input type="submit" name="submit" value="sub" />
        </form>
    </body>
</html>
