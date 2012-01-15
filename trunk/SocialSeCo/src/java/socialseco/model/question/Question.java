/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.question;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 *
 * @author krle
 */
public class Question {
    
    private String operationID;

    private String question;
    private String description;

    private Schema questionSchema;
    private List<Instance> questionInstances;

    private String [] selectedUsers;  //this is what we have to populate
    private String [] confidence;

    //additiona stuff, not important
    private String userID;            
    private String password;
    private String returnAddress;
    private String what;                //type of social query
    private String where;               //platform select
    private String when;   //time limit

    public Question() {
        questionSchema = new Schema();
        questionInstances = new ArrayList<Instance>();
    }

     public static Question parseQuestion(String json)
    {
        Gson gson = new Gson();
        return gson.fromJson(json, Question.class);
    }
    
    
public void populateMappings(String json)
{
        JsonParser parser = new JsonParser();
        JsonObject obj = parser.parse(json).getAsJsonObject();
        JsonObject schemaObj = obj.get("schema").getAsJsonObject();
        Set<Map.Entry<String,JsonElement>> mappings = schemaObj.entrySet();
        
        Iterator<Entry<String,JsonElement>> it = mappings.iterator();
        while (it.hasNext())
        {
            Entry<String,JsonElement> entry = it.next();
            questionSchema.setValue(entry.getKey(),entry.getValue().getAsString());
        }
        
       
        
        JsonArray instanceObj = obj.get("instances").getAsJsonArray();
        
        for(int i= 0;i<instanceObj.size();i++)
        {
            JsonObject instance = instanceObj.get(i).getAsJsonObject();
            
            Set<Entry<String,JsonElement>> instanceMappings = instance.entrySet();
           
            Iterator<Entry<String,JsonElement>> insit = instanceMappings.iterator();
            
            Instance ins = new Instance();
            while(insit.hasNext())
            {
                Entry<String,JsonElement> insentry = insit.next();
                ins.setValue(insentry.getKey(),insentry.getValue().getAsString());
            }
            
            getQuestionInstances().add(ins);
        }       


}

    /**
     * @return the operationID
     */
    public String getOperationID() {
        return operationID;
    }

    /**
     * @param operationID the operationID to set
     */
    public void setOperationID(String operationID) {
        this.operationID = operationID;
    }

    /**
     * @return the question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * @param question the question to set
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the questionSchema
     */
    public Schema getQuestionSchema() {
        return questionSchema;
    }

    /**
     * @param questionSchema the questionSchema to set
     */
    public void setQuestionSchema(Schema questionSchema) {
        this.questionSchema = questionSchema;
    }

    

    /**
     * @return the selectedUsers
     */
    public String[] getSelectedUsers() {
        return selectedUsers;
    }

    /**
     * @param selectedUsers the selectedUsers to set
     */
    public void setSelectedUsers(String[] selectedUsers) {
        this.setSelectedUsers(selectedUsers);
    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the returnAddress
     */
    public String getReturnAddress() {
        return returnAddress;
    }

    /**
     * @param returnAddress the returnAddress to set
     */
    public void setReturnAddress(String returnAddress) {
        this.returnAddress = returnAddress;
    }

    /**
     * @return the what
     */
    public String getWhat() {
        return what;
    }

    /**
     * @param what the what to set
     */
    public void setWhat(String what) {
        this.what = what;
    }

    /**
     * @return the where
     */
    public String getWhere() {
        return where;
    }

    /**
     * @param where the where to set
     */
    public void setWhere(String where) {
        this.where = where;
    }

    /**
     * @return the when
     */
    public String getWhen() {
        return when;
    }

    /**
     * @param when the when to set
     */
    public void setWhen(String when) {
        this.when = when;
    }

    /**
     * @return the questionInstances
     */
    public List<Instance> getQuestionInstances() {
        return questionInstances;
    }

    /**
     * @param questionInstances the questionInstances to set
     */
    public void setQuestionInstances(List<Instance> questionInstances) {
        this.questionInstances = questionInstances;
    }

    /**
     * @return the confidence
     */
    public String[] getConfidence() {
        return confidence;
    }

    /**
     * @param confidence the confidence to set
     */
    public void setConfidence(String[] confidence) {
        this.confidence = confidence;
    }

   

}
