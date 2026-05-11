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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AIService {

	@Value("${google_api_key}")
	private String apiKey;
	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private static final String GEMINI_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3-flash-preview:generateContent";

	
	// AI service to fix code...
	public String analyzeCode(String code) {
		if(apiKey==null || apiKey.isEmpty()) {
			return "Error: Google API key is Missing. Please set GOOGLE_API_KEY environment variable.";
		}
		
		HttpHeaders headers=new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("x-goog-api-key", apiKey);
		
		String promptText = "You are a senior software engineer.\r\n"
				+ "\r\n"
				+ "Analyze the given code and respond STRICTLY in the following structured format. \r\n"
				+ "Do NOT add extra explanations, comments, or text outside this format.\r\n"
				+ "\r\n"
				+ "INPUT CODE:\r\n"
				+ "{code}\r\n"
				+ "\r\n"
				+ "OUTPUT FORMAT:\r\n"
//				+ "\r\n"
//				+ "1. BUGS:\r\n"
//				+ "- List only real bugs or logical errors (if none, write \"None\")\r\n"
//				+ "\r\n"
//				+ "2. IMPROVEMENTS:\r\n"
//				+ "- List concise improvements (max 5 points, no explanation)\r\n"
//				+ "\r\n"
				+ "FIXED ERROR:\r\n"
				+ "- If there is any bug fix the bug in the code, and comment only on that line where error present and write after 10 space 'Problem...' with serial no\r\n"
				+ "- Provide 2 improved versions of the code first is fixed the error and second is more optimized or Other way to write code,  \r\n"
				+ "- If generate optimized versions of the code, write at the top 'OPTIMIZED VERSION:' "
				+ "- Keep the same language as input\r\n"
				+ "- Use clean, production-quality code\r\n"
				+ "- Do NOT include comments inside the code except for error code in first version\r\n"
				+ "\r\n"
				+ "TIME COMPLEXITY:\r\n"
				+ "- Original: O(?)\r\n"
				+ "- Optimized: O(?)\r\n"
				+ "\r\n"
				+ "SPACE COMPLEXITY:\r\n"
				+ "- Original: O(?)\r\n"
				+ "- Optimized: O(?)\r\n"
				+ "\r\n"
				+ "RULES:\r\n"
				+ "- Do not generate random text\r\n"
				+ "- Do not explain reasoning\r\n"
				+ "- Do not exceed the defined structure\r\n"
				+ "- Keep response concise and precise \n Code \n" + code;
		
		// Gemini REST : request body JSON 
		// {"contents": [{"parts": [{"text": "...query?..."}] }]}
		
		Map<String, Object> body = new HashMap<>();
        List<Map<String, Object>> contents = new ArrayList<>();
        Map<String, Object> contentPart = new HashMap<>();
        List<Map<String, String>> parts = new ArrayList<>();
        Map<String, String> testPart = new HashMap<>();

        testPart.put("text", promptText);
        parts.add(testPart);
        contentPart.put("parts", parts);
        contents.add(contentPart);
        body.put("contents", contents);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);
		try {
			// now go to gemini server here...
			ResponseEntity<Map> response = restTemplate.postForEntity(GEMINI_URL, request, Map.class);
			
			if(response.getStatusCode() == HttpStatus.OK) {
                Map<String, Object> responseBody = response.getBody();
				
				if(responseBody == null || !responseBody.containsKey("candidates")) {
					return "Error: No candidates return from Gemini API";
				}
				
				// Gemini REST : response JSON
				// {"candidate": [{"content": [{"parts":[{"text": "...query?..."}] }]}     // for one user multiple response
				 
				 List<Map<String, Object>> candidates = (List<Map<String, Object>> )responseBody.get("candidates");
				 if(candidates.isEmpty()){
					 return "Error: Empty candidates list";
				 }
				 Map<String, Object> firstCandidate = candidates.get(0);
				 Map<String, Object> content = (Map<String, Object>) firstCandidate.get("content");
				 List<Map<String, String>> responseParts = (List<Map<String, String>>) content.get("parts");
						 
				 if(responseParts.isEmpty()) {
					 return "Error: No content parts in the response";
				 }				 
				 // final response...
				 return (String) responseParts.get(0).get("text");
			}
			else {
				return "Error analyzing code: " + response.getStatusCode();
			}
		}
		catch (HttpClientErrorException e) {
//			 System.out.println(e.getResponseBodyAsString());
			 return "Error in communicating with Google API service: " + e.getResponseBodyAsString();
		}
		
		
		
	}

}
