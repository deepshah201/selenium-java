/*Helper class is use for defining the common methods which can be used to all other files.
  @author Deep Shah
*/

package com.saucedemo.infrastructure;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Helper extends BaseTest {

	public static WebDriver getDriver(String browser) {
		WebDriver driver = null;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new IllegalArgumentException("Invalid browser name: " + browser);
		}
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void sendKeys(WebElement ele,String value) {
		ele.sendKeys(value);
	}
	
	public static void click(WebElement ele) {
		ele.click();
	}

	public static void scrollToElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", ele);
	}

	public static void scrollToBottom(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	public static void waitForElementToBeVisibleAndClickable(WebDriver driver, WebElement ele,
			Duration timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		WebElement visibleClickablElement = wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public static void waitForElementToBeVisibleAndClickable(WebDriver driver, WebElement ele) {
		Duration defaultTimeoutInSeconds = Duration.ofSeconds(60);// Default timeout value
		waitForElementToBeVisibleAndClickable(driver, ele, defaultTimeoutInSeconds);
	}

	public static void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public static void waitForElementToBeVisible(WebDriver driver, By locator, Duration timeoutInSeconds) {
		WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
		WebElement visiblElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public static void waitForElementToBeVisible(WebDriver driver, By locator) {
		Duration defaultTimeoutInSeconds = Duration.ofSeconds(60);// Default timeout value
		waitForElementToBeVisible(driver, locator, defaultTimeoutInSeconds);
	}

	public static void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public static void dismissAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public static void setDropdown(WebDriver driver, String value, WebElement element) {
		element.click();
		WebElement dropDownField = driver.findElement(By.xpath("//div[@id='select2-drop']//input[@type='text']"));
		waitForElementToBeVisibleAndClickable(driver, dropDownField);
		dropDownField.sendKeys(value, Keys.ENTER);
	}

	public static void switchToWindowByIndex(WebDriver driver, int windowIndex) {
		String currentWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		if (windowIndex >= 0 && windowIndex < windowHandles.size()) {
			driver.switchTo().window((String) windowHandles.toArray()[windowIndex]);
		} else {
			System.out.println("Invalid window index");
		}
	}

	public static void closeWindow(WebDriver driver) {
		driver.close();
	}

	public static void selectDropdownOption(WebDriver driver, WebElement dropdown, String option) {
		Select select = new Select(dropdown);
		select.selectByVisibleText(option);
	}

	public static String getTextFromTooltip(WebDriver driver, WebElement element) {
		return element.getAttribute("validationMessage");
	}

	public static String getValueFromTheField(WebDriver driver, WebElement element) {
		return element.getAttribute("value");
	}

	public static void hoverToElement(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

}