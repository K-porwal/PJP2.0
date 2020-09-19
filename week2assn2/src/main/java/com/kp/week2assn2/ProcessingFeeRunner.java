package com.kp.week2assn2;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;

public class ProcessingFeeRunner {
	

	public static void main(String[] args) throws IOException {
	
		System.out.println("Processing Fee Calculator");
		System.out.println("*********************");
		System.out.println("Enter a file name with .csv extension");
		Scanner sc = new Scanner(System.in);
		String filename = sc.nextLine();
		sc.close();
		FileReader filereader = new FileReader(filename);
		CSVReader csvReader = new CSVReader(filereader);
		MyFileReader myFileReader = new MyFileReader();
		List<InputRecord> inputRecords = myFileReader.readFile(csvReader);


		ProcessingFeeHelper pih = new ProcessingFeeHelper();
		List<OutputRecord> OutputRecords = pih.calculateProcessingFee(inputRecords);
		MyFileWriter myFileWriter = new MyFileWriter();
		myFileWriter.writeFile(OutputRecords);
	}
}

//Sample_Data_Fee_Calculator.csv
