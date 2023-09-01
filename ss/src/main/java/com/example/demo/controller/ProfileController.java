package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.ProfileDto;
import com.example.demo.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService pSer;
	
	@GetMapping("/insert")
	public String profileInsert() {
		return "profileInsert";
	}
	
	@PostMapping("/insert")
	public String setProfileInsert(ProfileDto pDto,MultipartFile attachments,HttpSession session,RedirectAttributes rttr) {
    	log.info("pDto : {}",pDto);
    	log.info("multipartFile : {}",attachments);
    	
    	String pName = pDto.getP_name();
    	String id = pDto.getId();
    	boolean pNameEquals = pSer.pNameEquals(pName,id);
    	
    	if(pNameEquals) {
    		rttr.addFlashAttribute("msg","동일한 이름을 가진 동물이 존재합니다.");
    		return "redirect:/member/profile/insert";
    	} else {
			boolean result = pSer.setProfileInsert(pDto,attachments,session);
			
			if(result) {
				rttr.addFlashAttribute("msg","프로필 추가 성공 ! ! !");
				return "redirect:/member/detail";
			} else {
				rttr.addFlashAttribute("msg","프로필 추가 실패 ! ! !");
				return "redirect:/member/profile/insert";
			}
    	}
	}
	
	@GetMapping("/update")
	public String profileUpdate(ProfileDto pDto,Model model) {
		log.info("pDto : {}",pDto);
		model.addAttribute("profile",pDto);
		return "profileUpdate";
	}
	
	@PostMapping("/update")
	public String setProfileUpdate(ProfileDto pDto,MultipartFile attachments,HttpSession session,RedirectAttributes rttr) {
    	log.info("pDto : {}",pDto);
    	log.info("multipartFile : {}",attachments);
    	
    	String pName = pDto.getP_name();
    	String id = pDto.getId();
    	boolean pNameEquals = pSer.pNameEquals(pName,id);
		
    	if(pNameEquals) {
    		rttr.addFlashAttribute("msg","동일한 이름을 가진 동물이 존재합니다.");
    		return "redirect:/member/profile/update";
    	} else {
			boolean result = pSer.setProfileUpdate(pDto,attachments,session);
			
			if(result) {
				rttr.addFlashAttribute("msg","프로필 수정 성공 ! ! !");
				return "redirect:/member/detail";
			} else {
				rttr.addFlashAttribute("msg","프로필 수정 실패 ! ! !");
				return "redirect:/member/profile/update";
			}
    	}
	}
	
	@PostMapping("/delete")
	public String profileDelete(ProfileDto pDto,RedirectAttributes rttr,HttpSession session) {
		log.info("pDto : {}",pDto);
		boolean result = pSer.profileDelete(pDto);
		
		if(result) {
			List<ProfileDto> pList = pSer.getProfileSelect(pDto.getId());
			log.info("pDto : {}",pList);
			
		    String imageUrl = ServletUriComponentsBuilder
		            .fromCurrentContextPath()
		            .path("/upload")
		            .toUriString();

		    for (ProfileDto prof : pList) {
		    	if(prof.getSysFileName() != null) {
		    		prof.setSysFileName(imageUrl + "/" + prof.getSysFileName());  // SysFileName 업데이트
		    	}
		        log.info(prof.getSysFileName());
		    }
			
			session.setAttribute("pDto",pList);
			
			rttr.addFlashAttribute("msg","삭제성공");
			return "redirect:/member/detail";
		} else {
			rttr.addFlashAttribute("msg","삭제실패");
			return "redirect:/member/detail";
		}
	}
}
