package com.demo.springdemoproject;

import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;



@SpringBootTest(classes=SpringDemoProjectApplication.class,webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts={"/create_table.sql","/insert_table.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

class UserControllerTest {
	
	@LocalServerPort	// injects http server port that was allocated at runtime
    private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();	// it by default carries basic authentication headers, apache anything can be used as client
	
	HttpHeaders headers= new HttpHeaders();	// data structure for headers
	
	
	// Used to set the url along with random port and uri specified while testing
	private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
	
	@Test
	public void Testmessage() throws JSONException
	{
		
		HttpEntity<String> entity= new HttpEntity<>(null,headers);	// Http request or response entity consisting of header and body
		
		// extension of httpentity that adds httpstatuscode
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/message"), HttpMethod.GET, entity, String.class);
		
		String expected = "This application returns user details";
		
		Assertions.assertEquals(expected, response.getBody());
	}

	@Test
	public void Testaddition() throws JSONException
	{
		
		HttpEntity<String> entity= new HttpEntity<>(headers);
		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/add/1/2"), HttpMethod.GET, entity, String.class);
		
		String expected = "3";
		
		Assertions.assertEquals(expected, response.getBody());
	}
	
	@Test
	public void Testload() throws JSONException
	{
		
		String values="{\"id\":4,\"name\":\"xyza\", \"teamname\":\"ng proc\", \"salary\":1000}";
		//JSONObject json1=new JSONObject(values);
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> entity= new HttpEntity<>(values,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/load"), HttpMethod.POST, entity, String.class);
		
		
		Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
	@Test
	public void Testfindall() throws JSONException
	{
		HttpEntity<String> entity= new HttpEntity<>(null,headers);
		
		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/users/all"), HttpMethod.GET, entity, String.class);
		
		System.out.println(response);
		//Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
		Assertions.assertFalse(response.getBody().isEmpty());		
	}
	
	
}
