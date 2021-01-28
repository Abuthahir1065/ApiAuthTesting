package com.atmecs.test.testdata;

import org.testng.annotations.DataProvider;

import com.atmecs.test.constants.FileConstants;
import com.atmecs.test.utilities.JsonReader;

public class DataProviderClass {

	@DataProvider(name = "createUserData")
	public static Object[][] getCreateUserData() {
		JsonReader jsonReader = new JsonReader(FileConstants.JSON_DATA_PATH);
		Object object = jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0] = object;
		return data;
	}

	@DataProvider(name = "userLoginData")
	public static Object[][] getLoginData() {
		JsonReader jsonReader = new JsonReader(FileConstants.LOGIN_DATA_PATH);
		Object object = jsonReader.parse();
		Object[][] data = new Object[1][1];
		data[0][0] = object;

		return data;
	}

}
