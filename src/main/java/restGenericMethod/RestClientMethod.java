package restGenericMethod;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestClientMethod {
	
	public static Response get(String url){
		Response rsp=RestAssured.get(url);
		
		return rsp;
	}

}
