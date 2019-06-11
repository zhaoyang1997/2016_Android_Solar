package net.solar.server.entity;

public class Task {
	private int id;
	private String taskName;
	private String taskState;
	private int taskTime;
	private String taskYear;
	private String taskMonth;
	private String taskDay;
	private String taskShow;
	private int userId;
	public Task() {
		super();
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	public String getTaskState() {
		return taskState;
	}
	public void setTaskState(String taskState) {
		this.taskState = taskState;
	}
	public int getTaskTime() {
		return taskTime;
	}
	public void setTaskTime(int taskTime) {
		this.taskTime = taskTime;
	}
	public String getTaskYear() {
		return taskYear;
	}
	public void setTaskYear(String taskYear) {
		this.taskYear = taskYear;
	}
	public String getTaskMonth() {
		return taskMonth;
	}
	public void setTaskMonth(String taskMonth) {
		this.taskMonth = taskMonth;
	}
	public String getTaskDay() {
		return taskDay;
	}
	public void setTaskDay(String taskDay) {
		this.taskDay = taskDay;
	}
	
	public String getTaskShow() {
		return taskShow;
	}
	public void setTaskShow(String taskShow) {
		this.taskShow = taskShow;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", taskName=" + taskName + "]";
	}
	
	
}
