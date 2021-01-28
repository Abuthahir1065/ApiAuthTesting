package com.atmecs.test.testscripts;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.atmecs.test.constants.FileConstants;
import com.atmecs.test.testdata.DataProviderClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class LoginApi {

	@Test(dataProvider = "userLoginData", dataProviderClass = DataProviderClass.class)
	public void testApi(Object requestBody) throws IOException {

		System.out.println("Request Body : " + requestBody);
		Reporter.log("Request Body has been Specified");

		String requestUrl = "http://localhost:8000/auth/login";
		Reporter.log("Request Url has been sent");

		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("Content-Type", "application/json");
		RequestSpecification request = RestAssured.given().headers(headers);

		Response response = request.when().body(requestBody.toString()).post(new URL(requestUrl)).then().extract()
				.response();
		Reporter.log("Post request has been Done");
		int statusCode = response.getStatusCode();
		Reporter.log("Got Status Code :" + statusCode);
		String responseBody = response.getBody().asString();

		System.out.println(responseBody);
		Reporter.log("Got Response Body :" + responseBody);
		JsonPath jsonpath = response.jsonPath();

		JSONObject jsonObject = (JSONObject) requestBody;
		String token = jsonpath.get("access_token");
		Reporter.log("Got access Token");
		JSONObject obj = new JSONObject();
		obj.put("access_token", token);

		FileWriter file = new FileWriter(FileConstants.RESPONSE_BODY_PATH);

		file.write(obj.toJSONString());
		Reporter.log("Access Token has been written to the json file");
		file.close();
		Reporter.log("File has been Closed");
		String actualEmail = jsonObject.get("email").toString();
		String actualPassword = jsonObject.get("password").toString();
		System.out.println("Status Code : " + statusCode);
		System.out.println("Response Body : " + responseBody);
		System.out.println("Email : " + actualEmail);
		System.out.println("Password : " + actualPassword);
		System.out.println("Access Token : " + token);
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Assertion Passed");

	}
}
