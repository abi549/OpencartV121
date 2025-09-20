package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountpage;
import pageObjects.homepage;
import pageObjects.loginpage;
import testBase.BaseClass;

public class TC002_loginPage extends BaseClass
{
	@Test (groups = {"Sanity", "Master"})
	public void Verify_login()
	{
		try 
		{
	    logger.info("*****Starting the TC002_login page");
	    
	    //home page
	    homepage hp = new homepage(driver);
	    hp.clickMyAccount();
	    hp.clickLogin();
	    
	    //login page
	    loginpage lp = new loginpage(driver);
		lp.setemail(p.getProperty("email"));
		lp.setpassword(p.getProperty("password"));
		lp.Clicklogin();
		
		//my account page
		MyAccountpage mg = new MyAccountpage(driver);
		boolean targetpage = mg.isMyAccountpageexist();
        Assert.assertTrue(targetpage);
        
		}
		
		catch(Exception e)
		{
		Assert.fail();
		}
		logger.info("*****Finished TC 02 and executed");
	}
}
