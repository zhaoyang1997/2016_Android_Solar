package net.solar.server.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.solar.server.entity.User;
import net.solar.server.service.DakaService;

@Controller
@RequestMapping("daka")
public class DakaController {

	@Autowired
	private DakaService dakaService;
	
	@RequestMapping(value="daka")
	public void insertDaka(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String date=request.getParameter("date");
		String address=request.getParameter("address");
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		dakaService.insertDaka(date, address, userId);
		writer.print("打卡成功");
		}
		

	@RequestMapping(value="dakanum")
	public void getDakaNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String date=request.getParameter("date");
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		int num=dakaService.selectDakaNum(userId);
		int tag=dakaService.selectDakaCurrent(date, userId);
		System.out.println(tag+"------");
		JSONArray array=new JSONArray();
		JSONObject obj=new JSONObject();
		obj.put("num", num);
		JSONObject obj1=new JSONObject();
		obj1.put("tag", tag);
		array.put(obj);
		array.put(obj1);
		writer.print(array.toString());
		}
		
}
