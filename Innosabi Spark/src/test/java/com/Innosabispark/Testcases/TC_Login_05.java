package com.Innosabispark.Testcases;

import org.testng.annotations.Test;

import com.Innosabispark.pages.BasePage;
import com.Innosabispark.pages.LoginPage;

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

public class TC_Login_05 extends BasePage{
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
		extent = new ExtentReports("C:\\eclipse-workspace\\AutomationReports\\ExtentReports("+fileName+"TC_Login_05).html");
		extent.addSystemInfo("Environment", "QA");
		extent.addSystemInfo("User Name", "Sowndarya");
		String s=this.getClass().getSimpleName();
		//Name of the Test in extent reports
		test=extent.startTest(s,"This is a Test to Verify forgot password Functionality");
		driver.get("https://e2e.innosabi.com/");
		driver.manage().window().maximize();

	}



	@Test// to verify forgot passowrd functionality
	public void verifyLogin_05() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(100);
		lp.email("sowndarya.knthalaopka@gmail.com");
		test.log(LogStatus.PASS,"Entered  email address");
		lp.next();
		test.log(LogStatus.PASS,"Clicked next button");
		Thread.sleep(1000);
		lp.forgotpassword();
		test.log(LogStatus.PASS,"Clicked forgot password link");
		lp.email("sowndarya.knthalaopka@gmail.com");
		test.log(LogStatus.PASS,"Entered  email address");
		lp.resetpassword();
		test.log(LogStatus.PASS,"clicked reset password button");

		boolean errormsgdisplayed =lp.invalidpwd();
		Assert.assertEquals(errormsgdisplayed, true);
		test.log(LogStatus.INFO," Successfully displayed your request was successful message");
		test.log(LogStatus.PASS," Successfully User is able to Reset password");


	}

	@AfterMethod //TO close driver and generate screenshots 
	public void TearDown(ITestResult Result) throws IOException {
		System.out.println("Result.getStatus()"+Result.getStatus());
		if(Result.getStatus()==ITestResult.SUCCESS) {
			String screenshotpath=getScreenshot(driver, Result.getName(), "TC5");
			String image= test.addScreenCapture(screenshotpath);
			test.log(LogStatus.PASS, "Successfully able to Reset Password", image);		
			extent.endTest(test);
		}
		if(Result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, " Search Box is not displayed on Homepaage");
			String screenshotpath1=getScreenshot(driver, Result.getName(), "TC5");
			String image1= test.addScreenCapture(screenshotpath1);
			test.log(LogStatus.FAIL, "Unable to Reset password ", image1);		
			extent.endTest(test);

		}
		extent.flush();
		driver.close();
		driver.quit();
	}

}
