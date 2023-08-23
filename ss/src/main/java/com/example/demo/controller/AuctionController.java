package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.dto.AuctionBuyDto;
import com.example.demo.dto.AuctionDto;
import com.example.demo.service.AuctionService;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/auction")
public class AuctionController {

	@Autowired
	AuctionService AcSer;
	
	@Autowired
	MemberService mSer;
	
	
	@GetMapping("/list")
	public String findAuctionList(Model model,HttpSession session) {
		List<AuctionDto> AcList= AcSer.findAuctionList();
		model.addAttribute("AcList",AcList);
		log.info("auction list");
		return "auctionList";
	}
	
	@GetMapping("/bid")
	public String bidAuctionFrm(Integer acnum,Model model) {
		log.info("입찰 창 열기");
		AuctionBuyDto acbDto=AcSer.getAuctionDetail(acnum);
		if(acbDto!=null) {
			model.addAttribute("acbDto",acbDto);
			return "auctionBid";	
		}else {
		return "redirect:/auction/list";
		}
	}
	
//	@PostMapping("/bid")
//	public String bidAuction(AuctionBuyDto abDto,HttpSession session) {
//		log.info("입찰 처리");
//		boolean result=AcSer.bidAuction(abDto);
//		if(result) {
//			return true;
//		}else {
//			return false;
//		}
//	}//post end
	
}//end.
