package com.app.test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.app.pages.ForgotPassPage;
import com.app.pages.LoginPage;
import com.app.pages.RegistrationPage;
import com.framework.utils.ReadExcel;
import com.framework.webdriver.WebDriverClass;

public class ApplicationTest extends WebDriverClass{
	
	@Test(priority=1,groups={"Smoke"})
	public void VerifyLogo() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyLogo();
	}
	
	@Test(priority=0,groups={"Smoke","Sanity"})
	public void VerifyTitle() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyTitle();
	}
	
	@Test(priority=2,groups={"Smoke"})
	public void VerifyCaption() {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.verifyCaption();
	}
	
	@Test(priority=3,groups= {"Regression"},dataProvider="data")
	public void VerifyRegistrationProcess(String username, String password) {
		LoginPage loginpage = LoginPage.getLoginPage();
		RegistrationPage regpage =RegistrationPage.getRegistrationPage();
		loginpage.getRegPage();
		regpage.verifyRegPage();
		regpage.verifyRegistrationFeature(username, password);
	}
	
	@Test(priority=4,groups= {"Regression"},dependsOnMethods= {"VerifyRegistrationProcess"},dataProvider="data")
	public void VerifyLoginFeature(String username, String password) {
		LoginPage loginpage = LoginPage.getLoginPage();
		loginpage.loginIntoApplication(username, password);
	}
	
	@Test(priority=5,groups= {"Regression","Sanity"})
	public void VerifyFogotLoginInfoPage() {
		LoginPage loginpage = LoginPage.getLoginPage();
		ForgotPassPage fogotLoginPage = ForgotPassPage.getForgotPassPage();
		loginpage.getForgotLoginPage();
		fogotLoginPage.verifyForgotPassPage();
	}
	
	@DataProvider(name="data")
	public String [][] testData() {
		String [][] data = ReadExcel.readData("Data.xlsx", "TestData");
		return data;
	}
	

}
