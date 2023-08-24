package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.hosDao;
import com.example.demo.dto.hosCheckDto;
import com.example.demo.dto.hosDto;

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
}