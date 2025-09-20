package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass 
{

public static WebDriver driver;
public Logger logger;
public Properties p;
	
			@BeforeClass (groups = {"Sanity", "Regression", "Master"})
			@Parameters({"os","browser"})
			public void Setup(String os, String br) throws IOException
			{
				//loading config.properties file
				FileReader file = new FileReader("./src//test//resources//config.properties");   //will fetch the file from this location
				p= new Properties();
				p.load(file);
			
				logger=(Logger) LogManager.getLogger(this.getClass());  //this.getClass keyword will return the class that is executing and get the logs, log4j2 will automatically come her
				
				if(p.getProperty("Execution_env").equalsIgnoreCase("remote"))
				{
					DesiredCapabilities capabilites = new DesiredCapabilities();
					
					//os
					if(os.equalsIgnoreCase("windows"))
					{
						capabilites.setPlatform(Platform.WIN11);
					}
					
					else if(os.equalsIgnoreCase("mac"))
					{
						capabilites.setPlatform(Platform.MAC);
					}
					
					else if(os.equalsIgnoreCase("linux"))
					{
						capabilites.setPlatform(Platform.LINUX);
					}
					else {
						System.out.println("no matching OS");
					}
					
					//browser 
					switch(br.toLowerCase())
					
					 {
					 case "chrome" : capabilites.setBrowserName("chrome"); break;
					 case "edge" : capabilites.setBrowserName("Edge"); break;
					 case "firefox" : capabilites.setBrowserName("firefox"); break;
					 default : System.out.println("Invalid browser name"); return;
					 }
					
					driver = new RemoteWebDriver(new URL("http://localhost:4444"), capabilites);
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				}
				
				
				if(p.getProperty("Execution_env").equalsIgnoreCase("local"))
				{
					switch(br.toLowerCase())
				
				 {
				 case "chrome" : driver = new ChromeDriver(); break;
				 case "edge" : driver = new EdgeDriver(); break;
				 case "firefox" : driver = new FirefoxDriver(); break;
				 default : System.out.println("Invalid browser name"); return;
				 }
				
				}
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				
				driver.get(p.getProperty("appURL1"));   //reading URL from properties file, pass URL2 when testing for grid 
				driver.manage().window().maximize();
				
				
			}

			@AfterClass (groups = {"Sanity", "Regression", "Master"})
			public void tearDown()
			{
				driver.quit();
			}
			
			
			public String captureScreen(String tname) throws IOException
			{
				String timestamp = new SimpleDateFormat("yyyyMMddHHss").format(new Date());
				
				TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
				File sourcefile = takesScreenshot.getScreenshotAs(OutputType.FILE);
				
				String targetFilePath = System.getProperty("user.dir")+ "\\screenshots\\" + tname +  " " + timestamp + ".png";
				File targetFile  = new File(targetFilePath);
				
				sourcefile.renameTo(targetFile);
				
				return targetFilePath;
				
			}
			public String randomstring()
			{
				String generatedstring = RandomStringUtils.randomAlphabetic(5);
			    return generatedstring;
				
			}
			
			/*
			public String randomnumber()
			{
				String generatednumber = RandomStringUtils.randomNumeric(8);
			    return generatednumber;
				
			}
			*/
			
			public String randomAlphanumeric()
			{
				String generatedstring = RandomStringUtils.randomAlphabetic(5);
				String generatednumber = RandomStringUtils.randomNumeric(8);
			    return (generatedstring+ "@" + generatednumber);
				
			}
			
	
}
