package net.solar.server.serviceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.TimeMapper;
import net.solar.server.dao.TomatoMapper;
import net.solar.server.entity.Time;
import net.solar.server.entity.Tomato;
import net.solar.server.service.TomatoService;

@Service
public class TomatoServiceImpl implements TomatoService{
	
	@Autowired
	private TomatoMapper tomatoMapper;
	
	@Autowired
	private TimeMapper timeMapper;

	@Override
	public int insertTomato(int userId, int tomatoCount, 
			int tomatoYear, int tomatoMonth, int tomatoDay) {
		// TODO Auto-generated method stub
		return tomatoMapper.
				insertTomato(userId, tomatoCount, tomatoYear, tomatoMonth, tomatoDay);
	}
	@Override
	public Integer getStaticWeekAndMonth(String date, String month, String year, int id) {
		// TODO Auto-generated method stub
		return tomatoMapper.getStaticWeekAndMonth(date, month, year, id);
	}

	@Override
	public Integer getStaticYear(String month, String yser, int userId) {
		// TODO Auto-generated method stub
		return tomatoMapper.getStaticYear(month, yser, userId);
	}
	@Override
	public Integer getStaticWeekAndMonthTask(String date, String month, String year, int id) {
		// TODO Auto-generated method stub
		return tomatoMapper.getStaticWeekAndMonthTask(date, month, year, id);
	}

	@Override
	public Integer getStaticYearTask(String month, String year, int userId) {
		// TODO Auto-generated method stub
		return tomatoMapper.getStaticYearTask(month, year, userId);
	}

	@Override
	public Integer getStaticYearTaskNOT(String month, String year, int userId) {
		// TODO Auto-generated method stub
		return tomatoMapper.getStaticYearTaskNOT(month, year, userId);
	}
	@Override
	public int insertTime(int userId, int time, String timedate) {
		// TODO Auto-generated method stub
		return timeMapper.insertTime(userId, time, timedate);
	}
	@Override
	public int updateTime(int time, int userId, String timedate) {
		// TODO Auto-generated method stub
		return timeMapper.updateTime(time, userId, timedate);
	}
	@Override
	public Time selectTime(int userId, String timedate) {
		// TODO Auto-generated method stub
		return timeMapper.selectTime(userId, timedate);
	}
	
	public List<Tomato> selectAllTomatoByUserId(int userId){
		return tomatoMapper.selectAllTomatoByUserId(userId);
	}


}
