package com.flipkart.utilities;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import com.flipkart.constant.ConstantVariables;

public class ReadFromExcel {
	
	private static File file;
	private static FileInputStream input;
	private static XSSFWorkbook wb;
	private static XSSFSheet sh;
	private static XSSFRow  row;
	private static XSSFCell cell;
	private static DataFormatter dt;
	private static String [] data;

	private static String[] getValuesFromExcel() {
		try {
			file = new File(ConstantVariables.EXCELFILEPATH);
			input = new FileInputStream(file);
			wb = new XSSFWorkbook(input);
			sh = wb.getSheet("Product");
		}catch (Exception e) {
			System.out.println(e);
		}
		
		int lastRowCount = sh.getLastRowNum();
		int columnCount = sh.getRow(0).getLastCellNum();
		
		data = new String[lastRowCount];
		
		for(int i = 1 ; i <= lastRowCount ; i++) { //Rows
			row = sh.getRow(i);
			for(int j = 0 ; j < columnCount ; j++) { //Columns
				cell = row.getCell(j);
				dt = new DataFormatter();
				data[i-1] = dt.formatCellValue(cell);
			}
		}
		return data;
	}
	
	@DataProvider(name = "Products")
	public String[] getDataForTest() {
		return getValuesFromExcel();
	}

}
