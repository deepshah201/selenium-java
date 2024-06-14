package com.saucedemo.test;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucedemo.infrastructure.BaseTest;
import com.saucedemo.objects.LogoutPage;
import com.saucedemo.objects.ProductsPage;
import com.saucedemo.objects.YourcartPage;
import com.saucedemo.propertiesconfig.PropertiesFile;

@Listeners(com.saucedemo.logger.Listner.class)
public class Tests extends BaseTest {
	private ProductsPage productsPage;
	private YourcartPage yourcartPage;
	private LogoutPage logoutPage;

	@Test(description = "Verify that the user can add & remove a product from the cart")
	public void TC_07() {
		Category("Cart");
		Autor("Deep Shah");
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage = new ProductsPage(driver, test);
		productsPage.addToCart();
		Assert.assertEquals(true, productsPage.checkNumCartItems());
		productsPage.removeFromCart();
		Assert.assertEquals(false, productsPage.checkNumCartItems());
		Logout();
	}

	@Test(description = "Verify that the user can successfully complete the checkout process")
	public void TC_08() {
		Category("Checkout");
		Autor("Deep Shah");
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage = new ProductsPage(driver, test);
		productsPage.addToCart();
		Assert.assertEquals(true, productsPage.checkNumCartItems());
		productsPage.clickShoppingCart();
		yourcartPage = new YourcartPage(driver, test);
		yourcartPage.checkoutItem();
		Assert.assertEquals(yourcartPage.getCompleteMsg(), "Checkout: Complete!");
		Logout();
	}

	@Test(description = "Reset Cart State from CheckoutStep")
	public void TC_12() {
		Category("Cart");
		Autor("Deep Shah");
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage = new ProductsPage(driver, test);
		productsPage.addToCart();
		Assert.assertEquals(true, productsPage.checkNumCartItems());
		productsPage.clickShoppingCart();
		yourcartPage = new YourcartPage(driver, test);
		yourcartPage.clickCheckout();
		yourcartPage.setFirstName("John");
		yourcartPage.setLastName("Doe");
		yourcartPage.setPostalCode("12345");
		logoutPage = new LogoutPage(driver, test);
		logoutPage.clickReset();
		Assert.assertEquals(false, productsPage.checkNumCartItems());
		logoutPage.clickCrossButton();
		Logout();
	}

	@Test(description = "should be able to add one item to cart, go to Cart page, logout, log back in and check it out successfully")
	public void TC_14() {
		Category("Cart");
		Autor("Deep Shah");
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage = new ProductsPage(driver, test);
		productsPage.addToCart();
		Assert.assertEquals(true, productsPage.checkNumCartItems());
		Logout();
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage.clickShoppingCart();
		yourcartPage = new YourcartPage(driver, test);
		yourcartPage.checkoutItem();
		Assert.assertEquals(yourcartPage.getCompleteMsg(), "Checkout: Complete!");
		Logout();
	}

	@Test(description = "Should error out when customer information is missing")
	public void TC_15() {
		Category("Checkout");
		Autor("Deep Shah");
		Login(PropertiesFile.getProperties("username"), PropertiesFile.getProperties("password"));
		productsPage = new ProductsPage(driver, test);
		productsPage.addToCart();
		Assert.assertEquals(true, productsPage.checkNumCartItems());
		productsPage.clickShoppingCart();
		yourcartPage = new YourcartPage(driver, test);
		yourcartPage.clickCheckout();
		yourcartPage.setFirstName("John");
		yourcartPage.setLastName("Doe");
		yourcartPage.clickContinue();
		Assert.assertEquals(yourcartPage.verifyErrorMsg(), true);
		Logout();
	}
}
