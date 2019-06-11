package net.solar.server.dao;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Time;

public interface TimeMapper {
	
	public int insertTime(@Param("userId") int userId,
			@Param("time") int time,
			@Param("timedate") String timedate);
	
	public int updateTime(@Param("time") int time,
			@Param("userId") int userId,
			@Param("timedate") String timedate);
	
	public Time selectTime(@Param("userId") int userId,
			@Param("timedate") String timedate);


}
