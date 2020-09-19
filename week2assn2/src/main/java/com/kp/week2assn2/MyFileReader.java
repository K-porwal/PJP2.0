package com.kp.week2assn2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

public class MyFileReader {

	public List<InputRecord> readFile(CSVReader csvReader) throws IOException {
		List<InputRecord> inputRecords = new ArrayList<InputRecord>() ;
		try {
			
			
			String[] nextRecord;

			// we are going to read data line by line
			csvReader.readNext();
			while ((nextRecord = csvReader.readNext()) != null) {
	
			 InputRecord inputRecord = new InputRecord();	
			 inputRecord.setTransactionId(nextRecord[0]);
			 inputRecord.setClientId(nextRecord[1]);
			 inputRecord.setSecurityId(nextRecord[2]);
			 inputRecord.setTransactionType(nextRecord[3]);
			 inputRecord.setTransactionDate(nextRecord[4]);
			 inputRecord.setMarketValue(Double.valueOf(nextRecord[5]));
			 inputRecord.setPriorityFlag(nextRecord[6]);
			 inputRecords.add(inputRecord);
			}
			csvReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputRecords;

	}
}
