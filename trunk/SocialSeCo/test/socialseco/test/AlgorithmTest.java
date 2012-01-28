/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.test;

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

/**
 *
 * @author damian
 */
public class AlgorithmTest {
    
    public AlgorithmTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void test_letterPairSimilarity_Q1() {
        String method = Comparator.letterPairSimilarity;
        String platform = Comparator.facebookPlatform;
        int numberOfRecommendations = 5;
        
        // Here we get the Users we want to use for testing
        UserDAO dao = new UserDAO();
        dao.beginConversation();
        List<FacebookUser> users = getFacebookUsers(dao);
        // Get the first question
        Question question = getQuestion1();
        
        Comparator comparator = new Comparator();
        comparator.setMethod(method);
        comparator.setPlatform(platform);
        comparator.setNum(numberOfRecommendations);

        Question result = comparator.compare(question, users);
        
        // The transaction with the DB should be closed when we finish comparing the users
        dao.endConversation();
        
        processRecommendedUsers(question, result.getSelectedUsers());
    }
    
    public List<FacebookUser> getFacebookUsers(UserDAO dao) {
        List<FacebookUser> users = users = dao.readAllFacebookUsers_without_transaction();
        return users;
    }
    
    /*
     * Functions to get the Questions
     */
    public Question getQuestion1(){
        Question question = new Question();
        return question;
    }
    public Question getQuestion2(){
        Question question = new Question();
        return question;
    }
    public Question getQuestion3(){
        Question question = new Question();
        return question;
    }
    public Question getQuestion4(){
        Question question = new Question();
        return question;
    }
    public Question getQuestion5(){
        Question question = new Question();
        return question;
    }
    /*
     * Functions to process the recommended users
     */
    public void processRecommendedUsers(Question question, String[] users){
        System.out.println("*********************************************");
        System.out.println("Question: " + question.getQuestion());
        int i = 1;
        for(String user:users){
            System.out.println("(" + i + ") " + user);
            i++;
        }
    }
}
