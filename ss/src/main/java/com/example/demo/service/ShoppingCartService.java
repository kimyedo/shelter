package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ShoppingDao;
import com.example.demo.dto.ShoppingCartDto;
import com.example.demo.dto.ShoppingDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShoppingCartService {

	@Autowired
	private ShoppingDao shDao;
	
    // 장바구니에 상품 추가
    public boolean addToCart(ShoppingCartDto scDto) {
    	ShoppingDto sDto = shDao.SelectEa(scDto);
    	
    	ShoppingCartDto sCart = shDao.cartSelect(scDto);
    	if(sCart != null) {
    		int cartea = sCart.getEa(); //카트에 있던 수량
    		int cartea2 = scDto.getEa(); //담아온 수량
    		int ea = cartea + cartea2; //총 수량
    		
    		if(ea > sDto.getEa()) {
    			log.info("수량 초과");
    			return false;
    		} else {
    			log.info("수량 적절");
    			return shDao.addToCartUpdate(scDto);
    		}
    	} else {
    		log.info("장바구니 추가");
    		return shDao.addToCart(scDto);
    	}
    }

    // 장바구니 내용 가져오기
    public List<ShoppingDto> getCartItems(ShoppingCartDto scDto) {
        return shDao.getCartItems(scDto);
    }
}