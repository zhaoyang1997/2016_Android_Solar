package net.solar.server.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Task;

public interface TaskService {
	public List<Task> findTaskNameByUserId(@Param("id")int id,@Param("show")String show);
	
	public int updateTaskShowById(Task task);
	
	public int updateTaskById(@Param("uid")int id,@Param("tid")int task_id,
			@Param("name")String name,@Param("time")int time);
	
	public int insertTask(Task task);
	public int updateTaskStateById(@Param("id")int id,@Param("userId")int userId,@Param("taskState")String taskState);

}
