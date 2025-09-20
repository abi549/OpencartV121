package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class homepage extends BasePage
{

	public homepage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath= "//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(xpath= "//a[@class='dropdown-item'][normalize-space()='Login']")
	WebElement linklogin;
	
	public void clickMyAccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	}


	public void clickLogin()
	{
		linklogin.click();
	}

}
