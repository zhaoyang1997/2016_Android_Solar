package net.solar.server.service;

import org.apache.ibatis.annotations.Param;

public interface TomatoService {
	
	/**
	 * 插入一条番茄记录
	 */
	public int insertTomato(int userId,int tomatoCount,
			int tomatoYear,int tomatoMonth,int tomatoDay);

}
