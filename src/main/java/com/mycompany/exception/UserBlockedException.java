package com.mycompany.exception;

public class UserBlockedException extends Exception {
    /**
     * create user object with error description.
     */
    public UserBlockedException(){
        
    }
    
    public UserBlockedException(String errDesc){
      super(errDesc);  
    }
    
}
