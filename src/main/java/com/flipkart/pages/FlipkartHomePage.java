package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.flipkart.base.BrowserFactory;
import com.flipkart.utilities.ReusableMethods;

public class FlipkartHomePage {
	
	ReusableMethods reusableMethods;
	
	@FindBy(xpath = "//input[@title='Search for Products, Brands and More']")
	private WebElement searchBox;
	
	public FlipkartHomePage() {
		PageFactory.initElements(BrowserFactory.driver, this);
		reusableMethods = new ReusableMethods();
	}
	
	public void validateHomeTitle(String expectedTitle) {
		Assert.assertEquals(reusableMethods.getPageTitle(), expectedTitle);
	}
	
	public FlipkartResultPage searchInTextBoxPressEnter(String productName) {
		reusableMethods.typeTextAndPressEnter(searchBox, productName);
		return new FlipkartResultPage();
	}
	
	public void validateSearchTitle(String expectedTitle) {
		Assert.assertEquals(reusableMethods.getPageTitle(), expectedTitle);
	}

}
