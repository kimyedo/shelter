package com.example.demo.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.hosCheckDto;
import com.example.demo.dto.hosDto;
import com.example.demo.service.hosService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class hosController {

    @Autowired
    private hosService hSer;
    
    @GetMapping("/hos/login")
    public String h_login(hosDto hDto,HttpSession session) {
    	hosDto hList = hSer.hosDataSelect(hDto);
    	session.setAttribute("hos", hList);
    	return "hosProfile";
    }
    
    @GetMapping("/hos/join")
    public String hosjoin() {
    	return "hosJoin";
    }
    
    @PostMapping("/hos/join")
    public String h_join(hosDto hDto,RedirectAttributes rttr) {
    	log.info("hosDto : {}",hDto);
    	
    	if(hSer.h_join(hDto)) {
    		rttr.addFlashAttribute("msg","회원가입 성공");
    		return "redirect:/";
    	} else {
    		rttr.addAttribute("msg","회원가입 실패");
    		return "redirect:/hos/join";
    	}
    }
    
    @PostMapping("/hos/codecheck")
    public ResponseEntity<Map<String, Object>> h_codecheck(@RequestBody Map<String, String> requestData) {
        String code = requestData.get("code"); // 클라이언트에서 전송한 데이터 가져오기
        
        int result = hSer.h_codecheck(code); // 처리 로직 실행
        
        Map<String, Object> response = new HashMap<>();
        response.put("result", result > 0); // 처리 결과를 맵에 담기
        
        return ResponseEntity.ok(response); // 응답 반환
    }
    
    @GetMapping("/profile/hos")
    public String h_profile() {
    	return "hosProfile";
    }
    @PostMapping("/profile/hos/update")
    public String h_u_profile(hosDto hDto,RedirectAttributes rttr,HttpSession session) {
    	log.info("hosDto : {}",hDto);
    	
    	if(hSer.h_u_profile(hDto)) {
        	hosDto hList = hSer.hosDataSelect(hDto);
        	session.setAttribute("hos", hList);
        	log.info("hList : {}",hList);
    		rttr.addFlashAttribute("msg","회원수정 성공");
    		return "redirect:/profile/hos";
    	} else {
    		rttr.addAttribute("msg","회원수정 실패");
    		return "redirect:/profile/hos/update";
    	}
    }
    
    @GetMapping("/profile/hos/update")
    public String h_pro_update() {
    	return "hosUpdate";
    }
    
    @GetMapping("/reserves_hospital")
    public String reserves_hospital() {
    	return "hosReserve";
    }

    @PostMapping("/reserves_hospital/check")
    public String reserve_hos(hosCheckDto hDto,HttpSession session, Model model) {
        log.info("hDto : {}", hDto);

        try {
            String checkdateString = hDto.getCheckdate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime parsedDateTime = LocalDateTime.parse(checkdateString, formatter);

            // LocalDateTime을 java.sql.Timestamp로 변환
            Timestamp timestamp = Timestamp.valueOf(parsedDateTime);

            hDto.setCheckDateTime(timestamp);

            // 이제 timestamp를 활용하여 작업을 수행하는 로직을 추가

            boolean result = hSer.reserve_hos_check(hDto);

            if (result) {
                model.addAttribute("msg", "예약성공");
            } else {
                model.addAttribute("msg", "예약실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "예약실패");
        }

        return "redirect:/";
    }
}