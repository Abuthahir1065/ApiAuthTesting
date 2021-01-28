package com.atmecs.test.constants;

import java.io.File;

public class FileConstants {

	public static final String BASE_PATH = System.getProperty("user.dir");
	public static final String RESOURCE_PATH = BASE_PATH + File.separator + "src" + File.separator + "main"
			+ File.separator + "resources";
	public static final String LOGIN_DATA_PATH = RESOURCE_PATH + File.separator + "loginData.json";
	public static final String JSON_DATA_PATH = RESOURCE_PATH + File.separator + "userData.json";
	public static final String RESPONSE_BODY_PATH = RESOURCE_PATH + File.separator + "responseBody.json";

}
