package net.solar.server.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.DakaMapper;
import net.solar.server.service.DakaService;
@Service
public class DakaServiceImpl implements DakaService {

	@Autowired
	private DakaMapper dakaMapper;
	@Override
	public void insertDaka(String date, String address, int id) {
		// TODO Auto-generated method stub
		dakaMapper.insertDaka(date, address, id);
	}
	@Override
	public int selectDakaNum(int id) {
		// TODO Auto-generated method stub
		return dakaMapper.selectDakaNum(id);
	}
	@Override
	public int selectDakaCurrent(String date, int id) {
		// TODO Auto-generated method stub
		return dakaMapper.selectDakaCurrent(date, id);
	}

}
