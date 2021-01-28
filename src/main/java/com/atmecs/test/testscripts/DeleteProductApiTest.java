package com.atmecs.test.testscripts;

import java.net.MalformedURLException;
import java.net.URL;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.atmecs.test.constants.FileConstants;
import com.atmecs.test.utilities.TokenReader;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DeleteProductApiTest {
	@Test
	public void deleteTestApi() throws MalformedURLException {
		String requestUrl = "http://localhost:8000/products/9";
		Reporter.log("Request Url has been sent");

		TokenReader token = new TokenReader();
		String bearerToken = token.readToken(FileConstants.RESPONSE_BODY_PATH, "access_token");
		Reporter.log("Got Bearer Token");
		RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + bearerToken);
		Reporter.log("Authorization has been Done");
		Response response = request.delete(new URL(requestUrl)).then().extract().response();
		Reporter.log("Delete Request has been Done");
		int statusCode = response.getStatusCode();

		Assert.assertEquals(statusCode, 200);
		Reporter.log("Got Status Code " + statusCode);
		System.out.println("Status Code : " + statusCode);

	}
}
