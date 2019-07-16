package com.PortalNet.PortalNet;

import java.net.URL;

import javax.sound.sampled.Port;
import javax.swing.Spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.polarising.PortalNet.PortalNetApplication;
import com.polarising.PortalNet.model.Client;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PortalNetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class updateClientTest {
	
	@LocalServerPort
	private int port;
	
	private String url = "http://localhost:" + port + "/client/{clientId}";
	
	@Test
	public void updateClientUnitTest()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		TestRestTemplate restTemplate = new TestRestTemplate();
		
		String body = "{\"clientId\": 1,\"clientNumber\": \"201907163385\",\"nif\": 1111111,\"name\": \"Andre\",\"address\": \"Rua da Alegria\",\"postalCode\": \"1000-245\",\"city\": \"Lisboa\",\"mobilePhone\": 911111111,\"email\": \"andre@polarising.com\",\"gender\": \"male\",\"password\": \"bernardoAdmin\",\"entryDate\": \"Tue Jul 16 09:56:45 BST 2019\",\"endContract\": \"27/06/2020\",\"numberOfServices\": 2,\"serviceName\": \"Blue Master 1\",\"monthlyPay\": \"100€\",\"fraudulent\": true,\"status\": true,\"birthDate\": \"18/01/1989\"}";
		
		HttpEntity<String> entity = new HttpEntity<String>(body, null);

		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
		
		System.err.println(responseEntity.getBody());
	}

}
