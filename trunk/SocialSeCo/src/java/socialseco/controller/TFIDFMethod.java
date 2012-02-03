/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

/**
 *
 * @author dd
 */
public class TFIDFMethod {
    
    public static double computeTFIDFScore(Integer noDoc, String term, String crDoc, String[] allDocs) {
        
        double tf = LetterPairSimilarity.compareStrings(term, crDoc)/2;
        double noOfDocWithTerm = 1;
        
        for (int i = 0; i < allDocs.length; i++) {
            if (allDocs[i].contains(term)) {
                noOfDocWithTerm++;
            }
        }
        
        //tf-idf(t,f) = tf(t,d)*idf(f)
        double idf = Math.log(noDoc/noOfDocWithTerm); 
        
        return tf*idf;
    }
    
}
