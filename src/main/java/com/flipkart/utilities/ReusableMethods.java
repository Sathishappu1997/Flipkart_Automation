package com.flipkart.utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.flipkart.base.BrowserFactory;
import com.flipkart.constant.Constants;

public class ReusableMethods {
	
	Actions action;
	
	public ReusableMethods() {
		action = new Actions(BrowserFactory.driver);
	}
	
	public String getPageTitle() {
		return BrowserFactory.driver.getTitle();
	}
	
	private static WebDriverWait createWaitObject() {
		return new WebDriverWait(BrowserFactory.driver, Duration.ofSeconds(30));
	}
	
	private static WebElement elementToBeVisible(WebElement element) {
		return createWaitObject().until(ExpectedConditions.visibilityOf(element));
	}
	
	public void typeTextInInput(WebElement element, String textValue) {
		elementToBeVisible(element).sendKeys(textValue);
	}
	
	public void typeTextAndPressEnter(WebElement element, String textValue) {
		elementToBeVisible(element).sendKeys(textValue+Keys.ENTER);
	}
	
	public String xpathUsingText(String xpathText, String changeValue) {
		return xpathText.replaceAll("%replace%", changeValue);
	}
	
	private static Select createSelectObject(WebElement element) {
		return new Select(element);
	}
	
	public WebElement changeToWebelement(String locatorValue, Constants locatorName) {
		if(locatorName.equals(Constants.ID)) {
			return BrowserFactory.driver.findElement(By.id(locatorValue));
		}else if(locatorName.equals(Constants.CLASSNAME)) {
			return BrowserFactory.driver.findElement(By.className(locatorValue));
		}else if(locatorName.equals(Constants.NAME)) {
			return BrowserFactory.driver.findElement(By.name(locatorValue));
		}else if(locatorName.equals(Constants.XPATH)) {
			return BrowserFactory.driver.findElement(By.xpath(locatorValue));
		}else if(locatorName.equals(Constants.LINKTEXT)) {
			return BrowserFactory.driver.findElement(By.linkText(locatorValue));
		}else if(locatorName.equals(Constants.PARTIALLINKTEXT)) {
			return BrowserFactory.driver.findElement(By.partialLinkText(locatorValue));
		}else if(locatorName.equals(Constants.CSS)) {
			return BrowserFactory.driver.findElement(By.cssSelector(locatorValue));
		}else if(locatorName.equals(Constants.TAGNAME)) {
			return BrowserFactory.driver.findElement(By.tagName(locatorValue));
		}
		return null;
	}
	
	public WebElement changeWebelementUsingDynamicXpath(String xpathText, String changeValue) {
		return elementToBeVisible(changeToWebelement(xpathUsingText(xpathText, changeValue), Constants.XPATH));
	}
	
	public boolean validateElementEnabled(WebElement element) {
		return elementToBeVisible(element).isEnabled();
	}
	
	public void dragAndDropBy(WebElement element, int x, int y) {
		action.dragAndDropBy(element, x, y).perform();;
	}
	
	public void selectDropDownValue(WebElement element, String selectValue, Constants selectAttribute){
		if(selectAttribute.equals(Constants.VALUE)) {
			createSelectObject(element).selectByValue(selectValue);
		}else if(selectAttribute.equals(Constants.VISIBLETEXT)) {
			createSelectObject(element).selectByVisibleText(selectValue);
		}else if(selectAttribute.equals(Constants.INDEX)) {
			createSelectObject(element).selectByIndex(Integer.valueOf(selectValue));
		}
	}
	
	public static String takeScreenshotUsingBase64() {
		return ((TakesScreenshot)BrowserFactory.driver).getScreenshotAs(OutputType.BASE64);
	}
}
