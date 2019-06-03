package net.solar.server.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.TomatoMapper;
import net.solar.server.service.TomatoService;

@Service
public class TomatoServiceImpl implements TomatoService{
	
	@Autowired
	private TomatoMapper tomatoMapper;

	@Override
	public int insertTomato(int userId, int tomatoCount, 
			int tomatoYear, int tomatoMonth, int tomatoDay) {
		// TODO Auto-generated method stub
		return tomatoMapper.
				insertTomato(userId, tomatoCount, tomatoYear, tomatoMonth, tomatoDay);
	}
	
	

}
