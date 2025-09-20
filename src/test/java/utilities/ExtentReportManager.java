package utilities;

import java.awt.List;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener 
{

	public ExtentSparkReporter sparkreporter;  //UI of the report
	public ExtentReports extent;               //common information of the report
	public ExtentTest test;                    //creating TC entries in the report and update the status of the test methods
	
	
	String repname;
	
    public void onStart(ITestContext testContext) {
	
    	String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
    	repname = "Test-report-" + timestamp +".html";
    	sparkreporter = new ExtentSparkReporter(".\\reports" + repname);  //location where the reports will be stored(extension of file should be .html)
		
    	sparkreporter.config().setDocumentTitle("openCart Automation report"); //title of report
    	sparkreporter.config().setReportName("openCart Functional testing");  //name of the report
    	sparkreporter.config().setTheme(Theme.DARK); 
    	
    	
    	extent = new ExtentReports();
    	extent.attachReporter(sparkreporter);    //connecting the report to report using sparkreporter
    	
    	extent.setSystemInfo("Application", "opencart");
    	extent.setSystemInfo("Module", "Admin");
    	extent.setSystemInfo("Sub Module", "Customers");
    	extent.setSystemInfo("User name", System.getProperty("user.name"));
    	extent.setSystemInfo("Environment", "QA");
    	
    	String os = testContext.getCurrentXmlTest().getParameter("os");                       //same parameter is in XML 
    	extent.setSystemInfo("Operating system", os);

    	String browser = testContext.getCurrentXmlTest().getParameter("browser");             //same parameter is in XML 
    	extent.setSystemInfo("Browser", browser);
    	 	
    	java.util.List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();    //capture the groups
    	if(!includedGroups.isEmpty())
    	{
    		extent.setSystemInfo("Groups", includedGroups.toString());
    	}
    	
    }
    
    public  void onTestSuccess(ITestResult result)     //this method contains the details of all passed method
    {
        test=extent.createTest(result.getTestClass().getName());     //create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test case PASSED is: " +result.getTestClass().getName());   //update the status
     }
	
    public  void onTestFailure(ITestResult result)    //this method contains the detail of all failed method
    {
		 test = extent.createTest(result.getTestClass().getName());   //create a new entry in the report
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, "Test case FAILED IS: "+result.getTestClass().getName());   //update the status
		 test.log(Status.INFO,"Test case is FAILED due: " +result.getThrowable().getMessage());  // will return the actual reason/ exception
    
		 try {
			 String imgpath = new BaseClass().captureScreen(result.getName());
			 test.addScreenCaptureFromPath(imgpath);
		 }
		 catch(IOException e1)
		 {
			 e1.printStackTrace();
		 }
    }

    public void onTestSkipped(ITestResult result)   //this method contains the detail of all the skipped methods
    {
        test= extent.createTest(result.getClass().getName());   //create a new entry in the report
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test case SKIPPED is" +result.getName());  //update the status 
        test.log(Status.INFO,"Test case is SKIPPED due to: " +result.getThrowable().getMessage());
    	
	  }
    
    public void onFinish(ITestContext testContext)
    {
	extent.flush();
	 
	
    }
}
