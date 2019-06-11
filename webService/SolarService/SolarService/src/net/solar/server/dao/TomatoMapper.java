package net.solar.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Tomato;

public interface TomatoMapper {

	/**
	 * 插入一条番茄记录
	 */
	public int insertTomato(@Param("userId") int userId,
			@Param("tomatoCount")int tomatoCount,
			@Param("tomatoYear") int tomatoYear,
			@Param("tomatoMonth") int tomatoMonth,
			@Param("tomatoDay") int tomatoDay);
	/**
	 * 获取用户每周/月数据
	 * @param date
	 * @param month
	 * @param year
	 * @param id
	 * @return
	 */
	public Integer getStaticWeekAndMonth(
			@Param("day")String date,
			@Param("month")String month,
			@Param("year")String year,
			@Param("userId")int id);
	/**
	 * 获取用户一年的数据
	 * @param month
	 * @param yser
	 * @param userId
	 * @return
	 */
	public Integer getStaticYear(
			@Param("month")String month,
			@Param("year")String yser,
			@Param("userId")int userId);
	
	public Integer getStaticWeekAndMonthTask(
			@Param("day")String date,
			@Param("month")String month,
			@Param("year")String year,
			@Param("userId")int id);
	
	public Integer getStaticYearTask(
			@Param("month")String month,
			@Param("year")String year,
			@Param("userId")int userId);
	
	public Integer getStaticYearTaskNOT(
			@Param("month")String month,
			@Param("year")String year,
			@Param("userId")int userId);
	
	/**
	 * 查找指定用户所有的番茄
	 */
	public List<Tomato> selectAllTomatoByUserId(@Param("userId") int userId);

}
