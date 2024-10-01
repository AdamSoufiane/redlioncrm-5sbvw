package ai.shreds.application.utils; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class ApplicationLogger { 
  
     private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class); 
  
     public void logInfo(String message) { 
         logger.info(message); 
     } 
  
     public void logError(String message) { 
         logger.error(message); 
     } 
  
     // Implementation Note: Consider using Lombok annotations if necessary for reducing boilerplate code. 
 }