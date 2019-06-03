package net.solar.server.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.UserMapper;
import net.solar.server.entity.User;
import net.solar.server.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapper userMapper;

	@Override
	public int updateImg(String userHead, int id) {
		// TODO Auto-generated method stub
		return userMapper.updateImg(userHead, id);
	}

	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		return userMapper.findUserById(id);
	}

}
