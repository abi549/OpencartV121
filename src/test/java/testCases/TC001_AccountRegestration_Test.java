package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.Registerpage;
import pageObjects.homepage;
import testBase.BaseClass;

public class TC001_AccountRegestration_Test extends BaseClass
{
    
	@Test (groups = {"Regression", "Master"})
	public void verify_account_registration()
	{
		logger.info("*****Starting TC001_AccountRegestration_Test");
		try {
		homepage hp = new homepage(driver);
	    hp.clickMyAccount();
	    logger.info("*****Clicked on My account");
	    hp.clickRegister();
	    logger.info("*****Clicked on Register link");
	    
	    Registerpage rp = new Registerpage(driver);
	    rp.setFirstname(randomstring().toUpperCase());
	    rp.setlastname(randomstring().toUpperCase());
	    rp.setemail(randomstring() + "@gmail.com");
	    
	    rp.setpassword(randomAlphanumeric());
	    logger.info("*****Added the information of the user to register");
	    rp.setpolicy();
	    rp.clickcontinue();
	    
	    logger.info("*****Validation expected message");
	    String confmsg= rp.getconfirmationmsg();
	    Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e)
		{
			logger.error("Test Failed..");
			logger.debug("Debug logs.."); 
			Assert.fail();      //to fail the test method in reports
		}
		
		logger.info("***** Test Case Finished1");
	}
	
	
	
}
