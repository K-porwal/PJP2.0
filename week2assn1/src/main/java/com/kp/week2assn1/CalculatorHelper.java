package com.kp.week2assn1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import com.kp.week2assn1.helperClasses.*;
import com.kp.week2assn1.actualClasses.*;

public class CalculatorHelper {
	SessionBusiness sessionBusiness = new SessionBusiness();
	
	Session currentSession;
	
	public void startSession() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(this.currentSession != null) {
			endSession();
		}
		else {
			this.currentSession = sessionBusiness.createNewSession();
		}
	}
	
	public void calculate(String expression) {
		
	}
	
	public void endSession() throws FileNotFoundException, ClassNotFoundException, IOException {
		sessionBusiness.saveSession(currentSession);
	}
	
	
	public void listSessions() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Integer> sessionIds = sessionBusiness.getAllSessionIds();
		System.out.println("list of all session ids");
		for(Integer i:sessionIds) {
			System.out.println(i);
		}
	}
	
	public void showExpressions(int sessionId) throws FileNotFoundException, ClassNotFoundException, IOException {
		Session session = sessionBusiness.getSession(sessionId);
	}
	
}