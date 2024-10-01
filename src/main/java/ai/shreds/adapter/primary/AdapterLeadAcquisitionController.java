package ai.shreds.adapter.primary; 
  
 import ai.shreds.application.ports.ApplicationFetchLeadInputPort; 
 import ai.shreds.shared.SharedFetchLeadRequestParams; 
 import ai.shreds.shared.SharedApiResponse; 
 import org.springframework.beans.factory.annotation.Autowired; 
 import org.springframework.web.bind.annotation.PostMapping; 
 import org.springframework.web.bind.annotation.RequestBody; 
 import org.springframework.web.bind.annotation.RestController; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import lombok.RequiredArgsConstructor; 
  
 @RestController 
 @RequiredArgsConstructor 
 public class AdapterLeadAcquisitionController { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterLeadAcquisitionController.class); 
     private final ApplicationFetchLeadInputPort fetchLeadInputPort; 
  
     @PostMapping("/api/leads/fetch") 
     public SharedApiResponse fetchLeads(@RequestBody SharedFetchLeadRequestParams params) { 
         logger.info("Received request to fetch leads with parameters: {}", params); 
         try { 
             validateRequestParams(params); 
             SharedApiResponse response = fetchLeadInputPort.fetchLeads(params); 
             logger.info("Successfully fetched leads."); 
             return response; 
         } catch (IllegalArgumentException e) { 
             logger.error("Validation error: {}", e.getMessage()); 
             return new SharedApiResponse("error", "Validation error: " + e.getMessage()); 
         } catch (Exception e) { 
             logger.error("Failed to fetch leads: {}", e.getMessage()); 
             return new SharedApiResponse("error", "Failed to fetch leads: " + e.getMessage()); 
         } 
     } 
  
     private void validateRequestParams(SharedFetchLeadRequestParams params) throws IllegalArgumentException { 
         if (params == null) { 
             throw new IllegalArgumentException("Request parameters cannot be null"); 
         } 
         if (params.getIndustry() != null && params.getIndustry().isEmpty()) { 
             throw new IllegalArgumentException("Industry parameter cannot be empty"); 
         } 
         if (params.getLocation() != null && params.getLocation().isEmpty()) { 
             throw new IllegalArgumentException("Location parameter cannot be empty"); 
         } 
         if (params.getCompanySize() != null && params.getCompanySize().isEmpty()) { 
             throw new IllegalArgumentException("Company size parameter cannot be empty"); 
         } 
         if (params.getPosition() != null && params.getPosition().isEmpty()) { 
             throw new IllegalArgumentException("Position parameter cannot be empty"); 
         } 
         // Additional validation logic as needed 
     } 
 }