package ai.shreds.shared; 
  
 import lombok.Getter; 
 import lombok.Setter; 
 import lombok.NoArgsConstructor; 
  
 @Getter 
 @Setter 
 @NoArgsConstructor 
 public class SharedFetchLeadRequestParams { 
     private String industry; 
     private String location; 
     private String companySize; 
     private String position; 
 }