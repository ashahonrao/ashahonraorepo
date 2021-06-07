package com.rest.example.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class NumberControllerTest {
  
	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	@Test
	public void testgetPrimes() {
		HttpEntity<String> entity = new HttpEntity(null, headers);
		try {
			ResponseEntity<String> response = restTemplate.exchange(new URI("http://localhost:8080/primes/10"),
					HttpMethod.GET,entity,String.class);
			
			String expected = "{initial:10,primes:[2,3,5,7]}";
			try {
				JSONAssert.assertEquals(expected, response.getBody(), false);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
//   @Test
//   public void getProductsList() throws Exception {
//      String uri = "/primes/10";
//      MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
//         .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//      
//      int status = mvcResult.getResponse().getStatus();
//      assertEquals(200, status);
//      String content = mvcResult.getResponse().getContentAsString();
//      PrimeEntity primeEntity = super.mapFromJson(content, PrimeEntity.class);
//      assertTrue(primeEntity.getPrimes().size() > 0);
//   }
   
}
