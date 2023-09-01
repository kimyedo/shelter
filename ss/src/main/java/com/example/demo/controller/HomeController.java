package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller 
public class HomeController {
	
	
	@Autowired
	MemberService mSer;
	
  	@GetMapping("/")   
	public String home(Model model) {
		//model객체 데이터 저장용,request영역에 저장 
//		model.addAttribute("msg", "jsp이식 성공"); 
		return "home"; //home.jsp로 포워딩
	}

	@GetMapping("/detail")
	public String findAuctionList(Model model,HttpSession session) {
		log.info("image check");
		return "detail";
	}
  	
	
  	@GetMapping("/login")
	public String login() {
		log.info("로그인 화면(폼)");
  		return "login"; //login.jsp
	}
  	
  	@PostMapping("/idcheck")
  	@ResponseBody
  	public Integer idcheck(MemberDto mDto) {
  		log.info("id : {}", mDto.getId());
  		Integer result = mSer.idcheck(mDto.getId());
  		
  		return result;
  	}
  	
	@GetMapping("/logout")
	public String getLogout(HttpSession session, RedirectAttributes rttr) {
		log.info("get 로그아웃");
		session.invalidate();
		rttr.addFlashAttribute("msg", "버튼을 눌러 로그아웃 하세요");
		return "redirect:/"; //.jsp
	}
}
