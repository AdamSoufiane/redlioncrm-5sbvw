package ai.shreds.infrastructure.external_services; 
  
 import ai.shreds.domain.entities.DomainLeadEntity; 
 import ai.shreds.domain.ports.DomainLeadProcessingPort; 
 import ai.shreds.infrastructure.logging.InfrastructureLogger; 
 import lombok.AllArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
  
 /** 
  * InfrastructureLeadProcessingServiceClient class implements the DomainLeadProcessingPort interface. 
  * This class is responsible for handling the processing of lead data within an external service infrastructure. 
  */ 
 @Slf4j 
 @AllArgsConstructor 
 public class InfrastructureLeadProcessingServiceClient implements DomainLeadProcessingPort { 
  
     private final InfrastructureLogger logger; 
  
     /** 
      * Processes the given lead data. 
      * 
      * @param lead the lead entity to be processed 
      */ 
     @Override 
     public void processLead(DomainLeadEntity lead) { 
         try { 
             // Simulate processing logic 
             // This could involve calling external services, applying business rules, etc. 
             logger.logInfo("Processing lead: " + lead.getId()); 
             // More processing logic here 
         } catch (IllegalArgumentException e) { 
             logger.logError("Invalid argument for lead: " + lead.getId() + ", Error: " + e.getMessage()); 
             throw new InfrastructureException("Invalid argument for lead", e); 
         } catch (Exception e) { 
             logger.logError("Error processing lead: " + lead.getId() + ", Error: " + e.getMessage()); 
             throw new InfrastructureException("Failed to process lead", e); 
         } 
     } 
 } 
  
 /** 
  * Custom exception class for handling infrastructure-related exceptions. 
  */ 
 class InfrastructureException extends RuntimeException { 
     public InfrastructureException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 }