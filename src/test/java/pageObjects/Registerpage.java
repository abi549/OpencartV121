package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;



public class Registerpage extends BasePage
{
    //constructor
	public Registerpage(WebDriver driver)
	{
		super(driver);
	}
	
//locators
@FindBy(xpath= "//input[@id='input-firstname']")
WebElement txtfirstname;

@FindBy(xpath= "//input[@id='input-lastname']")
WebElement txtlastname;

@FindBy(xpath= "//input[@id='input-email']")
WebElement txtemail;

@FindBy(xpath= "//input[@id='input-password']")
WebElement txtpassword;

@FindBy(xpath= "//input[@name='agree']")
WebElement chkdpolicy;

@FindBy(xpath= "//button[normalize-space()='Continue']")
WebElement continue_btn;

@FindBy(xpath= "//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgconfirmation;


//action methods
public void setFirstname(String fname)
{
    txtfirstname.sendKeys(fname);	
}

public void setlastname(String lname)
{
    txtlastname.sendKeys(lname);	
}

public void setemail(String email)
{
	txtemail.sendKeys(email);	
}

public void setpassword(String password)
{
	txtpassword.sendKeys(password);	
}

public void setpolicy()
{

	Actions act = new Actions(driver);
	act.moveToElement(chkdpolicy).dragAndDropBy(chkdpolicy, 10, 0).perform();
	    	
}

public void clickcontinue()
{
	continue_btn.click();	
}

public String getconfirmationmsg()
{
	try 
	{
    	return(msgconfirmation.getText());
	}
	catch (Exception e)
	{
		return(e.getMessage());
	}
}

}
