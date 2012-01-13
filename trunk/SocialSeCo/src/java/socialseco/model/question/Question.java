/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.question;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
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

public void populateMappings(String json)
{
        JsonParser parser = new JsonParser();
        JsonElement el = parser.parse(json);
        JsonObject obj = el.getAsJsonObject();
        JsonObject schemaObj = obj.get("schema").getAsJsonObject();
        Set<Entry<String,JsonElement>> mappings = schemaObj.entrySet();
        Entry<String,JsonElement> [] arrayMappings = (Entry<String,JsonElement> [])mappings.toArray();
        
        for(int i = 0;i<arrayMappings.length;i++)
        {
            questionSchema.setValue(arrayMappings[i].getKey(),arrayMappings[i].getValue().getAsString());
        }
        
        JsonArray instanceObj = obj.get("instances").getAsJsonArray();
        
        for(int i= 0;i<instanceObj.size();i++)
        {
            JsonObject instance = instanceObj.get(i).getAsJsonObject();
            
            Set<Entry<String,JsonElement>> instanceMappings = instance.entrySet();
            Entry<String,JsonElement> [] arrayInstanceMappings = (Entry<String,JsonElement> [])instanceMappings.toArray();
            
            Instance ins = new Instance();
            for(int j= 0;j<arrayInstanceMappings.length;j++)
            {
                ins.setValue(arrayInstanceMappings[j].getKey(),arrayInstanceMappings[j].getValue().getAsString());
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

   

}
