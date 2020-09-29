package webapp;

public class MyDate {
	private String date;


	public MyDate(String date) {
		this.date = date;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString() {
		return getDate();
	}
}