package com.mycompany.util;

/**
 * The class contains utility methods related to string Operations.
 * @author  Suruchi
 */
public class StringUtil {
    
    public static String toCommaSeparationString(Object[] items){
       StringBuilder sb = new StringBuilder(); 
       for(Object item:items){
         sb.append(item).append(",");  
       }
       if(sb.length()>0){
           sb.deleteCharAt(sb.length()-1);
       }
        return sb.toString();
    }
}
