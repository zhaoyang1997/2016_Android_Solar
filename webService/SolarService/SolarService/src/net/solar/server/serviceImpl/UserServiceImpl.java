package net.solar.server.serviceImpl;

import java.util.List;

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
	@Override
	public User selectUserByName(String userName) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByName(userName);
	}

	@Override
	public User selectUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.selectUserByEmail(email);
	}

	@Override
	public void insertUser(User use) {
		// TODO Auto-generated method stub
		userMapper.insertUser(use);
	}

	@Override
	public User findUserByEmail(String email) {
		// TODO Auto-generated method stub
		return userMapper.findUserByEmail(email);
	}

	@Override
	public List<User> selectMax(String timeDate) {
		// TODO Auto-generated method stub
		return userMapper.selectMax(timeDate);
	}

}
