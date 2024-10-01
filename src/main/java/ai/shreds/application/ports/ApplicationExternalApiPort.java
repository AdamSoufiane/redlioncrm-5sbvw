package ai.shreds.application.ports; 
  
 import ai.shreds.shared.SharedLeadDataDTO; 
 import java.util.List; 
  
 /** 
  * Interface for external API interactions required by the lead acquisition service. 
  */ 
 public interface ApplicationExternalApiPort { 
     /** 
      * Establishes a connection to the external API, handling authentication and necessary setup. 
      */ 
     void connect() throws ExternalApiException; 
  
     /** 
      * Fetches raw lead data from the external API. 
      * @return List of SharedLeadDataDTO representing the fetched lead data. 
      * @throws ExternalApiException if there is an error during data fetching. 
      */ 
     List<SharedLeadDataDTO> fetchLeadData() throws ExternalApiException; 
 } 
  
 /** 
  * Custom exception for handling errors related to external API interactions. 
  */ 
 class ExternalApiException extends Exception { 
     public ExternalApiException(String message) { 
         super(message); 
     } 
  
     public ExternalApiException(String message, Throwable cause) { 
         super(message, cause); 
     } 
 } 
  
 // Implementation Note: Use Lombok annotations (e.g., @Getter, @Setter, @AllArgsConstructor) for any DTOs or entities to reduce boilerplate code.