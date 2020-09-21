package com.kp.week2assn1;

import java.io.IOException;
import java.util.Scanner;

public class CalculatorRunner {

	public static void main(String[] args) {

		CalculatorHelper calculator = new CalculatorHelper();
		Scanner sc = new Scanner(System.in);
		
		try {
			calculator.startSession();
		
			int choice;
			
			do {
				System.out.println("-------------- Choose action --------------");
				System.out.println("1. Evaluate Expression");
				System.out.println("2. Show Session History");
				System.out.println("3. Exit");
				
				choice = sc.nextInt();
				
				switch(choice) {
					case 1:
						System.out.println("Enter simple expression");
						calculator.calculate(sc.next());
						break;						
					case 2:
						calculator.listSessions();
						System.out.println("Enter session ID");
						int sessionId = sc.nextInt();
						calculator.showExpressions(sessionId);
						break;
						
					default:
						System.out.println("Invalid Option");
				}
				
			} while(choice != 3);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();	
		}
		finally {
			sc.close();
		}
		
	}

}