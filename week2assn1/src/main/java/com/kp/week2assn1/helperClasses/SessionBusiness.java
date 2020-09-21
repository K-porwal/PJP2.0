package com.kp.week2assn1.helperClasses;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kp.week2assn1.actualClasses.Session;

public class SessionBusiness {
	Random random = new Random();
	SessionData sessionData;

	public Session createNewSession() {
		return new Session(random.nextInt(1000000));
		
	}
	
	public List<Integer> getAllSessionIds() throws FileNotFoundException, ClassNotFoundException, IOException {
		List<Session> sessionList = sessionData.getAllSessions();
		
		List<Integer> sessionIdList = new ArrayList<Integer>();
		for(Session session:sessionList) {
			sessionIdList.add(session.getId());
		}
		
		return sessionIdList;
	}
	
	
	public void saveSession(Session session) throws FileNotFoundException, ClassNotFoundException, IOException {
		sessionData.saveSession(session);
	}
	
	public Session getSession(int sessionId) throws FileNotFoundException, IOException, ClassNotFoundException {
		return sessionData.getSession(sessionId);
	}
}	