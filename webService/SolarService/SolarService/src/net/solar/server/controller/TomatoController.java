package net.solar.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import net.solar.server.entity.ScoreDetails;
import net.solar.server.entity.Tomato;
import net.solar.server.service.TomatoService;

@Controller
@RequestMapping("tomato")
public class TomatoController {
	@Autowired
	private TomatoService tomatoService;
	
	@RequestMapping(value="static")
	public void getLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String day=request.getParameter("day");
		String month=request.getParameter("month");
		String year=request.getParameter("year");
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		
		List<Integer> weekList=new ArrayList();
		
		for(int i=0;i<7;i++) {
			Integer tomato=tomatoService.getStaticWeekAndMonth(day, month, year, userId);
		
			weekList.add(tomato);
			int date1=Integer.parseInt(day);
			int month1=Integer.parseInt(month);
			int year1=Integer.parseInt(year);
			date1--;
			if(date1==0) {
				month1--;
				if(month1==0) {
					month1=12;
					year1--;
				}
				if(month1==11||month1==9||month1==6||month1==4) {
					day="30";
				}else if(month1==2) {
					if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
						day="28";
					}else {
						day="29";
					}
				}else {
					day="31";
				}
				
			}else if(date1==9) {
				day="09";
			}else if(date1==8) {
				day="08";
			}else if(date1==7) {
				day="07";
			}else if(date1==6) {
				day="06";
			}else if(date1==5) {
				day="05";
			}else if(date1==4) {
				day="04";
			}else if(date1==3) {
				day="03";
			}else if(date1==2) {
				day="02";
			}else if(date1==1) {
				day="01";
			}else {
				day=String.valueOf(date1);
			}
			if(month1==1) {
				month="01";
			}else if(month1==2) {
				month="02";
			}
			else if(month1==3) {
				month="03";
			}
			else if(month1==4) {
				month="04";
			}
			else if(month1==5) {
				month="05";
			}
			else if(month1==6) {
				month="06";
			}
			else if(month1==7) {
				month="07";
			}
			else if(month1==8) {
				month="08";
			}
			else if(month1==9) {
				month="09";
			}else {
				month=String.valueOf(month1);
			}
			year=String.valueOf(year1);
		}
		
		List<Integer> monthList=new ArrayList();
		for(int i=0;i<30;i++) {
			Integer tomato=tomatoService.getStaticWeekAndMonth(day, month, year, userId);
			monthList.add(tomato);
			int date1=Integer.parseInt(day);
			int month1=Integer.parseInt(month);
			int year1=Integer.parseInt(year);
			date1--;
			if(date1==0) {
				month1--;
				if(month1==0) {
					month1=12;
					year1--;
				}
				if(month1==11||month1==9||month1==6||month1==4) {
					day="30";
				}else if(month1==2) {
					if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
						day="28";
					}else {
						day="29";
					}
				}else {
					day="31";
				}
				
			}
			else if(date1==9) {
				day="09";
			}else if(date1==8) {
				day="08";
			}else if(date1==7) {
				day="07";
			}else if(date1==6) {
				day="06";
			}else if(date1==5) {
				day="05";
			}else if(date1==4) {
				day="04";
			}else if(date1==3) {
				day="03";
			}else if(date1==2) {
				day="02";
			}else if(date1==1) {
				day="01";
			}else {
				day=String.valueOf(date1);
			}
			
			if(month1==1) {
				month="01";
			}else if(month1==2) {
				month="02";
			}
			else if(month1==3) {
				month="03";
			}
			else if(month1==4) {
				month="04";
			}
			else if(month1==5) {
				month="05";
			}
			else if(month1==6) {
				month="06";
			}
			else if(month1==7) {
				month="07";
			}
			else if(month1==8) {
				month="08";
			}
			else if(month1==9) {
				month="09";
			}else {
				month=String.valueOf(month1);
			}
			year=String.valueOf(year1);
			
		}
		
		List<Integer> yearList=new ArrayList();
		for(int i=0;i<12;i++) {
			Integer tomato=tomatoService.getStaticYear(month, year, userId);
			yearList.add(tomato);
			int month1=Integer.parseInt(month);
			month1--;
			if(month1==0) {
				int year1=Integer.parseInt(year);
				year1--;
				year=String.valueOf(year1);
				month="12";
			}else if(month1==1) {
				month="01";
			}else if(month1==2) {
				month="02";
			}
			else if(month1==3) {
				month="03";
			}
			else if(month1==4) {
				month="04";
			}
			else if(month1==5) {
				month="05";
			}
			else if(month1==6) {
				month="06";
			}
			else if(month1==7) {
				month="07";
			}
			else if(month1==8) {
				month="08";
			}
			else if(month1==9) {
				month="09";
			}else {
				month=String.valueOf(month1);
			}
		}
		
		JSONArray array=new JSONArray();
		for(Integer i:weekList) {
			JSONObject obj=new JSONObject();
			if(i==null) {
				obj.put("w", 0);
			}else {
			obj.put("w", i);
			}
			array.put(obj);
		}
		for(Integer i:monthList) {
			JSONObject obj=new JSONObject();
			if(i==null) {
				obj.put("m", 0);
			}else {
			obj.put("m", i);
			}
			array.put(obj);
		}
		/*for(Integer i:yearList) {
			JSONObject obj=new JSONObject();
			obj.put("y", i);
			array.put(obj);
		}*/
		writer.append(array.toString());
	}
	@RequestMapping(value="statictemp")
	public void getStatic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter writer=response.getWriter();
		String day=request.getParameter("day");
		String dayTemp=day;
		String month=request.getParameter("month");
		String monthTemp=month;
		String year=request.getParameter("year");
		String yearTemp=year;
		String id=request.getParameter("userId");
		int userId=Integer.parseInt(id);
		
		List<Integer> weekList=new ArrayList();
		List<Integer> monthList=new ArrayList();
		for(int i=0;i<7;i++) {
			Integer tomato=tomatoService.getStaticWeekAndMonth(day, month, year, userId);
			Integer task=tomatoService.getStaticWeekAndMonthTask(day, month, year, userId);
			if(tomato==null)
				tomato=0;
			if(task==null)
				task=0;
			int sum=tomato+task;
			weekList.add(sum);
			monthList.add(sum);
			int date1=Integer.parseInt(day);
			int month1=Integer.parseInt(month);
			int year1=Integer.parseInt(year);
			date1--;
			if(date1==0) {
				month1--;
				if(month1==0) {
					month1=12;
					year1--;
				}
				if(month1==11||month1==9||month1==6||month1==4) {
					day="30";
				}else if(month1==2) {
					if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
						day="28";
					}else {
						day="29";
					}
				}else {
					day="31";
				}
				
			}else {
				day=String.valueOf(date1);
			}
			
			month=String.valueOf(month1);
		
			year=String.valueOf(year1);
		}
		
		for(int i=0;i<30;i++) {
			Integer tomato=tomatoService.getStaticWeekAndMonth(day, month, year, userId);
			Integer task=tomatoService.getStaticWeekAndMonthTask(day, month, year, userId);
			if(tomato==null)
				tomato=0;
			if(task==null)
				task=0;
			int sum=tomato+task;
			monthList.add(sum);
			int date1=Integer.parseInt(day);
			int month1=Integer.parseInt(month);
			int year1=Integer.parseInt(year);
			date1--;
			if(date1==0) {
				month1--;
				if(month1==0) {
					month1=12;
					year1--;
				}
				if(month1==11||month1==9||month1==6||month1==4) {
					day="30";
				}else if(month1==2) {
					if(((year1%4)==0&&(year1%100)!=0)||(year1%400==0)) {   
						day="28";
					}else {
						day="29";
					}
				}else {
					day="31";
				}
				
			}
			else {
				day=String.valueOf(date1);
			}
			month=String.valueOf(month1);
			year=String.valueOf(year1);
			
		}
		month=monthTemp;
		year=yearTemp;
		int tomatoSum=0;
		int taskSum=0;
		int taskNotSum=0;
		
		for(int i=0;i<12;i++) {
			Integer tomato=tomatoService.getStaticYear(month, year, userId);
			Integer task=tomatoService.getStaticYearTask(month, year, userId);
			Integer taskNot=tomatoService.getStaticYearTaskNOT(month, year, userId);
			if(tomato==null)
				tomato=0;
			if(task==null)
				task=0;
			if(taskNot==null)
				taskNot=0;
			tomatoSum+=tomato;
			taskSum+=task;
			taskNotSum+=taskNot;
			int month1=Integer.parseInt(month);
			month1--;
			if(month1==0) {
				int year1=Integer.parseInt(year);
				year1--;
				year=String.valueOf(year1);
				month="12";
			}
			else {	
				month=String.valueOf(month1);
			}
			
			
		}
		int sum=tomatoSum+taskSum+taskNotSum;
		/*int tomatoRate=tomatoSum/sum;
		int taskRate=taskSum/sum;
		int taskNotRate=taskNotSum/sum;*/
		System.out.println(tomatoSum+","+taskSum+","+taskNotSum);
		JSONArray array=new JSONArray();
		for(Integer i:weekList) {
			JSONObject obj=new JSONObject();
			if(i==null) {
				obj.put("w", 0);
			}else {
			obj.put("w", i);
			}
			array.put(obj);
		}
		for(Integer i:monthList) {
			JSONObject obj=new JSONObject();
			if(i==null) {
				obj.put("m", 0);
			}else {
			obj.put("m", i);
			}
			array.put(obj);
		}
		
		JSONObject obj=new JSONObject();
		obj.put("tomatoSum",tomatoSum);
		obj.put("taskSum", taskSum);
		obj.put("taskNotSum", taskNotSum);
		array.put(obj);
		
		writer.append(array.toString());
	}
	

	@RequestMapping("score")
	public void score(HttpServletRequest request,
			HttpServletResponse response) throws IOException{
		int userId = Integer.parseInt(request.getParameter("uid").trim());
		List<Tomato> tomatos = tomatoService.selectAllTomatoByUserId(userId);
		List<ScoreDetails> sds = new ArrayList<>();
		for(Tomato t:tomatos){
			ScoreDetails sd = new ScoreDetails();
			if(t.getTomatoCount()==1){
				sd.setName("完成番茄时间25分钟");
				sd.setCount(5);
			}else if(t.getTomatoCount()==2){
				sd.setName("完成番茄时间45分钟");
				sd.setCount(10);
			}else if(t.getTomatoCount()==3){
				sd.setName("完成番茄时间60分钟");
				sd.setCount(15);
			}else if(t.getTomatoCount()==4){
				sd.setName("完成番茄时间90分钟");
				sd.setCount(20);
			}else{
				sd.setName("完成番茄时间120分钟");
				sd.setCount(25);
			}
			
			sds.add(sd);
		}
		
		//获取该用户的task并加入到sds中
		
		Gson gson = new Gson();
		String str = gson.toJson(sds);
		response.getWriter().println(str);
	}

	
	
}
