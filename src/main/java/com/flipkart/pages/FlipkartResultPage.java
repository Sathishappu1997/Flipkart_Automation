package com.flipkart.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.flipkart.base.BrowserFactory;
import com.flipkart.constant.Constants;
import com.flipkart.utilities.ReusableMethods;

public class FlipkartResultPage {
	
	ReusableMethods reusableMethods;
	
	@FindBy(xpath = "(//div[@class='PYKUdo'])[2]")
	WebElement priceSlider;
	
	@FindBy(xpath = "//div[text()='to']//following-sibling::div//select")
	WebElement maxPriceDropDown;
	
	@FindBy(xpath = "//div[text()='to']//preceding-sibling::div//select")
	WebElement minPriceDropDown;
	
	private String resultXpath = "//span[contains(text(),'results for')]//span[text()='%replace%']";
	
	public FlipkartResultPage() {
		PageFactory.initElements(BrowserFactory.driver, this);
		reusableMethods = new ReusableMethods();
	}
	
	public boolean validateSearchResultText(String textValue) {
		return reusableMethods.validateElementEnabled(reusableMethods.changeWebelementUsingDynamicXpath(resultXpath, textValue));
	}
	
	public void adjustSlider() {
		reusableMethods.dragAndDropBy(priceSlider, -50, 0);
	}
	
	public void selectMaxPriceDrop(String textValue) {
		reusableMethods.selectDropDownValue(maxPriceDropDown, textValue, Constants.VALUE);
	}
	
	public void selectMinPriceDrop(String textValue) {
		reusableMethods.selectDropDownValue(minPriceDropDown, textValue, Constants.VALUE);
	}

}
