/**
 * 
 */
package com.Ratioform.pages;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;



/**
 * page object encapsulates the search page
 * @author Sowndarya
 *
 */
public class SearchPage {
	WebDriver driver;

	By searchbox=By.xpath("(//input[@name='searchQuery'])[1]");
	By searchbutton=By.xpath("(//span[@class='search-text'])[1]");
	By searchtext=By.xpath("//div[@class='heading-wrapper']/h1");
	By searchsuggestion=By.xpath("(//div[@class='suggestion-product']/a)[1]");
	By searchsuggestion1=By.xpath("(//span[@class='suggestion-product__name'])[1]");
	By ordertable=By.xpath("//a[contains(text(),'Zur Bestelltabelle')]");
	By invalidsearch=By.xpath("//span[@id='search-result-count']");
	By partialsearch=By.xpath("(//a[@class='link-product-wrapper'])[1]//span");
	By searchresult=By.xpath("//div[@class='sort__col sort__col--counter col']");

	public SearchPage(WebDriver driver) 
	{
		this.driver=driver;
	}

	//to select an product option from suggestion layer
	public Boolean searchProductselect(String Productname) throws InterruptedException 
	{
		// Javascript executor class with executeScript method
		JavascriptExecutor j = (JavascriptExecutor) driver;
		// identify element and set value
		WebElement element = driver.findElement(searchsuggestion);
		j.executeScript("arguments[0].click();", element);
		Thread.sleep(3000);
		Boolean orderdetails = driver.findElement(ordertable).isDisplayed();	
		return orderdetails;

	}


	//To verify the suggestions in suggestion layer
	public String searchProductgettext(String Productname) throws InterruptedException 
	{
		driver.findElement(searchbox).sendKeys(Productname);
		driver.findElement(searchbox).click();
		Thread.sleep(4000);
		WebElement elem = driver.findElement(By.xpath("(//div[@class='suggestion-product']/a)[1]"));
		String suggestion = (String)((JavascriptExecutor)driver).executeScript("return arguments[0].textContent;", elem);
		return suggestion;
	}

	//to search the product with productname as input
	public void searchProduct(String Productname) throws InterruptedException 
	{
		driver.findElement(searchbox).sendKeys(Productname);

	}

	//to get the search results when no text is given in searchbox
	public String searchnotext() throws InterruptedException 
	{
		String notxt=driver.findElement(searchresult).getText();
		return notxt;
	}

	//to get the search results when no text is given in searchbox
	public String partialSearch( ) throws InterruptedException 
	{
		String partialtext=driver.findElement(partialsearch).getText();
		return partialtext;
	}

	//to get the search results when Invalid text is given in searchbox
	public String searchinvalidProduct(String Productname) throws InterruptedException 
	{
		driver.findElement(searchbox).sendKeys(Productname);
		driver.findElement(searchbutton).click();
		Thread.sleep(5000);
		String searchdata = driver.findElement(invalidsearch).getText();	
		return searchdata;

	}

	//To verify SearchBox in homepage
	public Boolean searchBox() 
	{
		Boolean searchboxdisplayed=driver.findElement(searchbox).isDisplayed();
		return searchboxdisplayed;
	} 


	// to clcik search button
	public void ClickSearchButton() 
	{
		driver.findElement(searchbutton).click();

	}
	
	//to verify searchtext displayed on Homepage
	public Boolean verifysearchtext() throws InterruptedException {
		Thread.sleep(3000);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		Boolean searchtextdisplayed =driver.findElement(searchtext).isDisplayed();
		return searchtextdisplayed;

	}


}