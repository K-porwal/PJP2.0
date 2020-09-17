package com.kp.week2assn3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

class Pair {
	String country = null;
	String gender = null;

	public Pair(String country, String gender) {
		this.country = country;
		this.gender = gender;

	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair)) {
			return false;
		}

		Pair that = (Pair) obj;

		return ((this.country.equals(that.country) && this.gender.equals(that.gender)));
	}

	public String toString() {
		return String.format("<%s, %s>", country, gender);
	}
}

class Comp implements Comparator<Pair> {
	public int compare(Pair p1, Pair p2) {
		boolean c1 = p1.country.equals(p2.country);
		boolean c2 = p1.gender.equals(p2.gender);

		if (c1 && c2)
			return 0;
		else if (c1 && !c2)
			return p1.gender.compareTo(p2.gender);
		else
			return p1.country.compareTo(p2.country);
	}
}

public class AvgIncomeHelper {

	public List<OutputRecord> calculateAvgIncome(List<InputRecord> inputRecords) {
		
		HashMap<String,Double> curr_exc = new HashMap<String,Double>();
		
		curr_exc.put("USD",1.0);
		curr_exc.put("INR",1/66.0);
		curr_exc.put("GBP",1/0.67);
		curr_exc.put("SGP",1/1.5);
		curr_exc.put("HKD",1/8.0);
		
//		 for (Map.Entry<String,double> entry : curr_exc.entrySet()) 
//		 {
//			 System.out.println("Key" + entry.getKey() + "Value"+entry.getValue());
//		 }
		
		TreeMap<Pair, Double> tm1 = new TreeMap<Pair, Double>(new Comp());
		TreeMap<Pair, Integer> tm2 = new TreeMap<Pair, Integer>(new Comp());
		for (int i = 0; i < inputRecords.size(); i++) {
			Pair temp;
			double avgIncome = inputRecords.get(i).getAverageIncome();
			double convRate = curr_exc.get((inputRecords.get(i)).getCurrency());
			//System.out.println(convRate);
			avgIncome = avgIncome*convRate;
			if (inputRecords.get(i).getCountry() != null)
				temp = new Pair(inputRecords.get(i).getCountry(), inputRecords.get(i).getGender());
			else
				temp = new Pair(inputRecords.get(i).getCity(), inputRecords.get(i).getGender());
			if (tm1.containsKey(temp)) {
				if (inputRecords.get(i).getCountry() != null) {
					tm1.replace(temp, tm1.get(temp)+(avgIncome));
					tm2.replace(temp, tm2.get(temp) + 1);
				} else {
					tm1.replace(temp, tm1.get(temp)+(avgIncome));
					tm2.replace(temp, tm2.get(temp) + 1);
				}

			} else {
				if (inputRecords.get(i).getCountry() != null) {
					tm1.put(temp, avgIncome);
					tm2.put(temp, 1);
				} else {
					tm1.put(temp, avgIncome);
					tm2.put(temp, 1);
				}
			}

		}
		Set set = tm1.entrySet();

		Iterator it = set.iterator();

		Set set2 = tm2.entrySet();

		Iterator it2 = set2.iterator();

		
		List<OutputRecord> outputRecords = new ArrayList<OutputRecord>();
		while (it.hasNext() && it2.hasNext()) {
			Map.Entry<Pair, Double> me = (Map.Entry) it.next();
			Map.Entry<Pair, Integer> me2 = (Map.Entry) it2.next();
			double avgIncome = me.getValue()/me2.getValue();

			OutputRecord or = new OutputRecord();
			or.setCityorcountry(me.getKey().country);
			or.setGender(me.getKey().gender);
			or.setAverageIncome(avgIncome);
			outputRecords.add(or);

		}
		return outputRecords;
	}
}