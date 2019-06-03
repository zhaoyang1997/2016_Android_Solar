package net.solar.server.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

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
		int tomatoCount=0;
		BufferedReader reader = request.getReader();
		String json = reader.readLine();
		if(json == null){
			System.out.println("没有数据");
		}else{
			tomatoCount= Integer.valueOf(json);
		}
		
		//获取插入番茄时间记录的日期
		int[] date = DateTimeUtil.getDate();
		System.out.println("zn--test--year : "+date[0]);
		System.out.println("zn--test--month : "+date[1]);
		System.out.println("zn--test--day : "+date[2]);
	
		int i = tomatoService.insertTomato(1,tomatoCount,date[0],date[1],date[2]);
		System.out.println(i);
	}
	
	/**
	 * 上传头像
	 */
	@RequestMapping("index")
	public void index(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		
		response.setContentType("text/html;charset=utf-8");
		ServletInputStream in = request.getInputStream();
		
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
			response.getWriter().println("头像上传成功");
		}else {
			System.out.println("zn--test--img : 客户端头像上传失败");
		}
		int i = this.userService.updateImg(filename+".jpg",1);
		System.out.println("zn--test--updateImg: "+ i);
		
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
		}
		
		User user = userService.findUserById(id);
		Gson gson = new Gson();
		String str = gson.toJson(user);
		response.getWriter().println(str);
	
	}

}
