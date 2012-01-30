/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.evaluation;

/**
 *
 * @author Rand
 */

import socialseco.dao.UserDAO;
import socialseco.controller.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import socialseco.model.facebook.FacebookUser;
import socialseco.model.question.Question;
import static org.junit.Assert.*;

public class comarisonAlgorithmValuation {
      public String[] questions={"what is the kind of music that ladi gaga sing ","which is the best resturan a milano","where can i learn italian in italia ","who can recomend me a horro film","what is the best sport game ever "};// array of the question to be used in our evaluation   
      public int[][] classification;// an array 20(user)*5(question) and every classification[i][j] represent how much the user i relative to the question j. 
                       // can take 3 values 1:"Highly recommended", 2:"Medium recommended" and 3:"Not recommended".
                       // or we can put a number represent exactly in which order the user we expect to be for the question .
      public Comparator comparator = new Comparator();
      public int [] users;//the users in our database.
    //  public int [][] recomoandedUsers=new int[5][5];//temporal array which save the first 5 users who have been recomended by an algorithm for a question 
    //  public int [] algorithmPoints=new int[4];
      public UserDAO dao = new UserDAO();
      Object DBusers ;
     // public List<FacebookUser> DBusers ;
      public comarisonAlgorithmValuation(){
           UserUpdater updater = new UserUpdater();
           dao = new UserDAO();
           dao.beginConversation();
            DBusers = updater.totalRecall("facebook");
            classification=new int[5][22];
            users=new int[22];
            comparator.setPlatform("facebook");
            comparator.setNum(5);
            users[0]=4;
            users[1]=12;
            users[2]=22;
            users[3]=27;
            users[4]=30;
            users[5]=31;////////////////7yyy
            users[6]=45;
            users[7]=57;
            users[8]=61;
            users[9]=68;
            users[10]=71;
            users[11]=79;
            users[12]=91;
            users[13]=106;
            users[14]=116;
            users[15]=129;
            users[16]=133;
            users[17]=175;
            users[18]=47;
            users[19]=64;
            users[20]=50;
            users[21]=177; 
            classification[0][0]=3;
            classification[0][1]=5;
            classification[0][2]=7;
            classification[0][3]=9;
            classification[0][4]=8;
            classification[0][5]=9;
            classification[0][6]=6;
            classification[0][7]=2;
            classification[0][8]=5;
            classification[0][9]=9;
            classification[0][10]=9;
            classification[0][11]=7;
            classification[0][12]=4;
            classification[0][13]=8;
            classification[0][14]=9;
            classification[0][15]=9;
            classification[0][16]=6;
            classification[0][17]=1;
            classification[0][18]=-1;
            classification[0][19]=-1;
            classification[0][20]=-1 ;
            classification[0][21]=-1 ;
            classification[1][0]=-1;
            classification[1][1]=6;
            classification[1][2]=-1;
            classification[1][3]=5;
            classification[1][4]=-1;
            classification[1][5]=1;
            classification[1][6]=-1;
            classification[1][7]=-1;
            classification[1][8]=5;
            classification[1][9]=6;
            classification[1][10]=2;
            classification[1][11]=-1;
            classification[1][12]=-1;
            classification[1][13]=-1;
            classification[1][14]=3;
            classification[1][15]=-1;
            classification[1][16]=-1;
            classification[1][17]=7;
            classification[1][18]=-1;
            classification[1][19]=5;
            classification[1][20]=4;
            classification[1][21]=-1;
            classification[2][0]=-1;
            classification[2][1]=4;
            classification[2][2]=-1;
            classification[2][3]=-1;
            classification[2][4]=-1;
            classification[2][5]=5;
            classification[2][6]=-1;
            classification[2][7]=-1;
            classification[2][8]=-1;
            classification[2][9]=-1;
            classification[2][10]=1;
            classification[2][11]=-1;
            classification[2][12]=-1;
            classification[2][13]=-1;
            classification[2][14]=4;
            classification[2][15]=-1;
            classification[2][16]=-1;
            classification[2][17]=-1;
            classification[2][18]=-1;
            classification[2][19]=2;
            classification[2][10]=3;
            classification[2][21]=-1;


            classification[3][0]=-1;
            classification[3][1]=-1;
            classification[3][2]=5;
            classification[3][3]=-1;
            classification[3][4]=-1;
            classification[3][5]=-1;
            classification[3][6]=-1;
            classification[3][7]=1;
            classification[3][8]=-1;
            classification[3][9]=-1;
            classification[3][10]=3;
            classification[3][11]=-1;
            classification[3][12]=4;
            classification[3][13]=-1;
            classification[3][14]=-1;
            classification[3][15]=-1;
            classification[3][16]=-1;
            classification[3][17]=1;
            classification[3][18]=2;
            classification[3][19]=-1;
            classification[3][20]=-1;
            classification[3][21]=-1;



            classification[4][0]=-1;
            classification[4][1]=-1;
            classification[4][2]=3;
            classification[4][3]=4;
            classification[4][4]=-1;
            classification[4][5]=-1;
            classification[4][6]=-1;
            classification[4][7]=-1;
            classification[4][8]=3;
            classification[4][9]=3;
            classification[4][10]=4;
            classification[4][11]=3;
            classification[4][12]=1;
            classification[4][13]=4;
            classification[4][14]=-1;
            classification[4][15]=4;
            classification[4][16]=4;
            classification[4][17]=3;
            classification[4][18]=3;
            classification[4][19]=3;
            classification[4][20]=4;
            classification[4][21]=2;
      }
      
