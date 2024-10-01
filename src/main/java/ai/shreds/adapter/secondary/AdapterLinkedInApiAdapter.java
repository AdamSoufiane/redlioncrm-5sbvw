package ai.shreds.adapter.secondary; 
  
 import ai.shreds.application.ports.ApplicationExternalApiPort; 
 import ai.shreds.shared.SharedLeadDataDTO; 
 import com.fasterxml.jackson.databind.ObjectMapper; 
 import okhttp3.OkHttpClient; 
 import okhttp3.Request; 
 import okhttp3.Response; 
 import okhttp3.Interceptor; 
 import okhttp3.RequestBody; 
 import okhttp3.MediaType; 
 import okhttp3.FormBody; 
 import org.slf4j.Logger; 
 import org.slf4j.LoggerFactory; 
 import java.io.IOException; 
 import java.util.ArrayList; 
 import java.util.List; 
 import java.util.UUID; 
 import java.time.LocalDateTime; 
  
 public class AdapterLinkedInApiAdapter implements ApplicationExternalApiPort { 
  
     private static final Logger logger = LoggerFactory.getLogger(AdapterLinkedInApiAdapter.class); 
     private OkHttpClient client; 
     private ObjectMapper objectMapper; 
     private String accessToken; 
  
     public AdapterLinkedInApiAdapter() { 
         this.client = new OkHttpClient.Builder() 
             .addInterceptor(chain -> { 
                 Request originalRequest = chain.request(); 
                 Request.Builder builder = originalRequest.newBuilder().header("Authorization", "Bearer " + accessToken); 
                 Request newRequest = builder.build(); 
                 return chain.proceed(newRequest); 
             }).build(); 
         this.objectMapper = new ObjectMapper(); 
     } 
  
     @Override 
     public void connect() throws IOException { 
         // Implement OAuth 2.0 connection setup 
         logger.info("Connecting to LinkedIn API using OAuth 2.0"); 
         RequestBody formBody = new FormBody.Builder() 
             .add("grant_type", "client_credentials") 
             .add("client_id", "YOUR_CLIENT_ID")  // Client ID should be securely stored and retrieved 
             .add("client_secret", "YOUR_CLIENT_SECRET")  // Client Secret should be securely stored and retrieved 
             .build(); 
  
         Request request = new Request.Builder() 
             .url("https://www.linkedin.com/oauth/v2/accessToken") 
             .post(formBody) 
             .build(); 
  
         int retryCount = 0; 
         while (retryCount < 3) { 
             try (Response response = client.newCall(request).execute()) { 
                 if (!response.isSuccessful()) { 
                     logger.error("OAuth 2.0 authentication failed with status code: " + response.code()); 
                     throw new IOException("Unexpected code " + response); 
                 } 
                 String responseBody = response.body().string(); 
                 // Parse the access token from the response 
                 this.accessToken = objectMapper.readTree(responseBody).get("access_token").asText(); 
                 break; // Exit loop on success 
             } catch (IOException e) { 
                 logger.error("Error during OAuth 2.0 authentication", e); 
                 retryCount++; 
                 if (retryCount >= 3) { 
                     throw e; 
                 } 
                 try { 
                     Thread.sleep((long) Math.pow(2, retryCount) * 1000); 
                 } catch (InterruptedException ie) { 
                     Thread.currentThread().interrupt(); 
                     throw new IOException("Retry interrupted", ie); 
                 } 
             } 
         } 
     } 
  
     @Override 
     public List<SharedLeadDataDTO> fetchLeadData() throws IOException { 
         Request request = new Request.Builder() 
             .url("https://api.linkedin.com/v2/leads") 
             .build(); 
         Response response = null; 
         int retryCount = 0; 
         while (retryCount < 3) { 
             try { 
                 response = client.newCall(request).execute(); 
                 if (!response.isSuccessful()) { 
                     logger.error("API call failed with status code: " + response.code()); 
                     throw new IOException("Unexpected code " + response); 
                 } 
                 String responseBody = response.body().string(); 
                 return mapApiResponseToLeadData(responseBody); 
             } catch (IOException e) { 
                 logger.error("Error fetching lead data", e); 
                 retryCount++; 
                 if (retryCount >= 3) { 
                     throw e; 
                 } 
                 try { 
                     Thread.sleep((long) Math.pow(2, retryCount) * 1000); 
                 } catch (InterruptedException ie) { 
                     Thread.currentThread().interrupt(); 
                     throw new IOException("Retry interrupted", ie); 
                 } 
             } finally { 
                 if (response != null) { 
                     response.close(); 
                 } 
             } 
         } 
         return new ArrayList<>(); 
     } 
  
     private List<SharedLeadDataDTO> mapApiResponseToLeadData(String apiResponse) throws IOException { 
         List<SharedLeadDataDTO> leads = new ArrayList<>(); 
         // Assuming apiResponse is a JSON array of lead objects 
         try { 
             // Parse JSON and create SharedLeadDataDTO objects 
             // Example of parsing (actual implementation may vary) 
             SharedLeadDataDTO[] leadArray = objectMapper.readValue(apiResponse, SharedLeadDataDTO[].class); 
             for (SharedLeadDataDTO lead : leadArray) { 
                 leads.add(lead); 
             } 
         } catch (Exception e) { 
             logger.error("Error parsing API response", e); 
             throw new IOException("Error parsing API response", e); 
         } 
         return leads; 
     } 
 }