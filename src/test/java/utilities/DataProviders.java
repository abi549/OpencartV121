package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{

	//Data provider 1
	@DataProvider(name="LoginData")      //each data provider should have different name
	public String[][] getData() throws IOException
	{
		String path = ".\\testData\\opencart_logindata.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		
		int totalrows = xlutil.getrowcount("Sheet1");
		int totalcols = xlutil.getcellcount("Sheet1", 1);
		
		String logindata[][] = new String[totalrows][totalcols];   //created two dimension array which can stored
		
		for(int i=1; i<=totalrows; i++)
		{
			for(int j=0; j<=totalcols; j++)
			{
				logindata[i-1][j] = xlutil.getcelldata("Sheet1", i, j);
			}
		}

	  return logindata;   //returning two dimension arrays
		
	}
}
