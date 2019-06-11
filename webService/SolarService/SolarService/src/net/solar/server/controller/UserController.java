package net.solar.server.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import net.solar.server.entity.Time;
import net.solar.server.entity.User;
import net.solar.server.service.TomatoService;
import net.solar.server.service.UserService;
import net.solar.server.util.DateTimeUtil;
import net.solar.server.util.NamedUtil;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TomatoService tomatoService;
	
	/**
	 * 插入一条番茄时间记录
	 */
	@RequestMapping(value="tomato")
	public void tomato(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		String count = request.getParameter("count");
		String id = request.getParameter("userId");
		int tomatoCount= Integer.parseInt(count.trim());
		int userId = Integer.parseInt(id.trim());
		int tomatoTime=0;
		
		//25,45,60,90,120
		if(tomatoCount==25){tomatoTime = 1;
		}else if(tomatoCount == 45){tomatoTime=2;
		}else if(tomatoCount == 60){tomatoTime=3;
		}else if(tomatoCount == 90){tomatoTime=4;
		}else{tomatoTime=5;}
		
		//获取日期
		int[] date = DateTimeUtil.getDate();
		System.out.println("zn--test--year : "+date[0]);
		System.out.println("zn--test--month : "+date[1]);
		System.out.println("zn--test--day : "+date[2]);
		System.out.println("zn--test--userId : "+ userId);
		System.out.println("zn--test--count : "+ tomatoCount);
		
		String timedate = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
		
		Time t= tomatoService.selectTime(userId,timedate);
		if( t != null){
			int time = t.getTime()+tomatoCount;
			tomatoService.updateTime(time, userId, timedate);
		}else{
			int time = tomatoCount;
			tomatoService.insertTime(userId, time, timedate);
		}
	
		int i = tomatoService.insertTomato(userId,tomatoTime,date[0],date[1],date[2]);
		System.out.println(i);

		
	}
	
	/**
	 * 上传头像
	 */
	@RequestMapping("index")
	public void index(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html;charset=utf-8");
		int userId = (int) request.getSession().getAttribute("userId");
		ServletInputStream in = request.getInputStream();
	//	String id = request.getParameter("userId");
		System.out.println(userId);
//		NamedUtil.name() : 生成图片名字的类方法
		String filename = NamedUtil.name();
		String path = request.getSession().getServletContext()
				.getRealPath("/images/"+filename +".jpg");
		
		File file = new File(path);
		if(!file.exists()) {
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		
		FileOutputStream out = new FileOutputStream(file);
		if(in.available()>0) {
			int b = -1;
			while((b=in.read())!=-1){
				out.write(b);
				out.flush();
			}
			System.out.println("zn--test--img ： 客户端头像上传成功");
			response.getWriter().println(filename);
			
			int i = this.userService.updateImg(filename+".jpg",userId);
			System.out.println("zn--test--updateImg: "+ i);
		}else {
			System.out.println("zn--test--img : 客户端头像上传失败");
		}
		
		
		
	}
	
	/**
	 * 我的页面--根据id获取用户头像和名字，显示在客户端
	 */
	@RequestMapping("find")
	public void find(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		int id=0;
		BufferedReader reader = request.getReader();
		String json = reader.readLine();
		if(json == null){
			System.out.println("没有数据传入");
		}else{
			id = Integer.valueOf(json);
			request.getSession().setAttribute("userId", id);
		}
		
		User user = userService.findUserById(id);
		Gson gson = new Gson();
		String str = gson.toJson(user);
		response.getWriter().println(str);
	
	}
	@RequestMapping("/login")
	public void login(HttpServletRequest request,HttpServletResponse response) throws IOException {
	
		BufferedReader reader = request.getReader();
        String json = reader.readLine();
        if(json == null) {
        	System.out.println("没有数据传入！");
        }else {
        	System.out.println("有数据传入！");
        	System.out.println(json);
        	
    		User user = userService.findUserByEmail(json);
//    		System.out.println(user.toString());
    		PrintWriter out = response.getWriter();
    		Gson gson = new Gson();
    		String str = gson.toJson(user);
    		out.println(str);
    		
        }
        
	}
	@RequestMapping("register")
	public void getLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String email=request.getParameter("email");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		User user1=userService.selectUserByEmail(email);
		if(user1!=null) {
			writer.print("2");
		}else {
			User user2=userService.selectUserByName(userName);
			if(user2!=null) {
				writer.print("3");
			}else {
				User user3=new User();
				user3.setEmail(email);
				user3.setUserName(userName);
				user3.setPassword(password);
				user3.setUserHead("astronaut.jpg");
				userService.insertUser(user3);
				writer.print("1");
			}
		}
	}
	
	@RequestMapping("getrank")
	public void getRank(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		Date td = new Date();
		String timedate = new SimpleDateFormat("yyyyMMdd").format(new Date()).toString();
		List<User> userList=userService.selectMax(timedate);
		JSONArray array=new JSONArray();
		for(User i:userList) {
			JSONObject obj=new JSONObject();
			obj.put("img", i.getUserHead());
			obj.put("name", i.getUserName());
			obj.put("time", i.getTime().getTime());
			array.put(obj);
		}
		writer.print(array.toString());
		}

}
