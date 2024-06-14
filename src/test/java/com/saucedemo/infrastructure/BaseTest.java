package com.saucedemo.infrastructure;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.saucedemo.objects.LoginPage;
import com.saucedemo.objects.LogoutPage;
import com.saucedemo.propertiesconfig.PropertiesFile;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class BaseTest {

	public static WebDriver driver;
	public static ExtentReports extent;
	public ExtentTest test;
	public ExtentSparkReporter spark;
	private LoginPage loginPage;
	private LogoutPage logoutPage;

	@Parameters({ "browser" })
	@BeforeSuite
	public void setUp(@Optional("chrome") String browser) throws IOException {
		driver = Helper.getDriver(browser);
		extent = new ExtentReports();
		spark = new ExtentSparkReporter(PropertiesFile.projectPath + "/automation-report/WebTestAutomation.html");
		spark.config().setReportName(PropertiesFile.getProperties("reportName"));
		extent.attachReporter(spark);
		driver.get(PropertiesFile.getProperties("url"));
		deleteAndCreateScreenShotFolder();
		String css = " .nav-logo {display: none; }";
		spark.config().setCss(css);
		spark.config().setDocumentTitle(PropertiesFile.getProperties("reportTitle"));

	}

	@BeforeMethod
	public void startTest(Method method) {
		test = extent.createTest(method.getName());
	}

	@AfterMethod
	public void endTest(ITestResult result) throws InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			String screenshotPath = captureScreenshot(driver, result.getName());
			test.fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else if (result.getStatus() == ITestResult.SKIP) {
			String screenshotPath = captureScreenshot(driver, result.getName());
			test.skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			Markup markup = MarkupHelper.createLabel("Test Passed", ExtentColor.GREEN);
			test.pass(markup);
		}
	}

	public static String captureScreenshot(WebDriver driver, String screenshotName) {
		String dateName = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String folderPath = PropertiesFile.projectPath + "/WebScreenshots/";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = folderPath + screenshotName + "_" + dateName + "_.png";
		File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(source, finalDestination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destination;
	}

	public static void deleteAndCreateScreenShotFolder() {
		String folderPath = PropertiesFile.projectPath + "/WebScreenshots/";
		File folder = new File(folderPath);
		if (folder.exists()) {
			try {
				FileUtils.deleteDirectory(folder);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		folder.mkdir();
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		extent.flush();
		driver.quit();
	}

	public void Login(String username, String password) {
		loginPage = new LoginPage(driver, test);
		loginPage.login(username, password);
	}

	public void Logout() {
		logoutPage = new LogoutPage(driver, test);
		logoutPage.logout();
	}

	public void Category(String category) {
		test.assignCategory(category);
	}

	public void Autor(String autor) {
		test.assignAuthor(autor);
	}

}
