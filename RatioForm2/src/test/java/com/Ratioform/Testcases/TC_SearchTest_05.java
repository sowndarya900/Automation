package com.Ratioform.Testcases;

import org.testng.annotations.Test;

import com.Ratioform.pages.BasePage;
import com.Ratioform.pages.SearchPage;
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

public class TC_SearchTest_05 extends BasePage{
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
		extent = new ExtentReports("C:\\eclipse-workspace\\AutomationReports\\ExtentReports("+fileName+"TC_SearchTest_05).html");
		extent.addSystemInfo("Environment", "QA");
		extent.addSystemInfo("User Name", "QA_User");
		String s=this.getClass().getSimpleName();
		test=extent.startTest(s,"This is a Test to Verify Search Results for Partial SearchText");
		driver.get("https://www.ratioform.at/.");
		driver.manage().window().maximize();
		Assert.assertEquals(true, driver.getTitle().contains("Verpackung"));
		test.log(LogStatus.PASS," Successfully Navigated to RatioForm Homepage");

	}


	@Test //method to verify search box  for partial search Text
	public void verifySearch() throws InterruptedException {
		SearchPage sp=new SearchPage(driver);
		boolean searchboxdisplayed =sp.searchBox();
		Assert.assertEquals(searchboxdisplayed, true);
		test.log(LogStatus.PASS," Successfully Displayed Searchbox in homepage");
		sp.searchProduct("Well");
		test.log(LogStatus.INFO,"Entered Well in searchBox");
		sp.ClickSearchButton();
		test.log(LogStatus.INFO,"clicked Search Button");
		String s=sp.partialSearch();
		if(s.contains("Well"))
		test.log(LogStatus.PASS," Successfully Displayed Wellpapp products on Homepage");
		
		}
	


	@AfterMethod //TO close driver and generate screenshots
	public void TearDown(ITestResult Result) throws IOException {
		System.out.println("Result.getStatus()"+Result.getStatus());
		if(Result.getStatus()==ITestResult.SUCCESS) {
//			test.log(LogStatus.PASS, "Successfully Redirected to Product page");
			String screenshotpath=getScreenshot(driver, Result.getName(),"TC5");
			String image= test.addScreenCapture(screenshotpath);
			test.log(LogStatus.PASS, "Successfully Displayed Wellpapp products on Homepage", image);		
			extent.endTest(test);
		}
		if(Result.getStatus()==ITestResult.FAILURE) {
			test.log(LogStatus.FAIL, " unable to Display Wellpapp products on Homepage");
			String screenshotpath1=getScreenshot(driver, Result.getName(),"TC5");
			String image1= test.addScreenCapture(screenshotpath1);
			test.log(LogStatus.FAIL, "unable to Display Wellpapp products on Homepage", image1);		
			extent.endTest(test);

		}
		extent.flush();
		driver.close();
		driver.quit();
	}

}
