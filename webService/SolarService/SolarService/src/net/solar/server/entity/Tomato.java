package net.solar.server.entity;

public class Tomato {
	private int id;
	private int userId;
	private int tomatoCount;
	private int tomatoYear;
	private int tomatoMonth;
	private int tomatoDay;
	
	
	public Tomato() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTomatoCount() {
		return tomatoCount;
	}
	public void setTomatoCount(int tomatoCount) {
		this.tomatoCount = tomatoCount;
	}
	
	public int getTomatoYear() {
		return tomatoYear;
	}
	public void setTomatoYear(int tomatoYear) {
		this.tomatoYear = tomatoYear;
	}
	
	public int getTomatoMonth() {
		return tomatoMonth;
	}
	public void setTomatoMonth(int tomatoMonth) {
		this.tomatoMonth = tomatoMonth;
	}
	
	public int getTomatoDay() {
		return tomatoDay;
	}
	public void setTomatoDay(int tomatoDay) {
		this.tomatoDay = tomatoDay;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
