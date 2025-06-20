package com.flipkart.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.flipkart.utilities.ReadFromProperty;

public class BaseClass{
	
	@BeforeMethod
	protected void startBrowser() {
		BrowserFactory.initDriver(ReadFromProperty.getFromPropetry("browser"), ReadFromProperty.getFromPropetry("url"));
	}
	
	@AfterMethod
	protected void quiteBrowser() {
		BrowserFactory.endDriver();
	}

}
