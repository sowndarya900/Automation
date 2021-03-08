package com.Ratioform.Testcases;

import org.testng.annotations.Test;

import com.Ratioform.pages.BasePage;
import com.Ratioform.pages.LoginPage;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public class NavigationTest extends BasePage{
	WebDriver driver;
	ExtentReports extent;
	ExtentTest test;

	@BeforeMethod
	public void setUp() throws InterruptedException {
		//		 set path of the chrome driver
		System.setProperty("webdriver.chrome.driver", "C:\\BrowserDriver\\chromedriver.exe");
		//		 initialize new webdriver
		driver=new ChromeDriver();
		String fileName = new SimpleDateFormat("yyyyMMdd'.txt'").format(new Date());
		//setting path to extent report generation
		extent = new ExtentReports("C:\\eclipse-workspace\\AutomationReports\\ExtentReports("+fileName+"NavigationTest).html");
		extent.addSystemInfo("Environment", "QA");
		extent.addSystemInfo("User Name", "QA_User");
		String s=this.getClass().getSimpleName();
		//Name of the Test in extent reports
		test=extent.startTest(s,"This is a Test to Verify Navigation to ratioform homepage");
	
	}
	
	@Test //Method to verify login to Homepage
	public void verifyLogin() throws InterruptedException {
		LoginPage hp=new LoginPage(driver,"https://www.ratioform.at/.");
		  Assert.assertEquals(true, driver.getTitle().contains("Verpackung")); 
		test.log(LogStatus.PASS," Successfully Navigated to RatioForm Homepage");
		
	}

	@AfterMethod //TO close driver and generate screenshots 
	public void TearDown(ITestResult Result) throws IOException {
		System.out.println("Result.getStatus()"+Result.getStatus());
		if(Result.getStatus()==ITestResult.SUCCESS) {
			String screenshotpath=getScreenshot(driver, Result.getName(),"TC0");
			String image= test.addScreenCapture(screenshotpath);
			test.log(LogStatus.PASS, "Successfully Navigated to RatioForm Homepage", image);		
			extent.endTest(test);
		}
		if(Result.getStatus()==ITestResult.FAILURE) {
			String screenshotpath1=getScreenshot(driver, Result.getName(),"TC0");
			String image1= test.addScreenCapture(screenshotpath1);
			test.log(LogStatus.FAIL, "Failed to Navigate to RatioForm Homepage", image1);		
			extent.endTest(test);

		}
		extent.flush();
		driver.close();
		driver.quit();
	}

}
