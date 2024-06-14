package com.saucedemo.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.logger.Log;
import static com.saucedemo.infrastructure.Helper.*;

public class YourcartPage {
	WebDriver driver;
	ExtentTest test;

	public YourcartPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "checkout")
	private WebElement checkoutButton;

	@FindBy(id = "first-name")
	private WebElement firstName;

	@FindBy(id = "last-name")
	private WebElement lastName;

	@FindBy(id = "postal-code")
	private WebElement postalCode;

	@FindBy(id = "continue")
	private WebElement continueButton;

	@FindBy(id = "finish")
	private WebElement finish;

	@FindBy(className = "title")
	private WebElement title;

	@FindBy(id = "back-to-products")
	private WebElement backToProducts;

	@FindBy(css = "h3[data-test=\"error\"]")
	private WebElement errorMsg;

	public void clickCheckout() {
		Log.info("[+] Inside checkout ");
		test.info("[+] Inside checkout ");
		click(checkoutButton);
		Log.info("[+] Successfully click on checkout ");
		test.info("[+] Successfully click on  checkout ");
	}

	public void setFirstName(String value) {
		Log.info("[+] Inside setFirstName");
		test.info("[+] Inside setFirstName");
		sendKeys(firstName, value);
		Log.info("[+] Successfully enter first name ");
		test.info("[+] Successfully enter first name ");
	}

	public void setLastName(String value) {
		Log.info("[+] Inside setLastName");
		test.info("[+] Inside setLastName");
		sendKeys(lastName, value);
		Log.info("[+] Successfully enter last name ");
		test.info("[+] Successfully enter last name ");
	}

	public void setPostalCode(String value) {
		Log.info("[+] Inside setPostalCode");
		test.info("[+] Inside setPostalCode");
		sendKeys(postalCode, value);
		Log.info("[+] Successfully enter postal code ");
		test.info("[+] Successfully enter postal code ");
	}

	public void clickContinue() {
		Log.info("[+] Inside clickContinue");
		test.info("[+] Inside clickContinue");
		click(continueButton);
		Log.info("[+] Successfully click on continue ");
		test.info("[+] Successfully click on continue ");
	}

	public void clickFinish() {
		Log.info("[+] Inside Finish");
		test.info("[+] Inside Finish");
		click(finish);
		Log.info("[+] Successfully click on finish ");
		test.info("[+] Successfully click on finish ");
	}

	public void checkoutItem() {
		Log.info("[+] Inside checkoutItem");
		test.info("[+] Inside checkoutItem");
		clickCheckout();
		setFirstName("John");
		setLastName("Doe");
		setPostalCode("12345");
		clickContinue();
		clickFinish();
		Log.info("[+] Successfully checkout Item");
		test.info("[+] Successfully checkout Item");
	}

	public String getCompleteMsg() {
		Log.info("[+] Inside getCompleteMsg");
		test.info("[+] Inside getCompleteMsg");
		return title.getText();
	}

	public void clickBackToProducts() {
		Log.info("[+] Inside clickBackToProducts");
		test.info("[+] Inside clickBackToProducts");
		click(backToProducts);
		Log.info("[+] Successfully click on back to products ");
		test.info("[+] Successfully click on back to products ");
	}

	public boolean verifyErrorMsg() {
		Log.info("[+] Inside verifyErrorMsg");
		test.info("[+] Inside verifyErrorMsg");
		return errorMsg.isDisplayed();
	}
}
