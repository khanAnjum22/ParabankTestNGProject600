package com.app.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.framework.commons.WebCommons;
import com.framework.utils.ReadProp;
import com.framework.webdriver.WebDriverClass;

public class LoginPage extends WebCommons{	
	
	//LoginPage WebElements
	
	@FindBy(xpath="//img[@class='logo']")
	private WebElement logo;
	
	@FindBy(xpath="//p[@class='caption']")
	private WebElement caption;
	
	@FindBy(xpath="//h2")
	private WebElement loginPageHeader;
	
	@FindBy(xpath="//input[@name='username']")
	private WebElement usernameTxtb;
	
	@FindBy(xpath="//input[@name='password']")
	private WebElement passwordTxtb;
	
	@FindBy(xpath="//input[@value='Log In']")
	private WebElement loginBtn;
	
	By accountOverview = By.xpath("//h1[text()='Accounts Overview']");
	
	@FindBy(linkText="Forgot login info?")
	private WebElement forgotLoginLink;
	
	@FindBy(linkText="Register")
	private WebElement registrationLink;
	
	//Actions
	public void verifyLogo() {
		Assert.assertTrue(logo.isDisplayed());
		log("Logo is available");
	}
	
	public void verifyTitle() {
		Assert.assertEquals(getTitle(),ReadProp.readData("Config.properties").getProperty("Title"));
		log("Expected Title is Available");
	}
	
	public void verifyCaption() {
		Assert.assertEquals(getElementText(caption),ReadProp.readData("Config.properties").getProperty("Caption"));
		log("Expected Caption is Available");
	}
	
	public void loginIntoApplication(String username, String password) {
		enterInfo(usernameTxtb, username);
		enterInfo(passwordTxtb, password);
		click(loginBtn);
		waitForLocator(accountOverview);
		log("Login is Successful");
	}
	
	public void getRegPage() {
		click(registrationLink);
	}
	
	public void getForgotLoginPage() {
		click(forgotLoginLink);
	}
	
	public static LoginPage getLoginPage() {
		return PageFactory.initElements(WebDriverClass.getDriver(), LoginPage.class);
	}

}
