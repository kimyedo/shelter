package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.FileManager;
import com.example.demo.dao.ProfileDao;
import com.example.demo.dto.ProfileDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao pDao;

	@Autowired
	private FileManager fm;
	
	public List<ProfileDto> getProfileSelect(String id) {
		return pDao.getProfileSelect(id);
	}

	public boolean profileDelete(ProfileDto pDto) {
		return pDao.profileDelete(pDto);
	}

	public boolean setProfileInsert(ProfileDto pDto, MultipartFile attachments, HttpSession session) {
		boolean result = pDao.setProfileInsert(pDto);
		if(result) {
			if(fm.proffileUpload(attachments,session,pDto.getAnimal())) {
				return true;
			} else {
				log.info("파일첨부 실패");
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean setProfileUpdate(ProfileDto pDto, MultipartFile attachments, HttpSession session) {
		boolean result = pDao.setProfileUpdate(pDto);
		if(result) {
			if(fm.proffileUpload(attachments,session,pDto.getAnimal())) {
				return true;
			} else {
				log.info("파일첨부 실패");
				return true;
			}
		} else {
			return false;
		}
	}

	public boolean pNameEquals(String pName, String id) {
		return pDao.pNameEqauls(pName,id);
	}
}
