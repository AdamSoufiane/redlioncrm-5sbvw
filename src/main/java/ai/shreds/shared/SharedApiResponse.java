package ai.shreds.shared; 
  
 import lombok.AllArgsConstructor; 
 import lombok.Data; 
 import lombok.NoArgsConstructor; 
  
 /** 
  * SharedApiResponse is a data transfer object used to encapsulate the response status and message. 
  *  
  * <p>This class is part of the Lead Acquisition Shred, which is designed to acquire lead data from external platforms such as LinkedIn. The primary purpose of this shred is to establish secure connections with these platforms, fetch raw lead data, ensure compliance with security protocols via the Security Service, and pass the data to the Lead Processing Shred for further processing.</p> 
  *  
  * <p>The class has two fields:</p> 
  * <ul> 
  *   <li>status: Indicates the operation's success or failure.</li> 
  *   <li>message: Provides additional information or details about the operation's result.</li> 
  * </ul> 
  */ 
 @Data 
 @AllArgsConstructor 
 @NoArgsConstructor 
 public class SharedApiResponse { 
     /** 
      * The status of the operation, indicating success or failure. 
      */ 
     private String status; 
  
     /** 
      * A message providing additional details about the operation's result. 
      */ 
     private String message; 
 }