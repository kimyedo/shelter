package com.example.demo.controller;

import com.example.demo.dto.ShelterDto;
import com.example.demo.service.ShelterService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Slf4j
@Controller
@RequestMapping("/shelter")
public class ShelterController<ShelterFile> {

    @Autowired
    ShelterService sSer;
    
    int listcnt=10;
    
    
    
    @GetMapping("/write")
    public String shelterWrite() {
    	log.info("셀터라이트");
        return "shelterwrite";// JSP 파일명
    }

    @PostMapping("/writepro")
    public String shelterWritePro(ShelterDto sDto,Model model,MultipartFile file)
    throws Exception{
    	log.info("sDto : {}",sDto);
    	sSer.write(sDto,file);
    	model.addAttribute("message","글작성이 완료되었습니다");
    	model.addAttribute("searchUrl","/shelter/list");
    	return "message";
    }
    
    @GetMapping("/list")
    public String shelterList(Model model,HttpSession session) {
    		List<ShelterDto> sList= sSer.findShelterList();
    		model.addAttribute("sList",sList);
    		log.info("shelter list");
    		return "shelterList";
    }

    
    
    
    
//    @PostMapping("/write")
//    public String writeShelter(
//	ShelterDto sDto, 
//    	@RequestPart List<MultipartFile> attachments 
//    	,HttpSession session, 
//	RedirectAttributes rttr) {
//    	log.info("글쓰기 처리");
//		log.info("shelter={}", sDto);
//		log.info("attachments:{}개",attachments.size());
//		boolean result = sSer.shelterWrite(sDto,attachments,session);
//		if (result) {
//			rttr.addFlashAttribute("msg", "글쓰기 성공");
//			return "redirect:/shelterList";   //0--->10
//		}else { //forward는 get--->get, post--->post만 가능
//			rttr.addFlashAttribute("msg", "글쓰기 실패");
//			return "redirect:/shelterwrite";
//		}
//	}
    
//	@GetMapping("/detail")
//	public String shelterDetail(Integer snum, Model model ) {
//	model.addAttribute("shelterDto",sSer.shelterDetail(snum));
//	return "shelterDetail";
//}
	
	
}