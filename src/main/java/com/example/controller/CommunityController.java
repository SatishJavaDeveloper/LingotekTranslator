package com.example.controller;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class CommunityController {

	@Value("${iTalent.lingotek.api.url}")
	private String apiUrl;
	
	@Value("${iTalent.lingotek.api.token}")
	private String apiToken;
	
	@RequestMapping(value="community", method = RequestMethod.GET)
	public Object getCommunities() {
	//	JSONObject response = new JSONObject();
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.add("Authorization", apiToken);
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> result = restTemplate.exchange(apiUrl + "/api/community", HttpMethod.GET, entity, String.class);
	    if (result.getStatusCode().equals(HttpStatus.OK)) {
	    	response = result.getBody();
	    }
	    return response;
	    
	}
}
