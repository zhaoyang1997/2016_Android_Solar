package net.solar.server.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

import net.solar.server.entity.Ring;
import net.solar.server.serviceImpl.RingServiceImpl;

@Controller
@RequestMapping("ring")
public class RingController {
	
	@Autowired
	private RingServiceImpl ringService;
	FileOutputStream out;
	
	/**
	 * 返回客户端铃声列表信息
	 */
	@RequestMapping("ringList")
	public void getRings(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter writer = response.getWriter();
		List<Ring> rings = ringService.findRings();
		for(Ring r:rings) {
			String name = r.getRingName();
			String str = r.getRingFile();
			r.setRingName(name);
			r.setRingFile(str);
			System.out.println(r);
//			rings.add(r);
			Gson gson = new Gson();
			String jRing = gson.toJson(r);
			writer.write(jRing);
			
		}
	}

}
