/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.question;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krle
 */
public class KeyValueMapping {
   private List<KeyValue> values;

    public KeyValueMapping() {
    }

   public List<String> getKeys()
   {
       List<String> keys = new ArrayList<String>();
       for(KeyValue k:values)
       {
           keys.add(k.getKey());
       }
       return keys;
   }
   
   public String getValue(String key)
   {
       for(KeyValue k:values)
       {
           if(k.getKey().equals(key))
           {
               return k.getValue();
           }
       }
       return null;
   }
   
   public void setValue(String key, String value)
   {
       boolean exists = false;
       for(KeyValue k:values)
       {
          if(k.getKey().equals(key))
          {
              k.setValue(value);
              exists = true;
          } 
       }
       if(!exists)
       {
           values.add(new KeyValue(key,value));
       }
   }
   
    /**
     * @return the values
     */
    public List<KeyValue> getValues() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setValues(List<KeyValue> values) {
        this.values = values;
    }
}
