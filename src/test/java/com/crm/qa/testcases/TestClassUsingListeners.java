package com.crm.qa.testcases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClassUsingListeners {

	public WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		driver =  new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get("https://www.letskodeit.com");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	@Test
	public void testSuccessful() {
		System.out.println("Executing Successful Test Method");
	}
	@Test
	public void testFailed() {
		System.out.println("Executing Failed Test Method");
		Assert.fail("Executing Failed Test Method");
	}
	@Test
	public void testSkipped() {
		System.out.println("Executing Skipped Test Method");
		throw new SkipException("Executing Skipped Test Method");
	}
	
	
}
