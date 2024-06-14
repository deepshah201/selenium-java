package com.saucedemo.logger;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listner implements ITestListener {
	public void onTestStart(ITestResult result) {
//		BasicConfigurator.configure();
		Log.info("");
		Log.info("---------------------------------------------------");
		Log.info("====================================================");
		Log.info(result.getMethod().getMethodName().toUpperCase() + " Started ");
		Log.info("====================================================");
	}

	public void onTestSuccess(ITestResult result) {
		Log.info(result.getMethod().getMethodName().toUpperCase() + " passed");
		Log.info("====================================================");
		Log.info(result.getMethod().getMethodName().toUpperCase() + " Ended ");
		Log.info("====================================================");
		Log.info("---------------------------------------------------");
		Log.info("");
	}

	public void onTestFailure(ITestResult result) {
		Log.error("Failed due to - " + result.getThrowable());
		Log.info("====================================================");
		Log.info(result.getMethod().getMethodName().toUpperCase() + " Ended ");
		Log.info("====================================================");
		Log.info("---------------------------------------------------");
		Log.info("");
		Log.info("");
	}

	public void onTestSkipped(ITestResult result) {
		Log.info("Skipped because of - " + result.getThrowable());
		Log.info("====================================================");
		Log.info(result.getMethod().getMethodName().toUpperCase() + " Ended ");
		Log.info("====================================================");
		Log.info("---------------------------------------------------");
		Log.info("");
		Log.info("");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		Log.info("====================================================");
		Log.info("Start :-" + context.getName());
		Log.info("====================================================");

	}

	public void onFinish(ITestContext context) {
		Log.info("====================================================");
		Log.info("Finish :-" + context.getName());
		Log.info("====================================================");
	}
}