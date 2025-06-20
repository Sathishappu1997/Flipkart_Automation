package com.flipkart.testcases;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.flipkart.base.BaseClass;
import com.flipkart.constant.ConstantVariables;
import com.flipkart.pages.FlipkartHomePage;
import com.flipkart.pages.FlipkartResultPage;
import com.flipkart.utilities.ExtentReportManager;
import com.flipkart.utilities.ReadFromExcel;

public class TC01HomePage extends BaseClass {

	@Test(dataProvider = "Products", dataProviderClass = ReadFromExcel.class)
	public void searchProduct(String products) {

		if(products.equalsIgnoreCase("iphone")) {
			ExtentReportManager.test = ExtentReportManager.createExtentTest("Search for "+products, "Regression", "Chrome", "Manoj");
		}else if(products.equalsIgnoreCase("redmi")) {
			ExtentReportManager.test = ExtentReportManager.createExtentTest("Search for "+products, "Sanity", "Edge", "Sathish");
		}else if(products.equalsIgnoreCase("blackberry")) {
			ExtentReportManager.test = ExtentReportManager.createExtentTest("Search for "+products, "Smoke", "MsEdge", "Simon");
		}
		FlipkartHomePage home = new FlipkartHomePage();
		home.validateHomeTitle(ConstantVariables.EXPECTEDHOMETITLE);
		ExtentReportManager.markPassAndAddScreenshot(ExtentReportManager.test, "Validated the Home Title");

		FlipkartResultPage result = home.searchInTextBoxPressEnter(products);
		ExtentReportManager.test.log(Status.INFO, "Entered the Product in Textbox");
		System.out.println("Entered the Product in Textbox");

		home.validateSearchTitle(products+"- Buy Products Online at Best Price in India - All Categories | Flipkart.com");
		ExtentReportManager.markPassAndAddScreenshot(ExtentReportManager.test, "Validated the Result Title");

		result.validateSearchResultText(products);
		ExtentReportManager.markPassAndAddScreenshot(ExtentReportManager.test, "Results are displayed properly");

		result.adjustSlider();
		ExtentReportManager.test.log(Status.INFO,"Slider are adjust properly");
		System.out.println("Slider are adjust properly");

		result.selectMaxPriceDrop("20000");
		ExtentReportManager.test.log(Status.INFO,"Max price is selected to 20,000");
		System.out.println("Max price is selected to 20,000");

		result.selectMinPriceDrop("10000");
		ExtentReportManager.test.log(Status.INFO,"Min price is selected to 10,000");
		System.out.println("Min price is selected to 10,000");

		ExtentReportManager.markPassAndAddScreenshot(ExtentReportManager.test, "Min and Max sliders are moved properly and Drop Down also selected");

		ExtentReportManager.test.log(Status.PASS, MarkupHelper.createLabel("All the Steps are Pass in Search Iphone Test", ExtentColor.GREEN));
	}

}