      public int algorithmEvaluate(String methodName){//will return the number of point which represent the evaluation of the method !
          int [][] recomoandedUsers=new int[5][5];//temporal array which save the first 5 users who have been recomended by an algorithm for a question 
         // int [] algorithmPoints=new int[4];
          Question question = new Question();
          for(int i=0;i<5;i++){//for each i-question
              question.setQuestion(questions[i]);
              comparator.setMethod(methodName);
              if(DBusers.equals(null)){System.out.println("nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnulllllllll");}
              Question result = comparator.compare(question, DBusers);
              dao.endConversation();
              for(int j=0;j<5;j++){                 
                  recomoandedUsers[i][j]= Integer.parseInt(result.getSelectedUsers()[j]);//putting the results in selectedUser to use it later
              }
   
          }
          
        int algorithmPoints=0;          
        int userPosition=-100;
        for (int i=0;i<5;i++){//i-question
            for(int j=0;j<5;j++){//j-recomended user
                for(int k=0;k<22;k++){//k-user
                    if(recomoandedUsers[i][j]==users[k]){
                        userPosition=k;//in which row is the j-recomendeduser in the users array
                    }                                    
                }
                
                System.out.println(userPosition);
                if(classification[i][userPosition]==-1){
                    algorithmPoints=algorithmPoints;
                }
                else{
                    if(classification[i][userPosition]==j+1){//if our algorithm return the exact predicted order 
                        algorithmPoints=algorithmPoints+4;
                    }
                    else if((classification[i][userPosition]-j-1==1)||(classification[i][userPosition]-j-1==-1)){//if our algorithm return the exact predicted order-/+1
                        algorithmPoints=algorithmPoints+3;
                    }
                     else if((classification[i][userPosition]-j-1==2)||(classification[i][userPosition]-j-1==-2)){//if our algorithm return the exact predicted order-/+2
                        algorithmPoints=algorithmPoints+2;
                    }
                    else if((classification[i][userPosition]-j-1==3)||(classification[i][userPosition]-j-1==-3)){//if our algorithm return the exact predicted order-/+2
                        algorithmPoints=algorithmPoints+1;
                    }
                }

            }
        }
        System.out.println("algorithmPoints of "+methodName+"is:  "+algorithmPoints);
        return algorithmPoints;
      
      }

}
