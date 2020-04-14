package sample;

public class Invoice {
	private String number;
	private double cost;
	private String date;
	private String reason;
	
	
	public Invoice(String number, double cost, String date, String reason) {
		this.number = number;
		this.cost = cost;
		this.date = date;
		this.reason = reason;
	}
	
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	

}
