package com.example.demo.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.hosCheckDto;
import com.example.demo.service.hosService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class hosController {

    @Autowired
    private hosService hSer;
    
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