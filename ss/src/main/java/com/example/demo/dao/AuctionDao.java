package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.AuctionBuyDto;
import com.example.demo.dto.AuctionDto;

@Mapper
public interface AuctionDao {

	@Select("SELECT * FROM AUCTION")
	public List<AuctionDto> findAuctionList();
	


@Select("SELECT * FROM AUCTIONBUY ")
	public AuctionBuyDto getAuctionDetail(Integer acnum);
}
