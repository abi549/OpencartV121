package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class loginpage extends BasePage
{
    //constructor
	public loginpage(WebDriver driver) 
	{
		super(driver);
	}

    //Locators
	
    @FindBy(xpath= "//input[@id='input-email']")
    WebElement txtemail;
    
    @FindBy(xpath= "//input[@id='input-password']")
    WebElement txtpassword;
    
    @FindBy(xpath= "//button[normalize-space()='Login']")
	WebElement loginbtn;
	
	//Action method
    
    public void setemail(String email)
    {
    	txtemail.sendKeys(email);
    }
   
    public void setpassword(String pwd)
    {
    	txtpassword.sendKeys(pwd);
    }

    public void Clicklogin()
    {
    	loginbtn.click();
		
    }

 }
