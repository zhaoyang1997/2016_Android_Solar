package net.solar.server.service;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.User;

public interface UserService {
	
	/**
	 * ����id ����ͷ��
	 */
	public int updateImg(@Param("head") String userHead,@Param("id") int id);
	
	/**
	 * ����id�����û�
	 */
	public User findUserById(@Param("id") int id); 

}
