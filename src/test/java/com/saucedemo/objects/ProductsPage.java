package com.saucedemo.objects;

import static com.saucedemo.infrastructure.Helper.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.logger.Log;

public class ProductsPage {
	WebDriver driver;
	ExtentTest test;

	public ProductsPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.test = test;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "add-to-cart-sauce-labs-backpack")
	private WebElement addToCart;

	@FindBy(css = ".shopping_cart_badge")
	private WebElement numCartItems;

	@FindBy(id = "remove-sauce-labs-backpack")
	private WebElement removeFromCart;

	@FindBy(id = "shopping_cart_container")
	private WebElement shoppingCartContainer;

	public void addToCart() {
		Log.info("[+] Inside to cart");
		test.info("[+] Inside to cart");
		click(addToCart);
		Log.info("[+] Successfully added to cart");
		test.info("[+] Successfully added to cart");
	}

	public boolean checkNumCartItems() {
		Log.info("[+] Inside Checking number of cart items");
		test.info("[+] Inside Checking number of cart items");
		try {
			int numItems = Integer.parseInt(numCartItems.getText());
			if (numItems > 0) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	public void removeFromCart() {
		Log.info("[+] Inside remove from cart");
		test.info("[+] Inside remove from cart");
		click(removeFromCart);
		Log.info("[+] Successfully removed from cart");
		test.info("[+] Successfully removed from cart");
	}

	public void clickShoppingCart() {
		Log.info("[+] Inside click shopping cart link");
		test.info("[+] Inside click shopping cart link");
		click(shoppingCartContainer);
		Log.info("[+] Successfully click shopping cart link");
		test.info("[+] Successfully click shopping cart link");
	}

}
