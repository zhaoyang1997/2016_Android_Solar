package net.solar.server.dao;

import org.apache.ibatis.annotations.Param;

public interface DakaMapper {
	public void insertDaka(@Param("date")String date,@Param("address")String address,@Param("userId")int id);
	public int selectDakaNum(int id);
	public int selectDakaCurrent(@Param("date")String date,@Param("userId")int id);
}
