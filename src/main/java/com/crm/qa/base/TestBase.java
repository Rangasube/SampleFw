package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;
	public static Logger log = Logger.getLogger(TestUtil.class.getName());
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	//Constructor
	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream("C:\\Users\\Sai Sharan\\git\\PageObjectModel\\POM\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	//initialization method
	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if(browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.silentOutput", "true"); //suppresses chrome driver warnings in console output
			System.setProperty("webdriver.chrome.driver","c:\\chromedriver.exe");
			logInfo("Launching Chrome Browser");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("FireFox")) {
			System.setProperty("webdriver.gecko.driver","c:\\geckodriver.exe");
			logInfo("Launching FireFox Browser");
			driver = new FirefoxDriver();
		}
		else if (browserName.equals("IE")) {
			System.setProperty("webdriver.ie.driver","c:\\IEDriverServer.exe");
			logInfo("Launching Internet Explorer");
			driver = new InternetExplorerDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		logInfo("Entering URL of the Application");
		driver.get(prop.getProperty("url"));
	}
	public static void logInfo(String message) {
		log.info(message);
	}
	public static void logWarn(String message) {
		log.warn(message);
	}
	public static void logDebug(String message) {
		log.debug(message);
	}
	public static void logFatal(String message) {
		log.fatal(message);
	}
}
