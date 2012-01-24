/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package socialseco.controller;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author krle
 */
public class MaxWordSimilarity {
    
    
    public static double compareMinDistanceWords(String questionBag, String userBag)
    {
         
                String [] words1  = questionBag.split("\\s");
                String [] words2  = userBag.split("\\s");

                List<Double> eval = new ArrayList<Double>();
                for(int i = 0;i<words1.length;i++)
                {
                    Double max=0.0;
                    int count = 1;
                       for(int j = 0;j<words2.length;j++)
                       {
                           Double result=  LetterPairSimilarity.compareStrings(words1[i], words2[j]);
                           if(max<result)
                           {
                                max = result;
                                count =1;
                           }
                           else
                           {
                               if(max.equals(result))
                               {
                                  count++; 
                               }
                           }
                       }
                    eval.add(count*max);
                }

                Double sum=0.0;
                for(Double d:eval)
                {
                    sum+=d;
                }

                sum = sum/eval.size();
                
                return sum;
               
            
    }
}
