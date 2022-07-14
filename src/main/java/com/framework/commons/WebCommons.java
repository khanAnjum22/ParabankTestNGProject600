package com.framework.commons;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.framework.reports.ExtentReportsClass;
import com.framework.webdriver.WebDriverClass;

public class WebCommons {

	// This class will have all the common methods to deal with any web application

	public WebDriver driver = WebDriverClass.getDriver();

	// Method to perform Scroll to Element
	public void scrollToElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		
	}

	// Method to perform click action
	public void click(WebElement element) {
		scrollToElement(element);
		element.click();
	}

	// Method to enter text in textbox
	public void enterInfo(WebElement element, String value) {
		scrollToElement(element);
		element.clear();
		element.sendKeys(value);
	}

	// Method to select option from dropdown
	public void selectOption(WebElement element, String option, String by) {
		scrollToElement(element);
		Select s = new Select(element);
		if (by.equalsIgnoreCase("visibleText")) {
			s.selectByVisibleText(option);
		} else if (by.equalsIgnoreCase("value")) {
			s.selectByValue(option);
		} else if (by.equalsIgnoreCase("index")) {
			s.selectByIndex(Integer.parseInt(option));
		} else {
			Assert.fail("Inavlid selection method" );
		}
	}

	// Method to select checkbox
	public void selectCheckbox(WebElement element) {
		scrollToElement(element);
		if (!element.isSelected())
			element.click();
	}

	// Method to take screenshot
	public String takeScreenshot(String screenshotName) {
		String path = System.getProperty("user.dir") + "\\Screenshots\\" + screenshotName+getUniqueId()+ ".png";
		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
	
	// Method to generate uniqueId
	public String getUniqueId() {
		String uniqueId = new SimpleDateFormat("MMddyyyyhhmmss").format(Calendar.getInstance().getTime());
		return uniqueId;
	}
	
	//Java Wait
	public void wait(int seconds) {
		try {
			Thread.sleep(seconds*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Method for implicit wait
	public void implictWait(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
	
	//Method to wait for element locator (Explicit Wait)
	public void waitForLocator(By locator) {
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
	}
	
	//Method to get title
	public String getTitle() {
		return driver.getTitle();
	}
	
	//Method to get attribute value
	public String getAttributeValue(WebElement element,String attribute) {
		return element.getAttribute(attribute);
	}
	
	//Method to get element text value
	public String getElementText(WebElement element) {
		return element.getText();
	}
	
	//Print message in the report
	public void log(String message) {
		ExtentReportsClass.test.info(message);
	}
}
