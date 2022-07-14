package com.framework.listners;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.framework.commons.WebCommons;
import com.framework.reports.ExtentReportsClass;

public class TestListner extends ExtentReportsClass implements ITestListener {

//This class will have all the methods related to listeners (to perform actions based on test result)

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		test.pass(result.getMethod().getMethodName() + " is Executed Successfully");
	}

	public void onTestFailure(ITestResult result) {
		test.fail(result.getMethod().getMethodName() + " is Failed");
		try {
			test.addScreenCaptureFromPath(new WebCommons().takeScreenshot(result.getMethod().getMethodName()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test.skip(result.getMethod().getMethodName() + " is Skipped from Execution");
	}

}
