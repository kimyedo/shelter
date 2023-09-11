package com.example.demo.controller;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.dto.MemberDto;
import com.example.demo.dto.ShoppingBuyDto;
import com.example.demo.dto.ShoppingCartDto;
import com.example.demo.dto.ShoppingDto;
import com.example.demo.service.ShoppingCartService;
import com.example.demo.service.ShoppingService;
import com.example.demo.exception.DBException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/shopping")
public class ShoppingController<ShoppingFile> {

    @Autowired
    ShoppingService shSer;
    
    @Autowired
    ShoppingCartService cartService;

    @GetMapping("/list")
	public String findShoppingList(ShoppingDto shDto,Model model,HttpSession session) {
		List<ShoppingDto> shList= shSer.findShoppingList(shDto);
		
		log.info("shList : {}", shList);
		
	    String imageUrl = ServletUriComponentsBuilder
	            .fromCurrentContextPath()
	            .path("/upload")
	            .toUriString();

	    for (ShoppingDto shopping : shList) {
	    	if(shopping.getSysFileName() != null) {
	    		shopping.setSysFileName(imageUrl + "/" + shopping.getSysFileName());  // SysFileName 업데이트
	    		
	    	}
	        log.info(shopping.getSysFileName());
	        log.info("ea:{}",shopping.getEa());
	    }

	    model.addAttribute("shList", shList);
	    
	    log.info("shopping list");
	    return "shopping";
	}
    
    
    
    
    
    @GetMapping("/write")
    public String shoppingWrite() {
    	log.info("shopping라이트");
        return "shoppingwrite";// JSP 파일명
    }
    
    
    @PostMapping("/write")
    public String shoppingwrite(
    	ShoppingDto shDto, 
    	MultipartFile attachments ,
    	HttpSession session,RedirectAttributes rttr,
    	MultipartFile multi) {
    	log.info("글쓰기 처리");
		log.info("shopping={}", shDto);
		boolean result = shSer.shoppingWrite(shDto,attachments,session);
		if (result) {
			rttr.addFlashAttribute("msg", "글쓰기 성공");
			return "redirect:/shopping/list";   //0--->10
		}else { //forward는 get--->get, post--->post만 가능
			rttr.addFlashAttribute("msg", "글쓰기 실패");
			return "redirect:/shopping/write";
		}
	}
	@PostMapping("/delete")
	public String ShoppingDelete(String item, HttpSession session, RedirectAttributes rttr) {
		log.info("ShoppingDelete item:{}",item);
		if(shSer.ShoppingDelete(item)) {
			rttr.addFlashAttribute("msg", item+"삭제성공");  //request영역에 1번 사용후 삭제됨 
			//rttr.addAttribute("msg", "삭제성공");  //request객체에 파라미터 저장 
			return "redirect:/shopping/list?pageNum=1";
		} else {
			rttr.addFlashAttribute("msg", item+" 삭제실패");  //request영역에 1번 사용후 삭제됨 
			return "redirect:/shopping/detail?item="+item;
		}
	}
    
	@GetMapping("/detail")
	public String shoppingrDetail(String item, Model model ) {
		ShoppingDto shDto = shSer.ShoppingDetail(item);
		String imageUrl = ServletUriComponentsBuilder.fromCurrentContextPath().path("/upload/").path(shDto.getSysFileName()).toUriString();
		log.info("shopping : {}",shDto);
		model.addAttribute("imageUrl",imageUrl);
		model.addAttribute("shoppingDto", shDto);
		return "shoppingDetail";
}
	


