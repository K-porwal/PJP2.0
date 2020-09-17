package com.kp.week2assn3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileWriter { 
	
		public void writeFile(String fileName, List<OutputRecord> outputRecords) throws IOException {
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			XSSFSheet sheet = workbook.createSheet("Average Income Summary");
			
			Row row;
			row = sheet.createRow(0);
			row.createCell(0).setCellValue("City/Country");
			row.createCell(1).setCellValue("Gender");
			row.createCell(2).setCellValue("Average Income(in USD)");
			
			
			for(int i = 1; i <= outputRecords.size(); i++) {
				row = sheet.createRow(i);
				OutputRecord record = outputRecords.get(i - 1);
				
				row.createCell(0).setCellValue(record.getCityorcountry());
				row.createCell(1).setCellValue(record.getGender());
				row.createCell(2).setCellValue(record.getAverageIncome());
			}
			
			
			FileOutputStream fos = new FileOutputStream(fileName);
			
			workbook.write(fos);
			
			workbook.close();	
			fos.close();

	}
}
