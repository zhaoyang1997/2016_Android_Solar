package net.solar.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Task;

public interface TaskMapper {

	/*
	 * 根据用户id查找任务名称 
	 */
	
	public List<Task> findTaskNameByUserId(@Param("id")int id,@Param("show")String show);
	/**
	 * 根据任务id修改任务
	 */
	public int updateTaskById(@Param("uid")int id,@Param("tid")int task_id,
			@Param("name")String name,@Param("time")int time);
	/**
	 * 更新任务的显示状态
	 */
	public int updateTaskShowById(Task task);
	/**
	 * 添加任务
	 */
	public int insertTask(Task task);
	
	/**
	 * 更改任务完成状态
	 * @param id
	 * @param userId
	 * @return
	 */
	public int updateTaskStateById(@Param("id")int id,@Param("userId")int userId,@Param("taskState")String taskState);

	
}
