package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.AuctionBuyDto;
import com.example.demo.dto.AuctionDto;

@Mapper
public interface AuctionDao {

	@Select("select acnum,id,ac_animal,ac_age,minprice,toprice,starttime,endtime,OriFileName as OriFileName,SysFileName as SysFileName,filePath,endtime2 from auction")
	public List<AuctionDto> findAuctionList();
	


	@Select("SELECT * FROM AUCTIONBUY ")
	public AuctionBuyDto getAuctionDetail(Integer acnum);



public boolean auctionWrite(AuctionDto aDto);


@Update("update auction set OriFileName = #{oriFileName}, SysFileName = #{sysFileName}, filePath = #{filePath} where acnum = #{acnum}")
public boolean fileInsertMap(Map<String, Object> fMap);


}
