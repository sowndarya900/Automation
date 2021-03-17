
package com.Ratioform.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;




/** page object encapsulates the LoginPage
 *  
 *
 */
@Test
public class LoginPage {
	   WebDriver driver;

	  public LoginPage(WebDriver driver,String url){
	    this.driver = driver;
	    driver.get(url);
	    driver.manage().window().maximize();
	    
	
	  }
}