package ai.shreds.infrastructure.logging; 
  
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
  
 public class InfrastructureLogger { 
  
     private static final Logger logger = LoggerFactory.getLogger(InfrastructureLogger.class); 
  
     public void logInfo(String message) { 
         logger.info(message); 
     } 
  
     public void logError(String message) { 
         logger.error(message); 
     } 
 }