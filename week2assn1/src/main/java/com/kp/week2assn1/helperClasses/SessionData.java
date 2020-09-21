package com.kp.week2assn1.helperClasses;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.kp.week2assn1.actualClasses.*;

public class SessionData {

	private static String fileName = "../resources/session-storage.bin";

  	public Session getSession(int id) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		List<Session> sessionList = (ArrayList<Session>) ois.readObject();
		ois.close();

		for (Session session : sessionList) {
			if (session.getId() == id) {
				return session;
			}
		}

		return null;
	}

	public List<Session> getAllSessions() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		List<Session> sessionList = (List<Session>) ois.readObject();
		ois.close();

		return sessionList;
	}

	public void saveSession(Session session) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
		List<Session> sessionList = (List<Session>) ois.readObject();
		ois.close();

		if (sessionList == null) {
			sessionList = new ArrayList<Session>();
			sessionList.add(session);
		}

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
		oos.writeObject(sessionList);
		oos.close();
	}

}

