package com.framework.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	
	//This class will have methods to read data from excel file
	
	public static String [][] readData( String workbook, String sheet){
		String [][] data = null;		
		
		try {
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\"+workbook);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheet);
			int totalrows = sh.getPhysicalNumberOfRows();
			int totalColumns = sh.getRow(0).getPhysicalNumberOfCells();
			data = new String [totalrows-1][totalColumns];
			for(int r=1; r<totalrows;r++) {
				for(int c=0;c<totalColumns;c++) {
					data[r-1][c] = sh.getRow(r).getCell(c).getStringCellValue();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return data;
	}

}
