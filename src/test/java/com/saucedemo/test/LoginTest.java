package com.saucedemo.test;


import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.saucedemo.infrastructure.BaseTest;
import com.saucedemo.objects.LoginPage;

@Listeners(com.saucedemo.logger.Listner.class)
public class LoginTest extends BaseTest {
	private LoginPage loginPage;

	public void Category() {
		test.assignCategory(" Login ");
	}

	public void Autor(String autor) {
		test.assignAuthor(autor);
	}

	@Test
	public void Login() {
		Category();
		Autor("Deep Shah");
		loginPage = new LoginPage(driver,test);
		loginPage.login("standard_user", "secret_sauce");
	}
}
