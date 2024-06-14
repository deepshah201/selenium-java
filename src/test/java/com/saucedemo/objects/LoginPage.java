package com.saucedemo.objects;

import static com.saucedemo.infrastructure.Helper.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.logger.Log;

public class LoginPage {
	WebDriver driver;
	ExtentTest test;

	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "user-name")
	private WebElement userName;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "login-button")
	private WebElement loginButton;

	public void setUserName(String value) {
		Log.info("[+] Inside setUserName");
		test.info("[+] Inside setUserName");
		sendKeys(userName, value);
		Log.info("[+] Successfully enter userName ");
		test.info("[+] Successfully enter userName ");
	}

	public void setPassword(String value) {
		Log.info("[+] Inside setPassword");
		test.info("[+] Inside setPassword");
		sendKeys(password, value);
		Log.info("[+] Successfully enter password ");
		test.info("[+] Successfully enter password ");
	}

	public void clickLoginButton() {
		Log.info("[+] Inside clickLoginButton");
		test.info("[+] Inside clickLoginButton");
		click(loginButton);
		Log.info("[+] Successfully click on login button ");
	}

	public void login(String userName, String password) {
		Log.info("[+] Inside login");
		test.info("[+] Inside login");
		this.setUserName(userName);
		this.setPassword(password);
		this.clickLoginButton();
		Log.info("[+] Successfully login ");
		test.info("[+] Successfully login ");
	}

}
