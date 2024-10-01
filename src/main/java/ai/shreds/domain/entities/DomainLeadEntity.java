package ai.shreds.domain.entities; 
  
 import lombok.Data; 
  
 import java.math.BigDecimal; 
 import java.time.LocalDateTime; 
 import java.util.UUID; 
  
 @Data 
 public class DomainLeadEntity { 
     private UUID id; 
     private String name; 
     private String email; 
     private String company; 
     private String position; 
     private String location; 
     private String industry; 
     private BigDecimal revenue; 
     private String sourcePlatform; 
     private LocalDateTime fetchedAt; 
 }