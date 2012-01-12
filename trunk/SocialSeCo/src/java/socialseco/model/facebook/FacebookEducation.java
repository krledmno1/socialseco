/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.model.facebook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import socialseco.dao.facebookProperties.ConcentrationDAO;


/**
 *
 * @author krle
 */
@Entity
public class FacebookEducation implements Serializable {
        
        @Id @GeneratedValue
        private Long id;
        
        private String school;
        private String type;
        @ManyToMany(cascade=CascadeType.ALL)
        private List<FacebookConcentration> concentration;
   

    
  

        public FacebookEducation() {
            concentration = new ArrayList<FacebookConcentration>();
        }

        /**
         * @return the school
         */
        public String getSchool() {
            return school;
        }

        /**
         * @param school the school to set
         */
        public void setSchool(String school) {
            this.school = school;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            this.type = type;
        }

    /**
     * @return the concentration
     */
    public List<FacebookConcentration> getConecentration() {
        return concentration;
    }

    /**
     * @param conecentration the concentration to set
     */
    public void setConecentration(List<FacebookConcentration> conecentration) {
        this.concentration = conecentration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    void setValuesFrom(FacebookEducation read_edu) {
        if(read_edu!=null)
        {
            setId(read_edu.getId());
            for(FacebookConcentration con: read_edu.getConecentration())
            {
                ConcentrationDAO dao = new ConcentrationDAO();
                FacebookConcentration read_con = dao.readFacebookConcentrationByName(con.getName());
                con.setValues(read_con);
            }
            setConecentration(read_edu.getConecentration());
        }
    }

      
}
