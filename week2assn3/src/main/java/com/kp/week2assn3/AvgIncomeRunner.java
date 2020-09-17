package com.kp.week2assn3;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class AvgIncomeRunner {
	public static void main(String[] args) {

		try {
			FileReader fr = new FileReader();
			AvgIncomeHelper aih = new AvgIncomeHelper();
			FileWriter fw = new FileWriter();
			
			System.out.println("Welcome to Summary Report Generator\n");
			System.out.println("***********************************\n");
			System.out.println("Enter the name of the file along with extension");
			Scanner sc = new Scanner(System.in);
			String filename = sc.nextLine();
			sc.close();
			List<InputRecord> inputRecords = fr.readFile(filename);
//			for(int i=0;i<inputRecords.size();i++)
//			{	
//				System.out.println(inputRecords.get(i).getCountry());
//				System.out.println(inputRecords.get(i).getGender());
//				System.out.println(inputRecords.get(i).getAverageIncome());
//			}
			List<OutputRecord> outputRecords = aih.calculateAvgIncome(inputRecords);
			System.out.println("Summary report generating........");
			fw.writeFile("Summary.xlsx" ,outputRecords);
			
			
			
			//XSSFWorkbook workbook = new XSSFWorkbook();

			// Create a blank sheet
			//XSSFSheet sheet = workbook.createSheet("Employee Data");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}