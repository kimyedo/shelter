package com.example.demo.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.example.demo.dao.MemberDao;
import com.example.demo.dto.MemberDto;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MemberService {
	@Autowired
	private MemberDao mDao;

	public boolean join(MemberDto mDto) {
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		mDto.setPw(pwEncoder.encode(mDto.getPw())); //암호화 ex)1111 => $2a$10$bpw60.wNvHUE2v.XfhtnueoNdq/hsfy0oDivxTspNqT83XoCgQxLy
		
		return mDao.join(mDto);// TODO Auto-generated method stub
	}
	
	
	
	public boolean memberUpdate(MemberDto mDto) {
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		mDto.setPw(pwEncoder.encode(mDto.getPw()));
		
		return mDao.memberUpdate(mDto);
	}

	public MemberDto login(HashMap<String, String> mDto) {		
		//복호화는 안되지만 암호화된 값과 비교는 가능
		BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
		String encoPwd = mDao.getSecurityPw(mDto.get("id"));
		//특정회원의 암호화된 비밀번호를 DB에서 구하기
		//'1111' -> $2a$10$bpw60.wNvHUE2v.XfhtnueoNdq/hsfy0oDivxTspNqT83XoCgQxLy
		
		if(encoPwd != null) { //아이디가 존재함
			//matches("입력된 비밀번호", "암호화된 비밀번호");
			log.info("아이디 확인 완료");
			if(pwEncoder.matches(mDto.get("pw"), encoPwd)) {
				//아이디도 존재하며, 비밀번호도 일치
				log.info("비밀번호 일치");
				return mDao.getMemberInfo(mDto.get("id"));
				//return true; //수정 ???
			} else {
				//비밀번호 불일치
				log.info("비밀번호 불일치");
				return null;
			}
		} else { //아이디가 부존재함
			log.info("아이디 오류");
			return null;
		}
	}
	
	
	public boolean findPwidCheck(String id) {
		return mDao.findPwidCheck(id);
			}
		
		public boolean pwChange(MemberDto mDto) {
			BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
			mDto.setPw(pwEncoder.encode(mDto.getPw()));
			return mDao.pwChange(mDto);
		}
	
	
	
		public List<MemberDto> findMemberList() {
			List<MemberDto> mList=mDao.findMemberList();
			return mList;
			}
	
	
}
