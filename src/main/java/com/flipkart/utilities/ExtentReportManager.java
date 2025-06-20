package com.flipkart.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.flipkart.constant.ConstantVariables;

public class ExtentReportManager {
	
	private static ExtentSparkReporter report;
	private static ExtentReports extent;
	public static ExtentTest test;
	
	public static void createHtmlReport() {
		report = new ExtentSparkReporter(ConstantVariables.EXTENTREPORTFILEPATH);
		report.config().setReportName("Regression Test Report");
		report.config().setDocumentTitle("Flipkart Automation Report");
		report.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(report);
	}
	
	public static ExtentTest createExtentTest(String testName, String catagory, String device, String author) {
		test = extent.createTest(testName).assignAuthor(author).assignCategory(catagory).assignDevice(device);
		return test;
	}
	
	public static void markPassAndAddScreenshot(ExtentTest test, String message) {
		test.pass(message);
		test.info(MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.takeScreenshotUsingBase64(), message).build());
		System.out.println(message);
	}
	
	public static void flushReport() {
		extent.flush();
	}

}
