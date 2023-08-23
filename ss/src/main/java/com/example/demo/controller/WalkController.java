package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class WalkController {
	@GetMapping("/walk")   
	public String home(Model model) {
		//model객체 데이터 저장용,request영역에 저장 
		//model.addAttribute("msg", "jsp이식 성공"); 
		return "walk"; //home.jsp로 포워딩
	}
}
