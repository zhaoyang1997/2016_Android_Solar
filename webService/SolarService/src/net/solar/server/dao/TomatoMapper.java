package net.solar.server.dao;

import org.apache.ibatis.annotations.Param;

public interface TomatoMapper {

	/**
	 * 插入一条番茄记录
	 */
	public int insertTomato(@Param("userId") int userId,
			@Param("tomatoCount")int tomatoCount,
			@Param("tomatoYear") int tomatoYear,
			@Param("tomatoMonth") int tomatoMonth,
			@Param("tomatoDay") int tomatoDay);
	
}
