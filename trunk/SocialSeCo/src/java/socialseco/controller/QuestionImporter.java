/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import com.google.gson.Gson;
import socialseco.model.question.Question;

/**
 *
 * @author krle
 */
public class QuestionImporter {
    public static Question parseQuestion(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Question.class);
    }
    
}
