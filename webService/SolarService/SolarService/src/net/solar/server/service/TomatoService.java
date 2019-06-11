package net.solar.server.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Time;
import net.solar.server.entity.Tomato;

public interface TomatoService {

	public int insertTomato(int userId,int tomatoCount,
			int tomatoYear,int tomatoMonth,int tomatoDay);
	public Integer getStaticWeekAndMonth(@Param("day")String date,
			@Param("month")String month,
			@Param("year")String year,
			@Param("userId")int id);
	public Integer getStaticYear(@Param("month")String month,
			@Param("year")String yser,
			@Param("userId")int userId);
	public Integer getStaticWeekAndMonthTask(
			@Param("day")String date,
			@Param("month")String month,
			@Param("year")String year,@Param("userId")int id);
	public Integer getStaticYearTask(
			@Param("month")String month,
			@Param("year")String year,@Param("userId")int userId);
	public Integer getStaticYearTaskNOT(
			@Param("month")String month,
			@Param("year")String year,@Param("userId")int userId);
	
	public int insertTime(@Param("userId") int userId,
			@Param("time") int time,
			@Param("timedate") String timedate);
	
	public int updateTime(@Param("time") int time,
			@Param("userId") int userId,
			@Param("timedate") String timedate);
	
	public Time selectTime(@Param("userId") int userId,
			@Param("timedate") String timedate);
	
	public List<Tomato> selectAllTomatoByUserId(@Param("userId") int userId);

}
