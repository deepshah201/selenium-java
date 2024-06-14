package com.saucedemo.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.logger.Log;
import static com.saucedemo.infrastructure.Helper.*;

public class LogoutPage {
	WebDriver driver;
	ExtentTest test;

	public LogoutPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "react-burger-menu-btn")
	private WebElement burgerMenu;

	@FindBy(id = "logout_sidebar_link")
	private WebElement logout;

	@FindBy(id = "reset_sidebar_link")
	private WebElement reset;

	@FindBy(id = "react-burger-cross-btn")
	private WebElement crossButton;

	public void clickBurgeMenu() {
		Log.info("[+] Inside burger menu");
		test.info("[+] Inside burger menu");
		click(burgerMenu);
		Log.info("[+] Successfully click burger menu");
		test.info("[+] Successfully click burger menu");
	}

	public void clickLogout() {
		Log.info("[+] Inside logout");
		test.info("[+] Inside logout");
		waitForElementToBeVisibleAndClickable(driver, logout);
		click(logout);
		Log.info("[+] Successfully click logout");
		test.info("[+] Successfully click logout");
	}

	public void logout() {
		Log.info("[+] Inside logout");
		test.info("[+] Inside logout");
		clickBurgeMenu();
		clickLogout();
		Log.info("[+] Successfully logout");
		test.info("[+] Successfully logout");
	}

	public void clickReset() {
		Log.info("[+] Inside clickReset");
		test.info("[+] Inside clickReset");
		clickBurgeMenu();
		waitForElementToBeVisibleAndClickable(driver, reset);
		click(reset);
		Log.info("[+] Successfully click reset");
		test.info("[+] Successfully click reset");
	}

	public void clickCrossButton() {
		Log.info("[+] Inside clickCrossButton");
		test.info("[+] Inside clickCrossButton");
		click(crossButton);
		Log.info("[+] Successfully click cross button");
		test.info("[+] Successfully click cross button");
	}

}
