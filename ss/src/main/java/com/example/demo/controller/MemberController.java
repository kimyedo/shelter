package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.AuctionDto;
import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ProfileDto;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProfileService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
		
		@Autowired
		private MemberService mSer;
		
		@Autowired
		private ProfileService pSer;

		@PostMapping("/login")
		//public String login(@RequestParam String m_id, 
		//					@RequestParam("m_pwd") String pw) {
		public String login(@RequestParam HashMap<String,String> member, RedirectAttributes rttr ,Model model, HttpSession session) {
			log.info("로그인 처리");
			log.info("id:{}, pw:{}", member.get("id"), member.get("pw"));
			MemberDto mb=mSer.login(member);
			log.info("mb={}",mb); //아이디, 이름, 포인트,등급 확인
			if(mb!=null) {
				//session.setAttribute("id", member.get("m_id")); //권장 	
				List<ProfileDto> pDto = pSer.getProfileSelect(mb.getId());
				log.info("pDto : {}",pDto);
				
			    String imageUrl = ServletUriComponentsBuilder
			            .fromCurrentContextPath()
			            .path("/upload")
			            .toUriString();

			    for (ProfileDto prof : pDto) {
			    	if(prof.getSysFileName() != null) {
			    		prof.setSysFileName(imageUrl + "/" + prof.getSysFileName());  // SysFileName 업데이트
			    	}
			        log.info(prof.getSysFileName());
			    }
				
				session.setAttribute("pDto",pDto);
				session.setAttribute("mb", mb);
				return "redirect:/member/detail";
				//return "boardList";  //boardList.jsp
			}else {
				//model.addAttribute("msg", "로그인 실패"); //request영역에 저장, F5시 반복출력됨
				//return "login";
				rttr.addFlashAttribute("msg", "로그인 실패");  //리다이렉트시 request영역에 저장후 1번사용후 삭제됨
				//rttr.addAttribute("msg", "로그인 실패");  //리다이렉트시 request객체에 저장 ${param.msg}로 출력함
				return "redirect:/member/login";
			}
		}
		@GetMapping("/join")
		public String join() {
			log.info("회원가입 화면(폼)");
			return "join"; //join.jsp
		}
		//String, Integer, int, double 등 단순 타입이면 @RequestParam생략 가능
		//MemberDto 등 단순 타입이 아니면 @ModelAttribute
		//예외) (@RequestParam HashMap<String,String> member) 명시할것.
		@PostMapping("/join")
		public String join(/*@ModelAttribute("mb")*/MemberDto mDto,Model model,RedirectAttributes rttr) {
							/*model.addAttribute("mb", mDto); 와 동일*/
			
			log.info("회원가입 처리");
//			boolean result = mSer.join(id, pw,tel);
			log.info("mDto : {}", mDto);
			
			boolean result;
			
			try {
				result = mSer.join(mDto);
				
				if(result) {
//					model.addAttribute("msg","회원가입 성공");
					rttr.addFlashAttribute("msg","회원가입 성공");
//					return "redirect:/";
					return "login";
					//return "redirect:/"; //redirect:/url
				} else {
					model.addAttribute("msg","회원가입 실패");
					log.info("회원가입 실패");
					return "join";
				}
			} catch (Exception e) {
				model.addAttribute("msg","회원가입 실패");
//				rttr.addAttribute("msg","회원가입 실패");
				log.info("회원가입 예외 처리");
				return "join";
			}
			//return view;
		}
	
	@GetMapping("detail")
	public String detailForm(HttpSession session) {
		MemberDto mDto = (MemberDto) session.getAttribute("mb");

		if(mDto != null) {
			
			List<ProfileDto> pDto = pSer.getProfileSelect(mDto.getId());
			log.info("pDto : {}",pDto);
			
		    String imageUrl = ServletUriComponentsBuilder
		            .fromCurrentContextPath()
		            .path("/upload")
		            .toUriString();

		    for (ProfileDto prof : pDto) {
		    	if(prof.getSysFileName() != null) {
		    		prof.setSysFileName(imageUrl + "/" + prof.getSysFileName());  // SysFileName 업데이트
		    	}
		        log.info(prof.getSysFileName());
		    }
			
			session.setAttribute("pDto",pDto);
			return "MemberDetail";
		} else {
			return "redirect:/login";
		}
	}
	
	@GetMapping("update")
	public String UpdateForm(HttpSession session) {
		MemberDto mDto = (MemberDto) session.getAttribute("mb");
		
		if(mDto != null) {
//			session.setAttribute("mb", mDto);
			return "MemberUpdate";
		} else {
			return "redirect:/member/detail";
		}
	}
	
	@PostMapping("update")
	public String Update(@RequestParam HashMap<String,String> member,MemberDto mDto,HttpSession session,RedirectAttributes rttr) {
		log.info("mDto : {}",mDto);
		
		boolean result;
		
		try {
			result = mSer.memberUpdate(mDto);
			
			if(result) {
//				model.addAttribute("msg","회원가입 성공");
				rttr.addFlashAttribute("msg","회원정보 수정 성공");
				mDto = mSer.login(member);
				session.setAttribute("mb", mDto);
//				return "redirect:/";
				return "redirect:/member/detail";
				//return "redirect:/"; //redirect:/url
			} else {
				rttr.addFlashAttribute("msg","회원정보 수정 실패");
				log.info("회원정보 수정 예외 처리");
				return "MemberUpdate";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("msg","회원정보 수정 실패");
			log.info("회원정보 수정 예외 처리");
			return "MemberUpdate";
		}
	}
	
	
}
