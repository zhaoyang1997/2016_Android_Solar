package net.solar.server.service;

import org.apache.ibatis.annotations.Param;


public interface DakaService {
	public void insertDaka(String date,String address,int id);
	public int selectDakaNum(int id);
	public int selectDakaCurrent(String date,int id);


}
