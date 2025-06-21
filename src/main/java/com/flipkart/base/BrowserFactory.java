package com.flipkart.base;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.flipkart.utilities.ReadFromProperty;

public class BrowserFactory {

	public static WebDriver driver;

	static void initDriver(String browser, String url) {

		if(ReadFromProperty.getFromPropetry("env").equalsIgnoreCase("remote")) {
			DesiredCapabilities cap = new DesiredCapabilities();

			if(ReadFromProperty.getFromPropetry("os").equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}else if(ReadFromProperty.getFromPropetry("os").equalsIgnoreCase("mac")){
				cap.setPlatform(Platform.MAC);
			}else {
				System.out.println("No Matching OS");
				return;
			}

			switch(browser) {
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("edge"); break;
			default : System.out.println("No Matching Browser");
			}

			try {
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}else if(ReadFromProperty.getFromPropetry("env").equalsIgnoreCase("local")) {

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
		}
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(url);
	}
	
	static void endDriver() {
		driver.quit();
	}

}
