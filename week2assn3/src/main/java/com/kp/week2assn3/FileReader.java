package com.kp.week2assn3;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {
	public List<InputRecord> readFile(String filename) throws IOException {
		
		List<InputRecord>Records = new ArrayList<InputRecord>();
		
		FileInputStream fis = new FileInputStream(filename);
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook (fis);
		
		XSSFSheet mySheet = myWorkBook.getSheetAt(0);
		
		Iterator<Row> rowIterator = mySheet.iterator();
		
		rowIterator.next();
		
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			
			InputRecord record = new InputRecord();
			
			Cell city = row.getCell(0);
			Cell country = row.getCell(1);
			Cell gender = row.getCell(2);
			Cell currency = row.getCell(3);
			Cell averageIncome = row.getCell(4);
			
			record.setCity(city.getStringCellValue());
			
			if(country != null) {
				record.setCountry(country.getStringCellValue());
			}
			
			record.setGender(gender.getStringCellValue());
			record.setCurrency(currency.getStringCellValue());
			record.setAverageIncome(averageIncome.getNumericCellValue());
			
			Records.add(record);
		}
		
		myWorkBook.close();
		fis.close();
		return Records;
	}

}
