package net.solar.server.entity;

public class Ring {
	private int id;
	private String ringName;
	private String ringFile;
	
	
	public Ring() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRingName() {
		return ringName;
	}
	public void setRingName(String ringName) {
		this.ringName = ringName;
	}
	public String getRingFile() {
		return ringFile;
	}
	public void setRingFile(String ringFile) {
		this.ringFile = ringFile;
	}
	
	@Override
	public String toString() {
		return "Setting [id=" + id + ", ringName=" + ringName + ", ringFile=" + ringFile + "]";
	}
	
	
}
