package ai.shreds.application.exceptions; 
  
 /** 
  * Custom exception class for application-specific exceptions. 
  */ 
 public class ApplicationException extends Exception { 
  
     private static final long serialVersionUID = 1L; 
  
     /** 
      * Constructs a new ApplicationException with the specified detail message. 
      *  
      * @param message the detail message 
      */ 
     public ApplicationException(String message) { 
         super(message); 
     } 
  
     /** 
      * Constructs a new ApplicationException with the specified detail message and cause. 
      *  
      * @param message the detail message 
      * @param cause the cause of the exception 
      */ 
     public ApplicationException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }