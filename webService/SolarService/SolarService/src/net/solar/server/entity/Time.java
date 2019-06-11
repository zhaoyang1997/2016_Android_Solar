package net.solar.server.entity;

public class Time {
	
	private int id;
	private int userId;
	private int time;
	private String timedate;
	public Time() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getTimedate() {
		return timedate;
	}
	public void setTimedate(String timedate) {
		this.timedate = timedate;
	}

	

}
