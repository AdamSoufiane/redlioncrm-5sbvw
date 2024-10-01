package ai.shreds.domain.value_objects; 
  
 import ai.shreds.domain.entities.DomainLeadEntity; 
 import lombok.Getter; 
 import java.util.UUID; 
 import java.math.BigDecimal; 
 import java.time.LocalDateTime; 
  
 /** 
  * DomainLeadDataValue is a value object that encapsulates DomainLeadEntity. 
  * It acts as a wrapper or decorator to DomainLeadEntity, providing an additional layer 
  * of abstraction and potentially enhancing the domain model with more behavior or constraints 
  * specific to the business logic. 
  */ 
 @Getter 
 public class DomainLeadDataValue { 
  
     private final DomainLeadEntity leadEntity; 
  
     /** 
      * Constructor to initialize the lead entity. 
      * 
      * @param leadEntity The lead entity to be encapsulated. 
      */ 
     public DomainLeadDataValue(DomainLeadEntity leadEntity) { 
         if (leadEntity == null) { 
             throw new IllegalArgumentException("Lead entity cannot be null."); 
         } 
         this.leadEntity = leadEntity; 
     } 
  
     /** 
      * Gets the ID of the lead entity. 
      * 
      * @return the UUID of the lead entity. 
      */ 
     public UUID getId() { 
         return leadEntity.getId(); 
     } 
  
     /** 
      * Gets the name of the lead entity. 
      * 
      * @return the name of the lead entity. 
      */ 
     public String getName() { 
         return leadEntity.getName(); 
     } 
  
     /** 
      * Gets the email of the lead entity. 
      * 
      * @return the email of the lead entity. 
      */ 
     public String getEmail() { 
         return leadEntity.getEmail(); 
     } 
  
     /** 
      * Gets the company of the lead entity. 
      * 
      * @return the company of the lead entity. 
      */ 
     public String getCompany() { 
         return leadEntity.getCompany(); 
     } 
  
     /** 
      * Gets the position of the lead entity. 
      * 
      * @return the position of the lead entity. 
      */ 
     public String getPosition() { 
         return leadEntity.getPosition(); 
     } 
  
     /** 
      * Gets the location of the lead entity. 
      * 
      * @return the location of the lead entity. 
      */ 
     public String getLocation() { 
         return leadEntity.getLocation(); 
     } 
  
     /** 
      * Gets the industry of the lead entity. 
      * 
      * @return the industry of the lead entity. 
      */ 
     public String getIndustry() { 
         return leadEntity.getIndustry(); 
     } 
  
     /** 
      * Gets the revenue of the lead entity. 
      * 
      * @return the revenue of the lead entity. 
      */ 
     public BigDecimal getRevenue() { 
         return leadEntity.getRevenue(); 
     } 
  
     /** 
      * Gets the source platform of the lead entity. 
      * 
      * @return the source platform of the lead entity. 
      */ 
     public String getSourcePlatform() { 
         return leadEntity.getSourcePlatform(); 
     } 
  
     /** 
      * Gets the fetched timestamp of the lead entity. 
      * 
      * @return the fetched timestamp of the lead entity. 
      */ 
     public LocalDateTime getFetchedAt() { 
         return leadEntity.getFetchedAt(); 
     } 
 }