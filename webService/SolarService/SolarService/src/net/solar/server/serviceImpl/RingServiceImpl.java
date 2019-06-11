package net.solar.server.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.RingMapper;
import net.solar.server.entity.Ring;
import net.solar.server.service.RingService;

@Service
public class RingServiceImpl implements RingService {

	@Autowired
	private RingMapper ringMapper;
	@Override
	public List<Ring> findRings() {
		// TODO Auto-generated method stub
		return ringMapper.findRings();
	}

}
