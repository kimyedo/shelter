package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.hosDao;
import com.example.demo.dto.hosCheckDto;

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
}