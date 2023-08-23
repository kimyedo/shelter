package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.MemberService;
import com.example.demo.service.ShelterService;


@Controller 
public class HomeController {
	
	
	@Autowired
	MemberService mSer;
	
//	@Autowired
//	ShelterService sSer;
	
	
	
  	@GetMapping("/")   
	public String home(Model model) {
		//model객체 데이터 저장용,request영역에 저장 
//		model.addAttribute("msg", "jsp이식 성공"); 
		return "home"; //home.jsp로 포워딩
	}
  	
  	@GetMapping("/shop")
  	public String shop(Model model) {
  		return "shop";
  	}

	
  	
  	
}
