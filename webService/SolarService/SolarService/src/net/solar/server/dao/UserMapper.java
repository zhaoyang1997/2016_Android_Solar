package net.solar.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.User;

public interface UserMapper {
	
	/**
	 * 更新用户头像
	 * @param userHead
	 * @param id
	 * @return
	 */
	public int updateImg(@Param("head") String userHead,@Param("id") int id);
	
	/**
	 * 通过ID查询用户数据
	 * @param id
	 * @return
	 */
	public User findUserById(@Param("id") int id); 
	/**
	 * 添加用户信息
	 * @param use
	 */
	public void insertUser(User use);
	/**
	 * 查找用户名称是否重复
	 * @param userName
	 * @return
	 */
	public User selectUserByName(String userName);
	/**
	 * 查找用户邮箱是否重复
	 * @param email
	 * @return
	 */
	public User selectUserByEmail(String email);
	/**
	 * 根据邮箱查找用户信息
	 * @param email
	 * @return
	 */
	public User findUserByEmail(String email);
	/**
	 * 根据时间查排行
	 * @param email
	 * @return
	 */
	public List<User> selectMax(String timeDate);
}
