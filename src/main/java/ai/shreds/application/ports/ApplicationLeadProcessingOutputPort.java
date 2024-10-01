package ai.shreds.application.ports; 
  
 import ai.shreds.shared.SharedLeadDataDTO; 
 import java.util.List; 
  
 /** 
  * Interface defining the output port for processing acquired leads. 
  *  
  * Note: Ensure Lombok annotations are used for getters and setters if necessary. 
  */ 
 public interface ApplicationLeadProcessingOutputPort { 
     /** 
      * Processes the list of leads. 
      * 
      * @param leads List of SharedLeadDataDTO containing the lead data to be processed. 
      */ 
     void processLeads(List<SharedLeadDataDTO> leads); 
 }