package com.atmecs.test.testscripts;

import java.io.IOException;
import java.net.URL;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.atmecs.test.constants.FileConstants;
import com.atmecs.test.utilities.TokenReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetProductApiTest {

	@Test
	public void testApi() throws IOException, ParseException {
		String requestUrl = "http://localhost:8000/products/2";
		Reporter.log("Request Url has been sent");

		TokenReader token = new TokenReader();
		String bearerToken = token.readToken(FileConstants.RESPONSE_BODY_PATH, "access_token");
		Reporter.log("Got Bearer Token");

		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + bearerToken);
		Reporter.log("Authorization has been Done");

		Response response = request.get(new URL(requestUrl));
		Reporter.log("Get Request has been Done");

		int statusCode = response.getStatusCode();
		Reporter.log("Got Status Code " + statusCode);
		String responseBody = response.getBody().asString();
		Reporter.log("Got Response Body " + responseBody);
		JsonPath jsonPath = response.jsonPath();

		int id = jsonPath.get("id");
		String name = jsonPath.get("name");
		int cost = jsonPath.get("cost");
		int quantity = jsonPath.getInt("quantity");
		
		Assert.assertEquals(statusCode, 200, "Verified Status Code");
		Reporter.log("Assertion Passed");
		System.out.println("Status Code : " + statusCode);
		System.out.println("Response Body : " + responseBody);
		System.out.println("Id : " + id);
		System.out.println("Name : " + name);
		System.out.println("Cost : " + cost);
		System.out.println("Quantity : " + quantity);

	}
}
