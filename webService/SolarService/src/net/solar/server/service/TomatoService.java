package net.solar.server.service;

import org.apache.ibatis.annotations.Param;

public interface TomatoService {
	
	/**
	 * ����һ�����Ѽ�¼
	 */
	public int insertTomato(int userId,int tomatoCount,
			int tomatoYear,int tomatoMonth,int tomatoDay);

}
