
package com.Innosabispark.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;




/**
 * page object encapsulates the Login page
 * @author Sowndarya
 *
 */
public class LoginPage {
	WebDriver driver;

	By Email=By.xpath("//input[@id='email']");
	By Nextbutton=By.xpath("//button[@type='button']");
	By password=By.xpath("//input[@id='password']");
	By login=By.xpath("//button[contains(text(),' Login ')]");
	By forgotpassword=By.xpath("//a[text()='Forgot Password?']");
	By Resetpassword=By.xpath("//button[text()=' Reset password ']");

	By imagespark=By.xpath("(//img[@alt='Platform Logo'])[1]");
	By errormessage=By.xpath("//div[@class='confirmation']/h1");
	By searchresult=By.xpath("//div[@class='sort__col sort__col--counter col']");

	public LoginPage(WebDriver driver) 
	{
		this.driver=driver;
	}

	//to Enter Emailid in the textbox
	public void email(String Emailid) throws InterruptedException 
	{
		driver.findElement(Email).sendKeys(Emailid);

	}
	//to click next button
	public void next() throws InterruptedException 
	{
		driver.findElement(Nextbutton).click();

	}
	//to Enter password in the textbox
	public void password(String pwd) throws InterruptedException 
	{
		driver.findElement(password).sendKeys(pwd);

	}
	
	//to check password field is present
		public boolean checkpassword() throws InterruptedException 
		{
			boolean pawd=driver.findElement(password).isDisplayed();
			return pawd;

		}
	//to click login button
	public void clicklogin() throws InterruptedException 
	{
		driver.findElement(login).click();

	}
	//to click  forgot password link
	public void forgotpassword() throws InterruptedException 
	{
		driver.findElement(forgotpassword).click();

	}

	//to click  reset password button
	public void resetpassword() throws InterruptedException 
	{
		driver.findElement(Resetpassword).click();

	}
	//To verify sparkimage in homepage
	public Boolean verifyHomepage() 
	{
		Boolean Homepagedisplayed=driver.findElement(imagespark).isDisplayed();
		return Homepagedisplayed;
	} 
	//To verify error message for invalid password
		public Boolean invalidpwd() throws InterruptedException 
		{
			Thread.sleep(4000);
			Boolean errormesssgedisplayed=driver.findElement(errormessage).isDisplayed();
			return errormesssgedisplayed;
		} 
}
