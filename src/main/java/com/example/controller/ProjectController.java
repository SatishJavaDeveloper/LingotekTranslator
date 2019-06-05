package com.example.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class ProjectController {

@Value("${iTalent.lingotek.api.url}")
private String apiUrl;
	
@Value("${iTalent.lingotek.api.token}")
private String apiToken;
	
@RequestMapping(value="projects/{community_id}", method = RequestMethod.GET)
public String getProjects(@PathVariable String community_id) {
	String response = "";
	RestTemplate restTemplate = new RestTemplate();
     
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    headers.add("Authorization", apiToken);
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/api/project")
            .queryParam("community_id", community_id);
    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
    ResponseEntity<String> result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, String.class);
    if (result.getStatusCode().equals(HttpStatus.OK)) {
    	response = result.getBody();
    }
    return response;
}
}
