package com.flipkart.listerner;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.flipkart.utilities.ExtentReportManager;
import com.flipkart.utilities.ReusableMethods;

public class ITestResultListerner implements ITestListener {

	public void onTestSuccess(ITestResult result) {
		System.out.println("Test case are Passed for "+result.getMethod().getMethodName());
		ExtentReportManager.test.log(Status.PASS, "Test case passed: " + result.getMethod().getMethodName());
	    ExtentReportManager.test.pass("Final screenshot on success", MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.takeScreenshotUsingBase64()).build()
	    );
	}

	public void onTestFailure(ITestResult result) {
		ExtentReportManager.test.log(Status.FAIL ,MediaEntityBuilder.createScreenCaptureFromBase64String(ReusableMethods.takeScreenshotUsingBase64()).build());
		ExtentReportManager.test.info(result.getThrowable());
	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("Test case are Skiiped for "+result.getMethod().getMethodName());
	}

	public void onStart(ITestContext context) {
		ExtentReportManager.createHtmlReport();
	}

	public void onFinish(ITestContext context) {
		ExtentReportManager.flushReport();
	}

}
