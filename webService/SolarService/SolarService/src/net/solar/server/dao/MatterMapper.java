package net.solar.server.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import net.solar.server.entity.Matter;

public interface MatterMapper {
	public void insertMatter(@Param("date")String date,@Param("matter")String matter,@Param("userId")int id);
	public List<Matter> selectMatter(int id);
}
