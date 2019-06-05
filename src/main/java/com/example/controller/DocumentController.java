package com.example.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.vo.Document;

@RestController
public class DocumentController {

	@Value("${iTalent.lingotek.api.url}")
	private String apiUrl;
	
	@Value("${iTalent.lingotek.api.token}")
	private String apiToken;
	
	@RequestMapping(value = "document", method = RequestMethod.POST)
	public String addDocument(@RequestBody Document document) {
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		    headers.add("Authorization", apiToken);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		map.add("content", document.getContent());
		map.add("locale_code", document.getLocale_code() );
		map.add("project_id", document.getProject_id() );
		map.add("title", document.getTitle());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> result = restTemplate.postForEntity( apiUrl + "/api/document", request , String.class );
		 if (result.getStatusCode().equals(HttpStatus.ACCEPTED)) {
		    	response = result.getBody();
		    }
		    return response;
	}
	
	@RequestMapping(value = "document/{id}/translation/{locale_code}", method = RequestMethod.POST)
	public String  createTranslation(@PathVariable String id, @PathVariable String locale_code) {
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
		 HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		    headers.add("Authorization", apiToken);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
		// map.add("content", document.getContent());
		map.add("locale_code", locale_code );
		// map.add("project_id", document.getProject_id() );
		// map.add("title", document.getTitle());

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

		ResponseEntity<String> result = restTemplate.postForEntity( apiUrl + "/api/document/" + id + "/translation", request , String.class );
		 if (result.getStatusCode().equals(HttpStatus.CREATED)) {
		    	response = result.getBody();
		    }
		    return response;
	}
	
	@RequestMapping(value="document/{id}/content/{locale_code}", method = RequestMethod.GET)
	public String getProjects(@PathVariable String id, @PathVariable String locale_code) {
		String response = "";
		RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.ALL));
	    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
	    headers.add("Authorization", apiToken);
	    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/api/document/" + id + "/content")
	            .queryParam("locale_code", locale_code);
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	    ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
	    if (result.getStatusCode().equals(HttpStatus.OK)) {
	    	response = result.getBody();
	    }
	    return response;
	}
	
	
}
