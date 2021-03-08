/**
 * 
 */
package com.Ratioform.pages;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;


/**  page object encapsulates the Base page
 * @author 
 *
 */
public class BasePage {
	WebDriver driver=null;
	ExtentReports extent;
	ExtentTest test;
	
	//To fetch the screenshot and send to destination file
	 public  String getScreenshot(WebDriver driver,String screenshotname, String name) throws IOException {
			File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("destination" ,"C:\\eclipse-workspace\\AutomationReports\\screenshots\\screenshots_"+ name +".png");
			File finaldestination=new File(destination);
			FileUtils.copyFile(scrFile,finaldestination );
			return destination;
			  
		  }


}
