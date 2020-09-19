package com.kp.week2assn2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVWriter;

public class MyFileWriter {

	public void writeFile(List<OutputRecord> OutputRecords) {

		File file = new File("Summary.csv");
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);
			System.out.println("Generating Summary file..........");

			// adding header to csv
			String[] header = { "Client Id", "Transaction Type", "Transaction Date", "Priority", "Processing Fee",
					"\n" };
			writer.writeNext(header);
			List<String []> temp = new ArrayList<String []>();
			for (int i = 0; i < OutputRecords.size(); i++) {
				
				temp.add(new String[] {OutputRecords.get(i).clientId,
										OutputRecords.get(i).transactionType,
										OutputRecords.get(i).transactionDate,
										OutputRecords.get(i).priorityFlag,
										OutputRecords.get(i).processingFee});	
			
				
			}
			writer.writeAll(temp);
			System.out.println("Summary File Generated");
			writer.close();
		}

		catch (IOException e) {
			e.printStackTrace();
		}
	}
}