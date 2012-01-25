<%-- 
    Document   : comparisonAlgorithmsEvaluation
    Created on : 25-gen-2012, 23.40.35
    Author     : Rand
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
String[] questions={"question1","question2","question3","question4","question5"};// array of the question to be used in our evaluation
 questions = new String[5];    
int[][] classification;// an array 20(user)*5(question) and every classification[i][j] represent how much the user i relative to the question j. 
                       // can take 3 values 1:"Highly recommended", 2:"Medium recommended" and 3:"Not recommended".
                       // or we can put a number represent exactly in which order the user we expect to be for the question .
String [] users=new String[20];//the users in our database.
classification = new int[20][5];    
String [][] recomoandedUsers=new String[5][5];//temporal array which save the first 5 users who have been recomended by an algorithm for a question 
int [] algorithmPoints=new int[4];
for (int i=0;i<5;i++){
//TODO here we have apply our algorithms and will insert the results of recomoandedUsers
}
int userPosition=-100;
for (int i=0;i<5;i++){//i-question
    for(int j=0;j<5;j++){//j-recomended user
        for(int k=0;k<5;k++){//k-user
            if(recomoandedUsers[i][j].equals(users[k])){
                userPosition=k;//in which row is the j-recomendeduser in the users array
            }                                    
        }
        if(classification[userPosition][i]==j){//if our algorithm return exactly 
            algorithmPoints[1]=algorithmPoints[1]+3;
        }
        else if((classification[userPosition][i]-j==1)||(classification[userPosition][i]-j==-1)){
            algorithmPoints[1]=algorithmPoints[1]+2;
        }
         else if((classification[userPosition][i]-j==2)||(classification[userPosition][i]-j==-2)){
            algorithmPoints[1]=algorithmPoints[1]+1;
        }
        
    }
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
