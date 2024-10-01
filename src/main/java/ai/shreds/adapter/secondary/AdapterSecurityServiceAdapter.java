package ai.shreds.adapter.secondary; 
  
 import ai.shreds.application.ports.ApplicationSecurityServicePort; 
 import ai.shreds.shared.SharedLeadDataDTO; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.stereotype.Service; 
  
 @Service 
 public class AdapterSecurityServiceAdapter implements ApplicationSecurityServicePort { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterSecurityServiceAdapter.class); 
  
     private final ExternalSecurityService externalSecurityService; 
  
     @Autowired 
     public AdapterSecurityServiceAdapter(ExternalSecurityService externalSecurityService) { 
         this.externalSecurityService = externalSecurityService; 
     } 
  
     /** 
      * Validates the lead data against security policies. 
      * @param leadData The lead data to validate. 
      * @return true if the lead data is valid, false otherwise. 
      */ 
     @Override 
     public boolean validateData(SharedLeadDataDTO leadData) { 
         if (leadData == null) { 
             logger.error("Lead data is null"); 
             return false; 
         } 
         logger.info("Validating lead data: {}", leadData); 
         return externalSecurityService.validateLeadData(leadData.getId(), leadData.getEmail(), leadData.getCompany()); 
     } 
  
     /** 
      * Checks if the current operation is authorized. 
      * @return true if the operation is authorized, false otherwise. 
      */ 
     @Override 
     public boolean authorizeOperation() { 
         logger.info("Authorizing operation"); 
         return externalSecurityService.checkAuthorization(); 
     } 
 } 
 