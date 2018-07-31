package apiTestScript;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Country;
import restGenericMethod.RestClientMethod;
import testBase.TestBase;

public class RestApiClientTest {
	String url;
	String baseUrl;
	String serviceUrl;
	TestBase testbase;
	ObjectMapper mapper;

	@BeforeMethod
	public void setUp() throws IOException {
		testbase = new TestBase();
		baseUrl = TestBase.prop.getProperty("url");
		serviceUrl = TestBase.prop.getProperty("apiUrl");
		url = baseUrl + serviceUrl;
		mapper = new ObjectMapper();
	}

	@Test(priority = 1)
	public void getCountries() {

		Response response = RestClientMethod.get(url);
		System.out.println(response.asString());
		assertEquals(TestBase.SUCCESS_RESPONSE_CODE_200, response.getStatusCode());

	}

	@Test(enabled = false, priority = 1)
	public void registerCountry() throws JsonProcessingException {
		Country cObj = new Country(10, "Rasol", "5000");
		// //object to json in string
		String countryjsonString = mapper.writeValueAsString(cObj);
		Response response = RestClientMethod.post(baseUrl, serviceUrl, countryjsonString);
		System.out.println("POST API Response :" + response.asString());
		assertEquals(TestBase.SUCCESS_RESPONSE_CODE_200, response.getStatusCode());

	}

	@Test(enabled = false)
	public void getCountryById() {
		int id = 4;

		String rspBody = RestAssured.given().pathParam("conId", id).when().get(url + "/{conId}").asString();
		System.out.println(rspBody);

	}

	@Test(enabled = true, priority = 1)
	public void modifyCountry() throws JsonProcessingException {
		Country cObj = new Country();
		cObj.setId(6);
		cObj.setCountryName("Sam");
		cObj.setPopulation("2500");
		String objStr = mapper.writeValueAsString(cObj);
		Response response = RestClientMethod.put(url, objStr);
		System.out.println("PUT API Response :" + response.asString());
		assertEquals(TestBase.SUCCESS_RESPONSE_CODE_200, response.getStatusCode());
	}

	@Test(enabled = false, priority = 2)
	public void removeCountry() {
		int id = 5;
		Response response = RestClientMethod.delete(url, id);
		System.out.println("DELETE API Response :No Content return ");
		assertEquals(TestBase.SUCCESS_RESPONSE_NOCONTENT_204, response.getStatusCode());
	}

}
