package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	AuctionService aSer;
	
	@Autowired
	MemberService mSer;
	
	
	@GetMapping("/list")
	public String findAuctionList(AuctionDto aDto,Model model,HttpSession session) {
		List<AuctionDto> AcList= aSer.findAuctionList(aDto);
		
		log.info("AcList : {}", AcList);
		
	    String imageUrl = ServletUriComponentsBuilder
	            .fromCurrentContextPath()
	            .path("/upload")
	            .toUriString();

	    for (AuctionDto auction : AcList) {
	    	if(auction.getSysFileName() != null) {
	    		auction.setSysFileName(imageUrl + "/" + auction.getSysFileName());  // SysFileName 업데이트
	    	}
	        log.info(auction.getSysFileName());
	    }

	    model.addAttribute("AcList", AcList);
	    log.info("auction list");
	    return "auctionList";
	}
	
	
	
	@GetMapping("/write")
	public String auctionWrite() {
		return "auctionWrite";
	}
	
	
	@PostMapping("/write")
	public String auctionWrite(AuctionDto aDto,MultipartFile attachments,HttpSession session,RedirectAttributes rttr) {
	    String checkdateString = aDto.getEndtime2();

	    if (checkdateString != null && !checkdateString.isEmpty()) {
	        try {
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            LocalDateTime parsedDateTime = LocalDateTime.parse(checkdateString, formatter);

	            // LocalDateTime을 java.sql.Timestamp로 변환
	            Timestamp timestamp = Timestamp.valueOf(parsedDateTime);

	            aDto.setEndTimeSet(timestamp);
	        } catch (DateTimeParseException ex) {
	            // 파싱 예외 발생 시 처리
	            log.error("Error parsing endtime2: {}", ex.getMessage());
	        }
	    }
        
	    log.info("aDto : {}",aDto);

		
	 		if(aSer.auctionWrite(aDto,attachments,session)) {
	 			rttr.addFlashAttribute("msg","성공");
	 			return "redirect:/";
	 		} else {
	 			rttr.addFlashAttribute("msg","실패");
	 			return "endtime2";
	 		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@GetMapping("/bid")
	public String bidAuctionFrm(Integer acnum,Model model) {
		log.info("입찰 창 열기");
		AuctionBuyDto acbDto=aSer.getAuctionDetail(acnum);
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
