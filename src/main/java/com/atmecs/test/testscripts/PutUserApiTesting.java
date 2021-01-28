package com.atmecs.test.testscripts;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.atmecs.test.constants.FileConstants;
import com.atmecs.test.testdata.DataProviderClass;
import com.atmecs.test.utilities.TokenReader;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class PutUserApiTesting {
	@Test(dataProvider = "createUserData", dataProviderClass = DataProviderClass.class)

	public void putData(Object requestBody) throws MalformedURLException {
		System.out.println("Request Body : " + requestBody);
		Reporter.log("Request Body has been Specified");

		String requestUrl = "http://localhost:8000/products/4";
		Reporter.log("Request Body has been Specified");

		TokenReader token = new TokenReader();
		String bearerToken = token.readToken(FileConstants.RESPONSE_BODY_PATH, "access_token");
		Reporter.log("Got Bearer Token");
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-Type", "application/json");
		headers.put("Authorization", "Bearer " + bearerToken);
		Reporter.log("Authorization has been Done");
		RequestSpecification request = RestAssured.given().headers(headers);
		Response response = request.when().body(requestBody.toString()).put(new URL(requestUrl)).then().extract()
				.response();
		Reporter.log("Put Request Method has been Done");
		int statusCode = response.getStatusCode();
		Reporter.log("Got Status Code :" + statusCode);

		String responseBody = response.getBody().asString();
		Reporter.log("Got Response Body :" + responseBody);

		JsonPath jsonpath = response.jsonPath();
		int id = jsonpath.getInt("id");
		String name = jsonpath.getString("name");
		int cost = jsonpath.getInt("cost");
		int quantity = jsonpath.getInt("quantity");
		System.out.println("Status Code :" + statusCode);
		System.out.println("Response Body :" + responseBody);
		Assert.assertEquals(statusCode, 200);
		System.out.println("Id : " + id);

		System.out.println("Name : " + name);

		System.out.println("Cost : " + cost);
		System.out.println("Quantity :" + quantity);
		JSONObject jsonObject = (JSONObject) requestBody;
		Assert.assertEquals(name, jsonObject.get("name").toString());
		Reporter.log("Assertion Passed");

	}
}
