package com.flipkart.testcases;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.flipkart.base.BaseClass;
import com.flipkart.constant.ConstantVariables;
import com.flipkart.pages.FlipkartHomePage;
import com.flipkart.pages.FlipkartResultPage;
import com.flipkart.utilities.ExtentReportManager;
import com.flipkart.utilities.ReadFromExcel;

public class TC01HomePage extends BaseClass {

	ExtentTest test;

	@Test(dataProvider = "Products", dataProviderClass = ReadFromExcel.class)
	public void searchProduct(String products) {

		if(products.equalsIgnoreCase("iphone")) {
			test = ExtentReportManager.createExtentTest("Search for "+products, "Regression", "Chrome", "Manoj");
		}else if(products.equalsIgnoreCase("redmi")) {
			test = ExtentReportManager.createExtentTest("Search for "+products, "Sanity", "Edge", "Sathish");
		}else if(products.equalsIgnoreCase("blackberry")) {
			test = ExtentReportManager.createExtentTest("Search for "+products, "Smoke", "MsEdge", "Simon");
		}
		FlipkartHomePage home = new FlipkartHomePage();
		home.validateHomeTitle(ConstantVariables.EXPECTEDHOMETITLE);
		ExtentReportManager.markPassAndAddScreenshot(test, "Validated the Home Title");

		FlipkartResultPage result = home.searchInTextBoxPressEnter(products);
		test.info("Entered the Product in Textbox");
		System.out.println("Entered the Product in Textbox");

		home.validateSearchTitle(products+"- Buy Products Online at Best Price in India - All Categories | Flipkart.com");
		ExtentReportManager.markPassAndAddScreenshot(test, "Validated the Result Title");

		result.validateSearchResultText(products);
		ExtentReportManager.markPassAndAddScreenshot(test, "Results are displayed properly");

		result.adjustSlider();
		test.info("Slider are adjust properly");
		System.out.println("Slider are adjust properly");

		result.selectMaxPriceDrop("20000");
		test.info("Max price is selected to 20,000");
		System.out.println("Max price is selected to 20,000");

		result.selectMinPriceDrop("10000");
		test.info("Min price is selected to 10,000");
		System.out.println("Min price is selected to 10,000");

		ExtentReportManager.markPassAndAddScreenshot(test, "Min and Max sliders are moved properly and Drop Down also selected");

		test.pass(MarkupHelper.createLabel("All the Steps are Pass in Search Iphone Test", ExtentColor.GREEN));
	}

}
