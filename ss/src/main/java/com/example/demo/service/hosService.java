package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.FileManager;
import com.example.demo.dao.hosDao;
import com.example.demo.dto.hosCheckDto;
import com.example.demo.dto.hosDto;
import com.icia.board.dto.BoardFile;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class hosService {

    @Autowired
    private hosDao hDao;
    
    @Autowired
    private FileManager fm;

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

	public boolean h_join(MultipartFile attachments, HttpSession session, hosDto hDto) {
		boolean result = hDao.h_join(hDto);
		if(result) {
			if(fm.hosfileUpload(attachments,session,hDto.getCode())) {
				log.info("file Upload ok !");
				return true;
			} else {
				log.info("file Upload fail . . .");
				return false;
			}
		}
		return false;
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
	
	public ResponseEntity<Resource> fileDownload(hosDto hDto, HttpSession session) 
			throws FileNotFoundException, UnsupportedEncodingException {
		return fm.hosfileDownload(hDto, session);
	}
}