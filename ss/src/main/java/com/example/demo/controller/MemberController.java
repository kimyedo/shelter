package com.example.demo.controller;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.MemberDto;
import com.example.demo.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/member")
public class MemberController {
		
		@Autowired
		private MemberService mSer;
		
		@GetMapping("/login")
		//public String login(@RequestParam String m_id, 
		//					@RequestParam("m_pwd") String pw) {
		public String login(@RequestParam HashMap<String,String> member, RedirectAttributes rttr ,Model model, HttpSession session) {
			log.info("로그인 처리");
			log.info("id:{}, pw:{}", member.get("id"), member.get("pw"));
			MemberDto mb=mSer.login(member);
			log.info("mb={}",mb); //아이디, 이름, 포인트,등급 확인
			if(mb!=null) {
				//session.setAttribute("id", member.get("m_id")); //권장 	
				session.setAttribute("mb", mb);
				return "MemberDetail";
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
		@GetMapping("/logout")
		public String getLogout(HttpSession session, RedirectAttributes rttr) {
			log.info("get 로그아웃");
			rttr.addFlashAttribute("msg", "버튼을 눌러 로그아웃 하세요");
			return "redirect:/board/list"; //.jsp
		}
		@PostMapping("/logout")
		public String postLogout(HttpSession session, RedirectAttributes rttr) {
			log.info("post 로그아웃");
			session.invalidate();
			rttr.addFlashAttribute("msg", "Post로그아웃 성공");
			return "redirect:/";
		}
	
	@GetMapping("detail")
	public String detailForm(HttpSession session) {
		MemberDto mDto = (MemberDto) session.getAttribute("mb");
		
		if(mDto != null) {
//			session.setAttribute("mb", mDto);
			return "MemberDetail";
		} else {
			return "redirect:/member/login";
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
	@GetMapping("/findPw1")
	public String findPwForm(HttpSession session) {
		return "findPw1";
	}
	
	@PostMapping("/findPw1")
	public String findPwidCheck(MemberDto mDto,HttpSession session,RedirectAttributes rttr) {
		boolean result;
		try {
			result = mSer.findPwidCheck(mDto.getId());
			
			if(result) {
//				model.addAttribute("msg","회원가입 성공");
				rttr.addFlashAttribute("msg","회원정보 수정 성공");
				//mDto = mSer.login(member);
				session.setAttribute("id", mDto.getId());
//				return "redirect:/";
				return "redirect:/member/findPw2";
				//return "redirect:/"; //redirect:/url
			} else {
				rttr.addFlashAttribute("msg","회원정보 수정 실패");
				log.info("회원정보 수정 예외 처리");
				return "findPw1";
			}
		} catch (Exception e) {
			rttr.addFlashAttribute("msg","회원정보 수정 실패");
			log.info("회원정보 수정 예외 처리");
			return "findPw1";
		}
	}
	
	
	
	
	
	@GetMapping("/findPw2")
	public String pwChange() {
		return "findPw2";
	}
	
	@PostMapping("/findPw2")
	public String pwChange(MemberDto mDto, HttpSession session) throws IOException{
		boolean result;
		
		try {
			result = mSer.pwChange(mDto);
			
			if(result) {
//				model.addAttribute("msg","회원가입 성공");
//				rttr.addFlashAttribute("msg","회원정보 수정 성공");
//				return "redirect:/";
				return "redirect:/member/login";
				//return "redirect:/"; //redirect:/url
			} else {
	//			rttr.addFlashAttribute("msg","회원정보 수정 실패");
				log.info("회원정보 수정 예외 처리");
				return "redirect:/";
			}
		} catch (Exception e) {
		//	rttr.addFlashAttribute("msg","회원정보 수정 실패");
			log.info("회원정보 수정 예외 처리");
			return "redirect:/";
		}
	}

	@GetMapping("/list")
	public String profile(HttpSession session) {
		MemberDto mDto = (MemberDto) session.getAttribute("mb");
		
		if(mDto != null) {
//			session.setAttribute("mb", mDto);
			return "profile";
		} else {
			return "redirect:/member/login";
		}
	}
	
}
