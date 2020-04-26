package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

import io.qameta.allure.Step;

public class LoginPage extends TestBase {

	//Page Factory - OR;
	@FindBy(xpath="//input[@placeholder='Username']")
	WebElement username;
	@FindBy(xpath="//input[@placeholder='Password']")
	WebElement password;
	@FindBy(xpath="//input[@class='btn btn-small']")
	WebElement loginBtn;
	@FindBy(xpath="//a[contains(text(),'Sign Up')]")
	WebElement signupBtn;
	@FindBy(xpath="//a[@class='navbar-brand']")
	WebElement crmLogo;

//Initializing the Page objects:
public LoginPage() {
	PageFactory.initElements(driver,this);
}
//Actions:
@Step("Getting login page title step...")
public String validateLoginPageTitle() {
	return driver.getTitle();
}
@Step("verifying CRM image is displayed step...")
public boolean validateCRMImage() {
	return crmLogo.isDisplayed();
}
@Step("login with Username: {0} and Password: {1}")
public HomePage login(String un, String pwd) {
	username.sendKeys(un);
	password.sendKeys(pwd);
	loginBtn.click();
	return new HomePage();
}
}