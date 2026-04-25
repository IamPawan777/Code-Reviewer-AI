package ai.review.review_dot_ai.service;

import java.net.http.HttpRequest;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

	@Value("${google.api.key}")
	private String apiKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
//	private static final String GEMINI_API = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent"; 
	private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent";

	
	
	public String analyzeCode(String code) {
		if(apiKey==null || apiKey.isEmpty()) {
			return "Error: Google API key is Missing. Please set GOOGLE_API_KEY environment variable.";
		}
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-goog-api-key", apiKey);
		
		String promptText = "You are a senior software engineer, Analyse the following code:\n - Indentify bug \n - Suggest Improvement\n - Suggest optimized code version \n\n Code \n"
				+code;
		
		
		// Gemini REST : request JSON
		// {"contents": [{"parts": [{"text": "....."}] }]}
		
		Map<String, Object> body=new HashMap<>();
		List<Map<String, Object>> contents = new ArrayList<>();
		
		Map<String, Object> contentPart=new HashMap<>();
		List<Map<String, String>> parts=new ArrayList<>();
		Map<String, String> textPart=new HashMap<>();
		
		textPart.put("text", promptText);
		parts.add(textPart);
		contentPart.put("parts", parts);
		contents.add(contentPart);
		body.put("contents", contents);
		
		HttpEntity<Map<String, Object>> request=new HttpEntity<>(body, headers);
		
		try {
			ResponseEntity<Map> response = restTemplate.postForEntity(GEMINI_URL, request, Map.class);
			
			if(response.getStatusCode()==HttpStatus.OK) {
				// Gemini REST : response JSON
				// {"candidate": [{"content": [{"parts":[{"text": "......?"}] }]}
				 Map<String, Object> responseBody = response.getBody();

				 if(responseBody == null || !responseBody.containsKey("candidates")) {
					 return "Error: No candidates return from Gemini API";
				 }				 
				 
				 List<Map<String, Object>> candidates = (List<Map<String, Object>>) responseBody.get("candidates");
				 if(candidates.isEmpty()){
					 return "Error: Empty candidates list";
				 }
				 Map<String, Object> firstCandidate = candidates.get(0);
				 Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
				 List<Map<String, String>> responseParts = (List<Map<String, String>>) content.get("parts");
						 
				 if(responseParts.isEmpty()) {
					 return "Error: No content parts in the response";
				 }				 
				 return (String) responseParts.get(0).get("text");
				
			}
			else {
				return "Error analyzing code: " + response.getStatusCode();
			}
		}
		catch (Exception e) {
			 return "Error in communicating with Google API service: " + e.getMessage();
		}
		
		
		
	}

}
