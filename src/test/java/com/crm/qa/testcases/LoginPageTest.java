package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() 
	{
		super(); //TestBase class constructor will be called and properties will be initialized
	}
	@BeforeMethod
	public void setUp()
	{
		initialization();
		loginPage = new LoginPage();//Initialize LoginPage.java
	}
	@Test(priority = 1, description = "verifying login page title test")
	@Severity(SeverityLevel.NORMAL)
	@Description("TestCase Description: verify login page title test on login page")
	@Story("Story Name: To check login page title")
	public void loginPageTitleTest() 
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "CRMPRO - CRM software for customer relationship management, sales, and support.");
	}
	@Test(priority = 2)
	@Severity(SeverityLevel.NORMAL)
	@Description("TestCase Description: verify CRM Logo Image on login page")
	@Story("Story Name: To check CEM Logo")
	public void crmLogoImageTest() 
	{
		boolean flag = loginPage.validateCRMImage();
		Assert.assertTrue(flag);
	}
	@Test(priority = 3)
	@Severity(SeverityLevel.BLOCKER)
	@Description("TestCase Description: verify Login Test with correct credentials")
	@Story("Story Name: To check Login to Home Page")
	public void loginTest()
	{
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
