package com.flipkart.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.flipkart.constant.ConstantVariables;

public class ReadFromProperty {

	private static Properties prop;

	static {
		try {
			InputStream input = new FileInputStream(ConstantVariables.PROPERTYFILEPATH);
			prop = new Properties();
			prop.load(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getFromPropetry(String key) {
		return prop.getProperty(key);
	}

}
