package com.example.demo.service;

import com.example.demo.controller.ReplyDto;
import com.example.demo.dao.ShelterDao;
import com.example.demo.dto.ShelterDto;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Slf4j
@MultipartConfig
@Service
public class ShelterService {

	@Autowired
     ShelterDao shelterDao;



//    @Autowired
//    public ShelterService(ShelterDao shelterDao) {
//        this.shelterDao = shelterDao;
//    }

    public List<ShelterDto> getAllShelters() {
        return shelterDao.getAllShelters();
    }


//    public String saveImage(MultipartFile imageFile) throws IOException {
//        String imageName = UUID.randomUUID().toString() + "_" + imageFile.getOriginalFilename();
//        File saveFile = new File(uploadDir, imageName);
//        imageFile.transferTo(saveFile);
//        return saveFile.getAbsolutePath();
//    }

	public List<ShelterDto> findShelterList() {
		List<ShelterDto> sList=shelterDao.findShelterList();
		return sList;
	}

	

//	public ShelterDto getShelterDetail(Integer snum) {
//		return shelterDao.getShelterDetail(snum);
//	}
//
//	public List<ReplyDto> getReplyList(Integer snum) {
//		return shelterDao.getReplyList(snum);
//	}
//	public List<ReplyDto> replyInsert(ReplyDto reply) {
//		List<ReplyDto> rList=null;
//		if(shelterDao.replyInsert(reply)) {
//			rList=shelterDao.getReplyList(reply.getsrnum());
//		}
//		return rList;
//	}
	
	//글작성처리
	public boolean write(ShelterDto sDto,MultipartFile file)throws Exception {
		boolean result=shelterDao.writePro(sDto);
		if(result) {
			
		//저장할 경로 지정
		String projectPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\files";
	
	//식별자 . 랜덤으로 이름 만들어줌
	UUID uuid=UUID.randomUUID();
	//랜덤식별자_원래파일이름 = 저장될 파일 이름 지정
	String fileName =uuid + "_" + file.getOriginalFilename();
	
	//빈 껍데기 생성
	//File을 생성할건데, 이름은 "name"으로할거고,projectpath라는 경로에 담긴다는 뜻
	File saveFile= new File(projectPath,fileName);

	file.transferTo(saveFile);
	//DB에 파일 넣기
	sDto.setFilename(fileName);
	//저장되는 경로
	sDto.setFilepath("/files/" + fileName); // 저장된 파일의이름,저장된파일의 경로
	//파일 저장
	shelterDao.save(sDto);
	return true;
		} else {
			return false;
		}
	}




    // 나머지 비즈니스 로직 구현
}
