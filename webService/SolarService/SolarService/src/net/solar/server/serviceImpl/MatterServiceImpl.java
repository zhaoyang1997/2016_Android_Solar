package net.solar.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.MatterMapper;
import net.solar.server.entity.Matter;
import net.solar.server.service.MatterService;
@Service
public class MatterServiceImpl implements MatterService {
	@Autowired
	private MatterMapper matterMapper;

	@Override
	public void insertMatter(String date, String matter, int id) {
		// TODO Auto-generated method stub
		matterMapper.insertMatter(date, matter, id);
	}

	@Override
	public List<Matter> selectMatter(int id) {
		// TODO Auto-generated method stub
		return matterMapper.selectMatter(id);
	}

}
