package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountpage extends BasePage 
{

	public MyAccountpage(WebDriver driver) 
	{
		super(driver);
		
	}

	@FindBy(xpath= "//h1[normalize-space()='My Account']")
	WebElement msgheading;
	
	@FindBy(xpath= "//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnklogout;
	
	
	public boolean isMyAccountpageexist()
	{
		try
		{
			return(msgheading.isDisplayed());
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clicklogout()
	 {
		lnklogout.click();
	 }
  
}
