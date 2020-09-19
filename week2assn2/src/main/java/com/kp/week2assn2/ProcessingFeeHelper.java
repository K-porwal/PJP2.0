package com.kp.week2assn2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class Key1 {
	// String transactionId;
	String clientId;
	String transactionType;
	String transactionDate;
	String priorityFlag;

	public Key1(String clientId, String transactionType, String transactionDate, String priorityFlag) {
		// this.transactionId = transactionId;
		this.clientId = clientId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.priorityFlag = priorityFlag;
	}
	public String toString() {
		return String.format("<%s, %s,%s, %s>", clientId, transactionType,transactionDate,priorityFlag);
	}
}

class Key2 {
	String clientId;
	String securityId;
	String transactionDate;

	public Key2(String clientId, String securityId, String transactionDate) {
		this.clientId = clientId;
		this.securityId = securityId;
		this.transactionDate = transactionDate;
	}
	public String toString() {
		return String.format("<%s, %s,%s>", clientId, securityId,transactionDate);
	}	
	

}

class Comp implements Comparator<Key1> {
	public int compare(Key1 K1, Key1 K2) {
		boolean c1 = K1.clientId.equals(K2.clientId);
		boolean c2 = K1.transactionType.equals(K2.transactionType);
		boolean c3 = K1.transactionDate.equals(K2.transactionDate);
		boolean c4 = K1.priorityFlag.equals(K2.priorityFlag);

		if (c1 && c2 && c3 && c4)
			return 0;
		else if (c1 && c2 && c3 && !c4)
			return K1.priorityFlag.compareTo(K2.priorityFlag);
		else if (c1 && c2 && !c3)
		{
			int count = 0;
			int firstIndex1 = 0,secondIndex1 = 0;
			int p11=0,p12=0,p13=0;
			for(int i=0;i<K1.transactionDate.length();i++)
			{ 
				if(K1.transactionDate.charAt(i)=='/' && count==1)
				{	
					
					secondIndex1 = i;
					p12 = Integer.parseInt(K1.transactionDate.substring(firstIndex1+1,secondIndex1));
					p13 = Integer.parseInt(K1.transactionDate.substring(secondIndex1+1,K1.transactionDate.length()));
					break;
				}

				
				if(K1.transactionDate.charAt(i)=='/' && count==0)
				{	
					
					firstIndex1 = i;
					p11 = Integer.parseInt(K1.transactionDate.substring(0,firstIndex1));
					count++;
				}
			}
		    
			count = 0;
		    int p21=0,p22=0,p23=0;
			int firstIndex2 = 0,secondIndex2 = 0;
			
			for(int i=0;i<K2.transactionDate.length();i++)
			{ 
				if(K2.transactionDate.charAt(i)=='/' && count==1)
				{	
					
					secondIndex2 = i;
					p22 = Integer.parseInt(K2.transactionDate.substring(firstIndex2+1,secondIndex2));
					p23 = Integer.parseInt(K2.transactionDate.substring(secondIndex2+1,K2.transactionDate.length()));
					break;
				}
				
				if(K2.transactionDate.charAt(i)=='/' && count==0)
				{	
					
					firstIndex2 = i;
					p21 = Integer.parseInt(K2.transactionDate.substring(0, firstIndex2-1));
					count++;
				}

			}
			if(p13==p23) {
				if(p12==p22) {
					if(p11<p21)
						return -1;
					else if(p11==p21)
						return 0;
					else
						return 1;
				}
				else {
					if(p12<p22)
						return -1;
					else
						return 1;
				}
			}
			else {
				if(p13<p23)
					return -1;
				else
					return 1;
			}
				
		}
		else if(c1 && !c2)
			return K1.transactionType.compareTo(K2.transactionType);
		else 
			return K1.clientId.compareTo(K2.clientId);
	}
}

public class ProcessingFeeHelper {

	public boolean determineIntraday(TreeMap<Key2, String> tm2, Key2 K, String V) {
		if (tm2.containsKey(K))
			return true;
		else {
			tm2.put(K, V);
			return false;
		}
	}

	public List<OutputRecord> calculateProcessingFee(List<InputRecord> inputRecords) {

		TreeMap<Key1, Integer> tm1 = new TreeMap<Key1, Integer>(new Comp());
		HashMap<Key2, Integer> tm2 = new HashMap<Key2, Integer>();
		for (int i = 0; i < inputRecords.size(); i++) {

			String securityId = inputRecords.get(i).getSecurityId();
			String clientId = inputRecords.get(i).getClientId();
			String transactionType = inputRecords.get(i).getTransactionType();
			String transactionDate = inputRecords.get(i).getTransactionDate();
			Key2 K2 = new Key2(clientId, securityId, transactionDate);
			if (tm2.containsKey(K2) && transactionType.equals("BUY")) {
				tm2.replace(K2, tm2.get(K2) + 1);
			} else if (tm2.containsKey(K2) && transactionType.equals("SELL")) {
				tm2.replace(K2, tm2.get(K2) - 1);
			} else {
				if(transactionType.equals("SELL"))
					tm2.put(K2, -1);
				else 
					tm2.put(K2, 1);
			}

		}
		
		for (int i = 0; i < inputRecords.size(); i++) {
			String securityId = inputRecords.get(i).getSecurityId();
			String clientId = inputRecords.get(i).getClientId();
			String transactionType = inputRecords.get(i).getTransactionType();
			String transactionDate = inputRecords.get(i).getTransactionDate();
			String priorityFlag = inputRecords.get(i).getPriorityFlag();

			Key2 temp = new Key2(clientId, securityId, transactionDate);
			Key1 K1 = new Key1(clientId, transactionType, transactionDate, priorityFlag);
			if (tm2.containsKey(temp) && tm2.get(temp)== 0) {
				tm1.put(K1, 10);
			} else {
				if (priorityFlag.equals("Y")) {
					tm1.put(K1, 500);
				} else {
					if (transactionType.equals("SELL") || transactionType.equals("WITHDRAW"))
						tm1.put(K1, 100);
					else
						tm1.put(K1, 50);
				}

			}

		}
		
		List<OutputRecord> OutputRecords = new ArrayList<OutputRecord>();
		for(Map.Entry<Key1, Integer> entry :tm1.entrySet()) {
			OutputRecord outputRecord = new OutputRecord();
			outputRecord.setClientId(entry.getKey().clientId);
			outputRecord.setTransactionType(entry.getKey().transactionType);
			outputRecord.setTransactionDate(entry.getKey().transactionDate);
			outputRecord.setPriorityFlag(entry.getKey().priorityFlag);
			outputRecord.setProcessingFee(String.valueOf(entry.getValue()));
			OutputRecords.add(outputRecord);
		}
		return OutputRecords;
	}

}
