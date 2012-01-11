/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.facebook.Json.Properties;

import socialseco.facebook.Json.JsonEntity;

/**
 *
 * @author krle
 */
public class JsonProperty {
        
    
        private String name;
        private String category;
        private String id;
       
        
        
        public JsonProperty() {
         }
        /**
         * @return the id
         */
        public String getId() {
            return id;
        }

        /**
         * @param id the id to set
         */
        public void setId(String id) {
            this.id = id;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }
       

      
        /**
         * @return the category
         */
        public String getCategory() {
            return category;
        }

        /**
         * @param category the category to set
         */
        public void setCategory(String category) {
            this.category = category;
        }
    
}
