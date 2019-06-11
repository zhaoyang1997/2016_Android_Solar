package net.solar.server.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.User;

public interface UserService {
	
	public int updateImg(@Param("head") String userHead,@Param("id") int id);

	public User findUserById(@Param("id") int id); 

	public User selectUserByName(String userName);
	
	public User selectUserByEmail(String email);
	
	public void insertUser(User use);
	
	public User findUserByEmail(String email);
	
	public List<User> selectMax(String timeDate);
}
