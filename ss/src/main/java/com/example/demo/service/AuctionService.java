package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AuctionDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dto.AuctionBuyDto;
import com.example.demo.dto.AuctionDto;

@Service
public class AuctionService {
	@Autowired
	MemberDao mDao;
	
	@Autowired
	AuctionDao acDao;
	

	public List<AuctionDto> findAuctionList() {
		List<AuctionDto> AcList=acDao.findAuctionList();
		return AcList;
	}


	public AuctionBuyDto getAuctionDetail(Integer acnum) {
		return acDao.getAuctionDetail(acnum);
		
	}

	
//	public boolean bidAuction(AuctionBuyDto abDto) {
//		return abDto;
//	}
	
	
}
