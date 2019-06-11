package net.solar.server.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Matter;

public interface MatterService {
	public void insertMatter(String date,String matter,int id);
	public List<Matter> selectMatter(int id);

}
