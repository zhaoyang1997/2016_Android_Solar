package net.solar.server.dao;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.User;

public interface UserMapper {
	
	/**
	 * 根据id 更换头像
	 */
	public int updateImg(@Param("head") String userHead,@Param("id") int id);
	
	/**
	 * 根据id查找用户
	 */
	public User findUserById(@Param("id") int id); 

}
