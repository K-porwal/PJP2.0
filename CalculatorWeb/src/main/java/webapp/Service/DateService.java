package webapp.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import webapp.MyDate;

@Service
public class DateService {

	public String addDates(MyDate first, MyDate second) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c1 = Calendar.getInstance();
		try {
			Date firstDate = desiredFormat.parse(first.getDate());
			Date secondDate = desiredFormat.parse(second.getDate());
			Calendar c2 = Calendar.getInstance();
			c1.setTime(firstDate);
			c2.setTime(secondDate);
			c1.add(Calendar.YEAR, c2.get(Calendar.YEAR));
			c1.add(Calendar.MONTH, c2.get(Calendar.MONTH) + 1); // Zero-based months
			c1.add(Calendar.DATE, c2.get(Calendar.DATE));
			c1.add(Calendar.HOUR_OF_DAY, c2.get(Calendar.HOUR_OF_DAY));
			c1.add(Calendar.MINUTE, c2.get(Calendar.MINUTE));
			c1.add(Calendar.SECOND, c2.get(Calendar.SECOND));
			c1.add(Calendar.MILLISECOND, c2.get(Calendar.MILLISECOND));
			return desiredFormat.format(c1.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int subtractDates(MyDate first, MyDate second) {
		int days = 0;
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date firstDate = desiredFormat.parse(first.getDate());
			Date secondDate = desiredFormat.parse(second.getDate());
			long difference = secondDate.getTime() - firstDate.getTime();
			if (difference < 0)
				difference *= -1;
			days = (int) (difference / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return days;
	}

	public String addNUnits(MyDate date, int units) {

		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date givenDate = desiredFormat.parse(date.getDate());
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			Calendar c3 = Calendar.getInstance();
			c1.setTime(givenDate);
			c2.setTime(givenDate);
			c3.setTime(givenDate);

			c1.add(Calendar.DAY_OF_MONTH, units); 
			c2.add(Calendar.DAY_OF_MONTH, units * 7); 
			c3.add(Calendar.DAY_OF_MONTH, units * 30); 

			return "Added N units of days  " + desiredFormat.format(c1.getTime()) + "Added N units of weeks  "
					+ desiredFormat.format(c2.getTime()) + "Added N units of months  " + desiredFormat.format(c3.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String subtractNUnits(MyDate date, int units) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date givenDate = desiredFormat.parse(date.getDate());
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			Calendar c3 = Calendar.getInstance();
			c1.setTime(givenDate);
			c2.setTime(givenDate);
			c3.setTime(givenDate);

			c1.add(Calendar.DAY_OF_MONTH,  (-1) * units );

			c2.add(Calendar.DAY_OF_MONTH, (-1) * 7 * units);

			c3.add(Calendar.DAY_OF_MONTH, (-1) * 30 * units);

			return "Subtracted N units of days  " + desiredFormat.format(c1.getTime()) + "  Subtracted N units of weeks  "
					+ desiredFormat.format(c2.getTime()) + "  Subtracted N units of months  " + desiredFormat.format(c3.getTime());
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return null;
	}

	public int findWeekNumber(MyDate date) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(desiredFormat.parse(date.getDate()));
			return calendar.get(Calendar.WEEK_OF_YEAR);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String whichDay(MyDate date) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("EEEE");
		SimpleDateFormat dateConverter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return desiredFormat.format(dateConverter.parse(date.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String convertToDate(String naturalDate) {
		SimpleDateFormat desiredFormat = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();

		if (naturalDate.equalsIgnoreCase("today")) {
			Date date = c.getTime();
			return desiredFormat.format(date);
		} else if (naturalDate.equalsIgnoreCase("yesterday")) {
			c.add(Calendar.DAY_OF_MONTH, -1);
			Date date = c.getTime();
			return desiredFormat.format(date);

		} else if (naturalDate.equalsIgnoreCase("tomorrow")) {
			c.add(Calendar.DAY_OF_MONTH, 1);
			Date date = c.getTime();
			return desiredFormat.format(date);
		} else if (naturalDate.equalsIgnoreCase("daybeforeyesterday")) {
			c.add(Calendar.DAY_OF_MONTH, -2);
			Date date = c.getTime();
			return desiredFormat.format(date);
		} else if (naturalDate.equalsIgnoreCase("dayaftertomorrow")) {
			c.add(Calendar.DAY_OF_MONTH, 2);
			Date date = c.getTime();
			return desiredFormat.format(date);
		}
		return null;

	}

	public boolean isValidDate(String str) {
		DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setLenient(false);
		try {
			sdf.parse(str);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

}
