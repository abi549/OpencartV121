package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility 
{
	//Created once, to be used in every method. So instead of creating multiple times in the method 
		public  FileInputStream fi;
		public  FileOutputStream fo;
		public  XSSFWorkbook wb;
		public  XSSFSheet ws;
		public  XSSFRow row;
		public  XSSFCell cell;
		public  CellStyle style;
	    String path;
		
		public ExcelUtility(String path)
		{
			this.path = path;
		}
		
		public int getrowcount(String xlsheet) throws IOException 
		{
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			int rowcount = ws.getLastRowNum();
			wb.close();
			fi.close();
			return rowcount;
			
		}
		
		public int getcellcount(String xlsheet, int rownum) throws IOException
		{
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			int cellcount = row.getLastCellNum();
			wb.close();
			fi.close();
			return cellcount;
			
		}
		
		public String getcelldata(String xlsheet, int rownum, int colnum) throws IOException   //this will get cell data for one cell, to get multiple data we can use for loop.
		{
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);
			cell = row.getCell(colnum);
			
			//reading data from the cell, using try catch because there might be no value in the cell of excel sheet
			String data;
			try {
			data=cell.toString();
			}
			catch(Exception e)
			{
				data="";
			}
			wb.close();
			fi.close();
			return data;
		}
		
		public void setcelldata(String xlsheet, int rownum, int colnum, String data) throws IOException   //to get multiple data we can use for loop.
		{
			//Reading the data
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);    // we are getting this row and entering the data in new cell
			
			//Writing the data, we are doing this as this is test data driven testing it might have some data and appending the additional data
			cell = row.createCell(colnum);
			cell.setCellValue(data);
			fo = new FileOutputStream(path);
			wb.write(fo);                         //updating in which sheet we need to write
			wb.close();
			fi.close();
		}
		
		public void fillgreencolor(String xlsheet, int rownum, int colnum) throws IOException   //to get multiple data we can use for loop.
		{
		
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);  
			cell= row.getCell(colnum);
			
			style=wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.GREEN.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
			fo=new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();	
		}
		
		public void fillredcolor(String xlsheet, int rownum, int colnum) throws IOException   //to get multiple data we can use for loop.
		{
		
			fi= new FileInputStream(path);
			wb= new XSSFWorkbook(fi);
			ws=wb.getSheet(xlsheet);
			row=ws.getRow(rownum);  
			cell= row.getCell(colnum);
			
			style=wb.createCellStyle();
			style.setFillBackgroundColor(IndexedColors.RED.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			cell.setCellStyle(style);
			fo=new FileOutputStream(path);
			wb.write(fo);
			wb.close();
			fi.close();	
		}
	}


