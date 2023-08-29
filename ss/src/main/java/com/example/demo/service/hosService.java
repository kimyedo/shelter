package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.demo.dao.hosDao;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.hosCheckDto;
import com.example.demo.dto.hosDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class hosService {

    @Autowired
    private hosDao hDao;

    public boolean reserve_hos_check(hosCheckDto hDto) {
    	log.info("reserve_hos_check!!!");
    	
        if(hDao.reserve_hos_select(hDto)) {
        	log.info("reserve_hos_select!!! false!!!");
            return false;
        } else {
        	log.info("reserve_hos_check!!! complete!!!");
            return hDao.reserve_hos_check(hDto);
        }
    }

	public boolean h_join(hosDto hDto) {
		return hDao.h_join(hDto);
	}

	public int h_codecheck(String code) {
		return hDao.h_codecheck(code);
	}

	public boolean h_u_profile(hosDto hDto) {
		return hDao.h_u_profile(hDto);
	}

	public hosDto hosDataSelect(hosDto hDto) {
		return hDao.hosDataSelect(hDto);
	}
	
	public List<hosDto> findHospitalList(HttpSession session, Model model) {
		log.info("findHospitalList");
		MemberDto mDto = (MemberDto)session.getAttribute("mb");
    	if(mDto != null) {
    		return hDao.findHospitalList(mDto);
    	}else {
    		mDto = new MemberDto();
    		mDto.setAddr("인천");
    		return hDao.findHospitalList(mDto);
    	}
    	
	}
}