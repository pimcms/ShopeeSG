package com.shopeeSG.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseClass {

	protected static AndroidDriver<WebElement> driver;

	public static AndroidDriver<WebElement> getAppCapabilities(String plateform ) throws IOException {

		if (plateform.equalsIgnoreCase("Android"))
		{
		try {
			// Set the Desired Capabilities
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("deviceName", "Galaxy Note 8");
			caps.setCapability("udid", "ce061716e11c2118017e"); // Give Device ID of your mobile phone
			caps.setCapability("platformName", "Android");
			caps.setCapability("platformVersion", "9");
			caps.setCapability("appPackage", "com.shopee.sg");
			caps.setCapability("appActivity", "com.shopee.app.ui.home.HomeActivity_");
			caps.setCapability("unicodeKeyboard", true);
			caps.setCapability("resetKeyboard", true);
			caps.setCapability("maxTypingFrequency",30);
			//caps.setCapability("testdroid_testTimeout", 1200);
			caps.setCapability("clearSystemFiles", true);
			driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4723/wd/hub"), caps);
			driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			System.out.println("Cause is :" + e.getCause());
			System.out.println("Message is :" + e.getMessage());
			e.printStackTrace();
		}
		}
		return driver;

	}
	
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extentReports;
	public static ExtentTest extentTest;
	
	@BeforeSuite
	public void SetUp() {
		String path = System.getProperty("user.dir") + "./ExtentReport/TestReport" + CommonUtil.getCorrectTimeDate()
				+ ".html";
		htmlReporter = new ExtentHtmlReporter(path);
		extentReports = new ExtentReports();
		extentReports.attachReporter(htmlReporter);
		
		extentReports.setSystemInfo("Host Name", "Urvashi PC");
		extentReports.setSystemInfo("Username", "Urvashi");
		extentReports.setSystemInfo("Environment", "UAT");

		
		htmlReporter.config().setDocumentTitle("AutomationTesting Blu Report");
		htmlReporter.config().setReportName("Test Report");
		htmlReporter.getRunDuration();
		htmlReporter.getStartTime();
		htmlReporter.getEndTime();
		htmlReporter.getFilePath();
		
	}
	
	@AfterSuite
	public void tearDown()
	{
		extentReports.flush();
	}

	

}