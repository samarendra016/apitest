package apiTestScript;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
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

	@BeforeMethod
	public void setUp() throws IOException{
		testbase=new TestBase();
		 baseUrl=TestBase.prop.getProperty("url");
		serviceUrl=TestBase.prop.getProperty("apiUrl");
	url=baseUrl+serviceUrl;
	}
	@Test()
	public void getCountries() {
		
		Response response=RestClientMethod.get(url);
		String bodyData=response.asString();
		System.out.println(bodyData);
		int actStsCode=response.getStatusCode();
		assertEquals(TestBase.SUCCESS_RESPONSE_CODE_201, actStsCode);

	}

	@Test(enabled=false)
	public void registerCountry() throws JsonProcessingException {
		RestAssured.baseURI = baseUrl;
		RequestSpecification request = RestAssured.given();
		Country cObj=new Country(10,"Rasol","5000");
		ObjectMapper mapper=new ObjectMapper();
		//object to json in string
		String countryjsonString=mapper.writeValueAsString(cObj);
		request.header("Content-Type", "application/json");
		request.body(countryjsonString);
		Response response = request.post(serviceUrl);
		int expStsCode = 200;
		int statusCode = response.getStatusCode();
		System.out.println(statusCode);
		assertEquals(expStsCode, statusCode);

	}

	@Test(enabled=true)
	public void getCountryById() {
		 int id =4;
		        
		String rspBody=RestAssured.given().
		        pathParam("conId",id).
		    when().
		        get(url+"/{conId}").asString();
		   System.out.println(rspBody);
		       
	}

}
