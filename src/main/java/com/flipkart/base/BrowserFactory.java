package com.flipkart.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserFactory {
	
	public static WebDriver driver;
	
	static void initDriver(String browser, String url) {
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}else if(browser.equalsIgnoreCase("ff")) {
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("ie")) {
			driver = new InternetExplorerDriver();
		}else {
			System.out.println("Please pass the proper browser name to launch");
			throw new RuntimeException("Input Mismatch Error");
		}
		
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
	}
	
	static void endDriver() {
		driver.quit();
	}

}
