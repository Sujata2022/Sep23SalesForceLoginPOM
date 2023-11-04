package com.automation.tests.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class PoiXlsxFileUtility {
	private static XSSFWorkbook workbook=null;
	
	public static Object readSingleCellDataFromXLfile(File path, String sheetName, int rowNum, int cellNum) {
		Object data=null;
		//XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(path);
			System.out.println(workbook.getSheetName(0));
			XSSFSheet sheet = workbook.getSheet(sheetName);
			XSSFRow row = sheet.getRow(rowNum);
			XSSFCell cell = row.getCell(cellNum);
			
			if (cell.getCellType() == CellType.NUMERIC)
				data= (Object)Double.valueOf(cell.getNumericCellValue());
			else if (cell.getCellType() == CellType.STRING)
				data= (Object)cell.getStringCellValue();

		} catch (InvalidFormatException | IOException e) {
			e.printStackTrace();
		}
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;

	}
	
	
	public static Object[][] readAllCellDataFromXLfile(String path,String sheetName) throws IOException {
		FileInputStream fs = new FileInputStream(new File(path));

		XSSFWorkbook workbook = new XSSFWorkbook(fs);// .xlsx
		XSSFSheet sheet = workbook.getSheet(sheetName);	
		int rowCount = sheet.getLastRowNum()+1;
		int columnCount = sheet.getRow(0).getLastCellNum();
		
		Object[][] data=new Object[rowCount][columnCount];
		Iterator<Row> rows= sheet.rowIterator();
		int i=0,j=0;
		while(rows.hasNext()) {
			Iterator<Cell> cells  =rows.next().cellIterator();
			j=0;
			while(cells.hasNext()) {
				XSSFCell cell=(XSSFCell) cells.next();
				if (cell.getCellType() == CellType.NUMERIC) {
					data[i][j]= cell.getNumericCellValue();
				}
				else if (cell.getCellType() == CellType.STRING) {
					data[i][j]=cell.getStringCellValue();
				}
				j++;
			}
			i++;
		}
		return data;	
		
	}
	
	

}
