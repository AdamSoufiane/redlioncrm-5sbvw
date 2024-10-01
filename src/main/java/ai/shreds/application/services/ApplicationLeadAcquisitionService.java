package ai.shreds.application.services; 
  
 import ai.shreds.adapter.primary.SharedFetchLeadRequestParams; 
 import ai.shreds.adapter.primary.SharedApiResponse; 
 import ai.shreds.application.ports.ApplicationFetchLeadInputPort; 
 import ai.shreds.application.ports.ApplicationExternalApiPort; 
 import ai.shreds.application.ports.ApplicationSecurityServicePort; 
 import ai.shreds.application.ports.ApplicationLeadProcessingOutputPort; 
 import ai.shreds.shared.SharedLeadDataDTO; 
 import lombok.RequiredArgsConstructor; 
 import lombok.extern.slf4j.Slf4j; 
 import java.util.List; 
 import java.util.stream.Collectors; 
  
 @Slf4j 
 @RequiredArgsConstructor 
 public class ApplicationLeadAcquisitionService implements ApplicationFetchLeadInputPort { 
     private final ApplicationExternalApiPort externalApiPort; 
     private final ApplicationSecurityServicePort securityServicePort; 
     private final ApplicationLeadProcessingOutputPort leadProcessingOutputPort; 
  
     @Override 
     public SharedApiResponse fetchLeads(SharedFetchLeadRequestParams params) { 
         try { 
             log.info("Validating request parameters."); 
             validateRequestParams(params); 
             log.info("Fetching leads from external API."); 
             List<SharedLeadDataDTO> leads = fetchLeadsFromExternalApi(); 
             log.info("Validating fetched lead data."); 
             List<SharedLeadDataDTO> validatedLeads = validateLeadData(leads); 
             log.info("Forwarding validated leads to processing service."); 
             forwardLeadsToProcessing(validatedLeads); 
             return new SharedApiResponse("success", "Lead data fetching and processing initiated."); 
         } catch (Exception e) { 
             log.error("Error during lead acquisition process: {}", e.getMessage()); 
             return new SharedApiResponse("error", e.getMessage()); 
         } 
     } 
  
     private void validateRequestParams(SharedFetchLeadRequestParams params) { 
         // Implement validation logic here 
         if (params == null) { 
             throw new IllegalArgumentException("Request parameters cannot be null"); 
         } 
         // Add more specific validation logic as needed 
     } 
  
     private List<SharedLeadDataDTO> fetchLeadsFromExternalApi() { 
         externalApiPort.connect(); 
         return externalApiPort.fetchLeadData(); 
     } 
  
     private List<SharedLeadDataDTO> validateLeadData(List<SharedLeadDataDTO> leads) { 
         return leads.stream() 
                     .filter(lead -> securityServicePort.validateData(lead)) 
                     .collect(Collectors.toList()); 
     } 
  
     private void forwardLeadsToProcessing(List<SharedLeadDataDTO> leads) { 
         leadProcessingOutputPort.processLeads(leads); 
     } 
 }