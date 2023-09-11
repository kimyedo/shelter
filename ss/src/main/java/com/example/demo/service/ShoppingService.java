package com.example.demo.service;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.common.ShoppingFileManager;
import com.example.demo.dao.ShoppingDao;
import com.example.demo.dto.AuctionDto;
import com.example.demo.dto.ShoppingBuyDto;
import com.example.demo.dto.ShoppingCartDto;
import com.example.demo.dto.ShoppingDto;
import com.example.demo.exception.DBException;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShoppingService {

    @Autowired
    private ShoppingDao shDao;
    
    @Autowired
    private ShoppingFileManager shfm;
    
   
    public boolean reserve_Shopping(ShoppingDto shDto) {
    	log.info("reserve_Buy!!!");
    	
        if(shDao.shopping(shDto)) {
        	log.info("reserve_hos_select!!! false!!!");
            return false;
        } else {
        	log.info("reserve_hos_check!!! complete!!!");
            return shDao.shopping(shDto);
        }
    }

	public boolean sh_join(MultipartFile attachments, HttpSession session, ShoppingDto shDto) {
		boolean result = shDao.sh_join(shDto);
		if(result) {
			if(shfm.ShoppingfileUpload(attachments,session,shDto.getItem())) {
				log.info("file Upload ok !");
				return true;
			} else {
				log.info("file Upload fail . . .");
				return false;
			}
		}
		return false;
	}

	public int sh_itemcheck(String item) {
		return shDao.sh_itemcheck(item);
	}

	public boolean sh_u_profile(ShoppingDto shDto) {
		return shDao.sh_u_profile(shDto);
	}

	public ShoppingDto shoppingDataSelect(ShoppingDto shDto) {
		return shDao.shoppingDataSelect(shDto);
	}
	
	public ResponseEntity<Resource> fileDownload(ShoppingDto shDto, HttpSession session) 
			throws FileNotFoundException, UnsupportedEncodingException {
		return shfm.shoppingfileDownload(shDto, session);
	}

	public boolean shoppingWrite(ShoppingDto shDto, MultipartFile attachments, HttpSession session) {
		
		boolean result = shDao.writePro(shDto);
		
		if(result) {
			if(shfm.ShoppingfileUpload(attachments, session, shDto.getItem())) {
				return true;
			} else {
				log.info("게시글 작성만 성공");
				return true;
			}
		} else {
			log.info("게시글 작성 실패");
			return false;
		}
		
	}

	public ShoppingDto ShoppingDetail(String item) {
		return shDao.getShoppingDetail(item);
	}

	public List<ShoppingDto> findShoppingList(ShoppingDto shDto) {
		List<ShoppingDto> shList=shDao.findShoppingList();
		return shList;
	
	}

	public boolean CartDelete(ShoppingCartDto scDto) {
		return shDao.CartDelete(scDto);
	}

	public boolean ShoppingDelete(String item) {
		return shDao.ShoppingDelete(item);
	}

	public ShoppingDto findShoppingBuyList(ShoppingDto shDto) {
		return shDao.findShoppingBuyList(shDto);
	}

	

	public boolean detailbuyProduct(ShoppingBuyDto sDto) {
		ShoppingBuyDto shBDto = shDao.SelectBuyNum(sDto.getId());
		
		if(shBDto != null) {
			int count = shBDto.getBuynum()+1;
			if(shDao.UpdateBuyNum(sDto.getId(),count)) {
				sDto.setBuynum(count);
				
				boolean result=shDao.detailbuyProduct(sDto);
				
				if(result) {
					log.info("buyEa : {}",sDto.getEa());
					if(shDao.updateBuyProduct(sDto.getItem(),sDto.getEa())) {
						boolean delete = shDao.CartDelete2(sDto);
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		} else {
			int count = 1;
			if(shDao.InsertBuyNum(sDto.getId(),count)) {
				sDto.setBuynum(count);
				
				boolean result=shDao.detailbuyProduct(sDto);
				
				if(result) {
					if(shDao.updateBuyProduct(sDto.getItem(),count)) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public List<ShoppingBuyDto> getBuyList(String id) {
		return shDao.getBuyList(id);
	}

	public ShoppingDto getShoppingLists(String item) {
		return shDao.getShoppingLists(item);
	}

	public boolean updateShopping(ShoppingDto shDto, MultipartFile attachments, HttpSession session) {
		boolean result = shDao.updateShopping(shDto);
		
		if(result) {
			if(shfm.ShoppingfileUpload(attachments, session, shDto.getItem())) {
				return true;
			} else {
				log.info("게시글 작성만 성공");
				return true;
			}
		} else {
			log.info("게시글 작성 실패");
			return false;
		}
	}

}