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

public class TC_Login_01 extends BasePage{
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
		extent = new ExtentReports("C:\\eclipse-workspace\\AutomationReports\\ExtentReports("+fileName+"TC_Login_01).html");
		extent.addSystemInfo("Environment", "QA");
		extent.addSystemInfo("User Name", "Sowndarya");
		String s=this.getClass().getSimpleName();
		//Name of the Test in extent reports
		test=extent.startTest(s,"This is a Test to Verify Login Functionality");
		driver.get("https://e2e.innosabi.com/");
		driver.manage().window().maximize();

	}


	@Test// to verify navigation to homepage
	public void verifyLogin_01() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		Thread.sleep(100);
		lp.email("sowndarya.kothalanka@gmail.com");
		test.log(LogStatus.PASS,"Entered email address");
		lp.next();
		test.log(LogStatus.PASS,"Clicked next button");
		Thread.sleep(1000);
		boolean pwd=lp.checkpassword();
		Assert.assertEquals(pwd, true);
		test.log(LogStatus.PASS,"Successfully Displayed password textbox");

	}

	@AfterMethod //TO close driver and generate screenshots 
	public void TearDown(ITestResult Result) throws IOException {
		System.out.println("Result.getStatus()"+Result.getStatus());
		if(Result.getStatus()==ITestResult.SUCCESS) {
			String screenshotpath=getScreenshot(driver, Result.getName(), "TC1");
			String image= test.addScreenCapture(screenshotpath);
			test.log(LogStatus.PASS, "Successfully Navigated to Homepaage", image);		
			extent.endTest(test);
		}
		if(Result.getStatus()==ITestResult.FAILURE) {
			String screenshotpath1=getScreenshot(driver, Result.getName(), "TC1");
			String image1= test.addScreenCapture(screenshotpath1);
			test.log(LogStatus.FAIL, "Unable to navigate to Homepage", image1);		
			extent.endTest(test);

		}
		extent.flush();
		driver.close();
		driver.quit();
	}

}
