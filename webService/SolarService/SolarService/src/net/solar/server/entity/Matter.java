package net.solar.server.entity;

public class Matter {
	private int id;
	private int userId;
	private String matterName;
	private String matterTime;
	
	public Matter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Matter(String matterName, String matterTime) {
		super();
		this.matterName = matterName;
		this.matterTime = matterTime;
	}

	public Matter(int userId, String matterName, String matterTime) {
		super();
		this.userId = userId;
		this.matterName = matterName;
		this.matterTime = matterTime;
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
	public String getMatterName() {
		return matterName;
	}
	public void setMatterName(String matterName) {
		this.matterName = matterName;
	}
	public String getMatterTime() {
		return matterTime;
	}
	public void setMatterTime(String matterTime) {
		this.matterTime = matterTime;
	}
	

}
