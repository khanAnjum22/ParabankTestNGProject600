package com.framework.reports;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportsClass {
	
	//This class will have methods to generate test results report 
	
	public static ExtentReports extent; // to generate report
	public static ExtentTest test; // to print messages in the report
	
	//Method to start report
	@BeforeSuite(alwaysRun = true)
	public static void setupReport() {
		ExtentHtmlReporter report = new ExtentHtmlReporter(System.getProperty("user.dir")+"\\Reports\\AutomationReport.html");
		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("ProjectName", "Parabank");
	}
	
	//Method to close reprot
	@AfterSuite(alwaysRun = true)
	public static void teardownReport() {
		extent.flush();
	}

}
