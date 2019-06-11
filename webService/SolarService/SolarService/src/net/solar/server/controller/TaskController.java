package net.solar.server.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import net.solar.server.entity.Task;
import net.solar.server.entity.User;
import net.solar.server.serviceImpl.TaskServiceImpl;
import net.solar.server.serviceImpl.UserServiceImpl;

@RequestMapping("/task")
@Controller
public class TaskController {
	@Autowired
	private TaskServiceImpl taskServiceImpl;
	
	@RequestMapping("/findTaskName")
	public void findTaskName(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
		BufferedReader reader = request.getReader();
        String json = reader.readLine();
        if(json == null) {
        	System.out.println("没有数据传入！");
        }else {
        	System.out.println("有数据传入！");
        	System.out.println(json);
        	int id = Integer.parseInt(json);
        	Task task =new Task();
        	task.setTaskShow("1");
        	String s=task.getTaskShow();
    		List<Task> tasks= taskServiceImpl.findTaskNameByUserId(id,s);
    	
    		System.out.println("liyabign" + tasks.toString());
    		PrintWriter out = response.getWriter();
    		Gson gson = new Gson();
    		String str = gson.toJson(tasks);
    		out.println(str);
    		
        }       
	}

	@RequestMapping("/addTask")
	public void addTask(HttpServletRequest request,HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
    	PrintWriter writer = response.getWriter();
    	Task task = new Task();
    	if(request.getParameter("Tname") == null) {
    		System.out.println("没有数据传入");
    	}else {
    		System.out.println("有数据传入");
    		String id = request.getParameter("userId");
        	if(id != null && !id.equals("")) {
        		task.setId(Integer.parseInt(id));
        	}
        	task.setTaskName(request.getParameter("Tname"));
    	    task.setTaskYear(request.getParameter("Tyear"));
    	    task.setTaskMonth(request.getParameter("Tmonth"));
    	    task.setTaskDay(request.getParameter("Tday"));
    	    task.setTaskState("0");
    	    task.setTaskShow("1");
    	    String time = request.getParameter("Ttime");
        	if(time != null && !time.equals("")) {
        		task.setTaskTime(Integer.parseInt(time));
        	}
    		    taskServiceImpl.insertTask(task);
    		    writer.println("1");
    	
    	}
	}	
	
	@RequestMapping("/deleteTask")
	public void deleteTask3(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(request.getParameter("userId")==null) {
			System.out.println("没有数据传入！");
		}else {
			System.out.println("有数据传入！");
			String userId=request.getParameter("userId");	
			String tId=request.getParameter("Tid");
        	Task task=new Task();
        	task.setId(Integer.parseInt(tId));
        	task.setTaskShow("0");
        	task.setUserId(Integer.parseInt(userId));	
        	taskServiceImpl.updateTaskShowById(task);
    		PrintWriter out = response.getWriter();
    		out.println("1");
		}
	}
	
	@RequestMapping("/updateTaskState")
	public void updateTaskState(HttpServletRequest request,HttpServletResponse response) throws IOException {
		if(request.getParameter("userId")==null) {
			System.out.println("没有数据传入！");
		}else {
			System.out.println("有数据传入！");
			String userId=request.getParameter("userId");	
			String tId=request.getParameter("Tid");
        	
        	taskServiceImpl.updateTaskStateById(Integer.parseInt(tId), Integer.parseInt(userId),"1");
    		PrintWriter out = response.getWriter();
    		out.println("1");
		}
	}
	@RequestMapping("/updateTask")
	public void updateTask2(HttpServletRequest request,HttpServletResponse response)throws IOException{
		
		if(request.getParameter("Tname")==null) {
			System.out.println("没有数据传入！");
		}else {
			Task task = new Task();
			System.out.println("有数据传入！");
        	String id=request.getParameter("userId");
        	if(id != null && !id.equals("")) {
//        		task.setId(Integer.parseInt(id));
        		int uid=Integer.parseInt(id);
        	}
        	String tid=request.getParameter("position");
        	if(tid != null && !id.equals("")) {
        		task.setId(Integer.parseInt(tid));
        	}
        	String name=request.getParameter("Tname");
        	task.setTaskName(name);
        	String time=request.getParameter("Ttime");
        	if(time!= null && !time.equals("")) {
        		task.setTaskTime(Integer.parseInt(time));
        	}
        	taskServiceImpl.updateTaskById(Integer.parseInt(id),task.getId(),task.getTaskName(),task.getTaskTime());
        	
    		PrintWriter out = response.getWriter();
    		out.println("1");
		}
	}

}
