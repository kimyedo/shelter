package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.common.FileManager;
import com.example.demo.dao.AuctionDao;
import com.example.demo.dao.MemberDao;
import com.example.demo.dto.AuctionBuyDto;
import com.example.demo.dto.AuctionDto;

import jakarta.servlet.http.HttpSession;

@Service
public class AuctionService {
	@Autowired
	MemberDao mDao;
	
	@Autowired
	AuctionDao aDao;
	
	@Autowired
	FileManager fm;
	

	public List<AuctionDto> findAuctionList(AuctionDto aDto) {
		List<AuctionDto> AcList=aDao.findAuctionList();
		return AcList;
	}


	public AuctionBuyDto getAuctionDetail(Integer acnum) {
		return aDao.getAuctionDetail(acnum);
		
	}


	public boolean auctionWrite(AuctionDto aDto, MultipartFile attachments, HttpSession session) {
		boolean result = aDao.auctionWrite(aDto);
		
		if(result) {
			if(fm.acfileUpload(attachments,session,aDto.getAcnum())) {
				return true;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}



	
	
//	public boolean bidAuction(AuctionBuyDto abDto) {
//		return abDto;
//	}
	
	
	
	
	
	
	
	
}
