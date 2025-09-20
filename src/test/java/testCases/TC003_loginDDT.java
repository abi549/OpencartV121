package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.MyAccountpage;
import pageObjects.homepage;
import pageObjects.loginpage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_loginDDT extends BaseClass
{

	@Test(dataProvider = "LoginData", dataProviderClass = DataProviders.class, groups = "Datadriven") //getting data provider from different class if provided from same class then no need of dataProviderClass = " "
	public void Verify_login(String email, String psd, String exp)
	{ 
		logger.info("***** Starting TC003_loginDDT");
		
		 
		try 
		{	
	    //home page
	    homepage hp = new homepage(driver);
	    hp.clickMyAccount();
	    hp.clickLogin();
	    
	    
	    //login page
	    loginpage lp = new loginpage(driver);
		lp.setemail(email);
		lp.setpassword(psd);
		lp.Clicklogin();
		
		
		//my account page
		MyAccountpage mg = new MyAccountpage(driver);
		boolean targetpage = mg.isMyAccountpageexist();
        
		/*
		 * data is valid  -  login success - test pass - logout
		 *                   login failed  - test fail
		 *                 
		 * data is invalid - login success - test fail - logout
		 *                   login failed  - test pass         
		 */
		  if(exp.equalsIgnoreCase("Valid"))
           {
        	 if(targetpage==true)
        	 {
        	    	mg.clicklogout();
        	    	Assert.assertTrue(true);
            	}
        	  else
        	  {
        		 Assert.fail();
        	  }
            }
		
		     if(exp.equalsIgnoreCase("Invalid"))
              {
			    if(targetpage== true)
        	   {
        	 	  mg.clicklogout();
        		 Assert.assertTrue(false);
        	    }
			 else
			  {
				Assert.assertTrue(true);
			  }
             }
          }
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** finished TC003_loginDDT");
    }
}
