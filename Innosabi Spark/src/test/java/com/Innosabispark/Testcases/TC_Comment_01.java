
package com.Innosabispark.Testcases;

import org.testng.annotations.Test;

import com.Innosabispark.pages.BasePage;
import com.Innosabispark.pages.InnovationChallengePage;
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

public class TC_Comment_01 extends BasePage{
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
		extent = new ExtentReports("C:\\eclipse-workspace\\AutomationReports\\ExtentReports("+fileName+"TC_Comment_01).html");
		extent.addSystemInfo("Environment", "QA");
		extent.addSystemInfo("User Name", "Sowndarya");
		String s=this.getClass().getSimpleName();
		//Name of the Test in extent reports
		test=extent.startTest(s,"This is a Test to Verify User is able to Comment Idea");
		driver.get("https://e2e.innosabi.com/");
		driver.manage().window().maximize();

	}


	@Test// to verify user to comment idea
	public void verifySubmitIdea() throws InterruptedException {
		LoginPage lp=new LoginPage(driver);
		InnovationChallengePage ip=new InnovationChallengePage(driver);
		Thread.sleep(100);
		lp.email("sowndarya.kothalanka@gmail.com");
		test.log(LogStatus.INFO,"Entered email address");
		lp.next();
		test.log(LogStatus.INFO,"Clicked next button");
		Thread.sleep(1000);
		lp.password("Vinayaka@123");
		test.log(LogStatus.INFO,"Entered password");
		lp.clicklogin();
		test.log(LogStatus.INFO,"Clicked login button");
		Thread.sleep(6000);
		boolean Homepagedisplayed =lp.verifyHomepage();
		Assert.assertEquals(Homepagedisplayed, true);
		test.log(LogStatus.PASS," Successfully Redirected to homepage");

		boolean challengedisplayed =ip.verifyInnovationchallenge();
		Assert.assertEquals(challengedisplayed, true);
		test.log(LogStatus.PASS," Successfully Displayed Innovation Challenge on Homepage");
		ip.clickInnovationchallenge();
		test.log(LogStatus.INFO,"Clicked Innovation challenge on Homepage");
		Thread.sleep(4000);
		boolean ipage=ip.Innovationpage();
		Assert.assertEquals(ipage, true);
		test.log(LogStatus.PASS," Successfully Redirected to Innovation challenge page");
		ip.clicksubmitidea();
		test.log(LogStatus.INFO,"clicked Submit Idea Button");
		Thread.sleep(4000);
		ip.ideatitle("This is My Idea");
		test.log(LogStatus.INFO,"Entered Idea Title");
		ip.ideades("Testing Innosabi Spark Page");
		test.log(LogStatus.INFO,"Entered Idea Description");
		ip.clicksubmitidea();
		test.log(LogStatus.INFO,"clicked Submit Idea Button");
		String titletext =ip.checkidea();
		System.out.println(titletext);
		if(titletext.contains("This is My Idea")) {
			test.log(LogStatus.PASS," Successfully Displayed my Idea on Innovation challenge page");
		}
        Thread.sleep(3000);
		ip.comment("This is an Amazing Idea!");
		ip.clickcomment();
		Thread.sleep(3000);
		test.log(LogStatus.PASS,"Successfully Commented another Idea");


	}

	@AfterMethod //TO close driver and generate screenshots 
	public void TearDown(ITestResult Result) throws IOException {
		System.out.println("Result.getStatus()"+Result.getStatus());
		if(Result.getStatus()==ITestResult.SUCCESS) {
			String screenshotpath=getScreenshot(driver, Result.getName(), "TCC1");
			String image= test.addScreenCapture(screenshotpath);
			test.log(LogStatus.PASS, "Successfully Commented  my Idea on Innovation challenge page", image);		
			extent.endTest(test);
		}
		if(Result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, " Search Box is not displayed on Homepaage");
			String screenshotpath1=getScreenshot(driver, Result.getName(), "TCC1");
			String image1= test.addScreenCapture(screenshotpath1);
			test.log(LogStatus.FAIL, "Unable to comment my Idea on Innovation challenge page", image1);		
			extent.endTest(test);

		}
		extent.flush();
		driver.close();
		driver.quit();
	}

}
