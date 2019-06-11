package net.solar.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.solar.server.entity.Matter;
import net.solar.server.service.MatterService;

@Controller
public class MatterController {
	@Autowired
	private MatterService matterService;
	
	@RequestMapping(value="addmatter")
	public void insertMatter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String date=request.getParameter("date");
		String matter=request.getParameter("matter");
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		System.out.println(date);
		matterService.insertMatter(date, matter, userId);
		writer.print("添加成功");
		}
	

	@RequestMapping(value="getmatter")
	public void getMatter(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		List<Matter> matterList=matterService.selectMatter(userId);
		JSONArray array=new JSONArray();
		for(Matter i:matterList) {
			JSONObject obj=new JSONObject();
			obj.put("date", i.getMatterTime());
			obj.put("matter", i.getMatterName());
			array.put(obj);
		}
		writer.print(array.toString());
		}
}
