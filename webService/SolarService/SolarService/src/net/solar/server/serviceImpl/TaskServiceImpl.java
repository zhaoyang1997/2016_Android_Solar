package net.solar.server.serviceImpl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.solar.server.dao.TaskMapper;
import net.solar.server.entity.Task;
import net.solar.server.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskMapper taskMapper;

	@Override
	public List<Task> findTaskNameByUserId(int id,String show) {
		// TODO Auto-generated method stub
		return taskMapper.findTaskNameByUserId(id,show);
	}
	
	@Override
	public int updateTaskById(int id,int task_id,
			String name,int time) {
		return taskMapper.updateTaskById(id, task_id, name, time);
	}

	@Override
	public int insertTask(Task task) {
		// TODO Auto-generated method stub
		return taskMapper.insertTask(task);
	}

	@Override
	public int updateTaskShowById(Task task) {
		// TODO Auto-generated method stub
		return taskMapper.updateTaskShowById(task);
	}
	
	@Override
	public int updateTaskStateById(int id, int userId,String taskState) {
		// TODO Auto-generated method stub
		return taskMapper.updateTaskStateById(id, userId,taskState);
	}

}