	    @GetMapping("/cart")
	    public String viewCart(Model model,ShoppingCartDto scDto) {
	        // 세션에서 장바구니 내용을 가져옵니다.
	    	log.info("test:{}",scDto);
	        List<ShoppingDto> cartItems = cartService.getCartItems(scDto);
		    String imageUrl = ServletUriComponentsBuilder
		            .fromCurrentContextPath()
		            .path("/upload")
		            .toUriString();

		    for (ShoppingDto shopping : cartItems) {
		    	if(shopping.getSysFileName() != null) {
		    		shopping.setSysFileName(imageUrl + "/" + shopping.getSysFileName());  // SysFileName 업데이트
		    	}
		        log.info(shopping.getSysFileName());
		    }
	        
	        model.addAttribute("cartItems", cartItems);
	        return "shoppingCart";
	       // return "redirect:/shopping/cart"; // cart.jsp 페이지로 이동
	    }

	    
	    @PostMapping("/addcart")
	    public String addToCart(ShoppingCartDto scDto ,Model model,RedirectAttributes rttr) {
	        log.info("scDto : {}",scDto);
	        model.addAttribute("model",model);
	        if(cartService.addToCart(scDto)){
	        	log.info("cart add Ok!!");
	        	log.info("id : {} ",scDto.getId());
	        	return "redirect:/shopping/cart?id="+scDto.getId();
	        } else {
	        	rttr.addFlashAttribute("msg","상품 수량을 초과하였습니다.");
	        	return "redirect:/shopping/list";
	        }
	    }
	    
	    @PostMapping("delcart")
		public String cartDelete(ShoppingCartDto scDto) {
			boolean result = shSer.CartDelete(scDto);
			
			if(result) {
				log.info("성공");
				return "redirect:/shopping/list";
			} else {
				log.info("실패");
				return "redirect:/shopping/list";
			}
		}
	    
	    @GetMapping("/buy")
	    public String findShoppingBuyList(ShoppingDto shDto,Model model,HttpSession session) {
			ShoppingDto shbDto= shSer.findShoppingBuyList(shDto);
			log.info("shbDto : {}", shbDto);
			log.info("shDto : {}",shDto);
			
			if(shbDto.getSysFileName() != null) {
				String imageUrl = ServletUriComponentsBuilder
		            .fromCurrentContextPath()
		            .path("/upload/")
		            .path(shbDto.getSysFileName())
		            .toUriString();
				log.info(imageUrl);
		    	model.addAttribute("imageUrl",imageUrl);
			}
			
			model.addAttribute("ea",shDto.getEa());
		    model.addAttribute("shbDto", shbDto);
		    log.info("shoppingBuy list");
		    return "shoppingBuy";
		}
	    
	    @PostMapping("/buy") //디테일구매  , 수량 변화
	    public String buyProduct(ShoppingBuyDto shbDto,ShoppingDto shDto,Model model,HttpSession session) {
	    	log.info(" 쇼핑구매");
	    	log.info("shbDto : {}",shbDto);
	        if(shSer.detailbuyProduct(shbDto)) { 
		        return "redirect:/shopping/buy/detail";
	        } else {
	        	return "redirect:/shopping/buy";
	        }
	    }

	    @GetMapping("/buy/detail")
	    public String buyDetail(Model model,HttpSession session) {
	    	log.info("buyDetail");
	    	MemberDto mDto = (MemberDto) session.getAttribute("mb");
	    	String id = mDto.getId();
	    	List<ShoppingBuyDto> shbDto = shSer.getBuyList(id);
	    	
	    	model.addAttribute("shbDto",shbDto);
	    	return "shoppingBuyDetail";
	    }
	    
	    @GetMapping("/update")
	    public String updateShopping(Model model,String item) {
	    	log.info("item : {}", item);
	    	ShoppingDto shDto = shSer.getShoppingLists(item);
	    	
	    	model.addAttribute("shDto",shDto);
	    	return "shoppingUpdate";
	    }
	    
	    @PostMapping("/update")
	    public String updateShoppings(Model model, ShoppingDto shDto,MultipartFile attachments,HttpSession session) {
	    	log.info("shDto : {}", shDto);
	    	boolean result = shSer.updateShopping(shDto,attachments,session);
	    	
	    	if(result) {
	    		return "redirect:/shopping/list";
	    	} else {
	    		return "redirect:/shopping/update?item="+shDto.getItem();
	    	}
	    }
	}
	
		
