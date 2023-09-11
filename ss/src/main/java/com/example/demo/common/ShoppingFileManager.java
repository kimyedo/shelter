package com.example.demo.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.InputStreamResource;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dao.ShoppingDao;
import com.example.demo.dto.ShoppingDto;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShoppingFileManager {
	
	@Autowired
	private ShoppingDao shDao;

	//파일 업로드, DB 저장
	public boolean ShoppingfileUpload(MultipartFile attachments, HttpSession session, String item) {
		log.info("Shopping File Manager class");
		//프로젝트의 upload 경로 찾기
	    String rootPath = session.getServletContext().getRealPath("/");
	    String uploadDirectory = rootPath + "upload/";

		//2. 폴더 생성을 꼭 할 것...
		File dir = new File(uploadDirectory);
		if(dir.isDirectory() == false) { //upload 폴더 없다면
			dir.mkdir(); //uploade 폴더 생성 mkdirs로 사용 시 다단 폴더까지 생성해줌.
		}
		//파일의 정보를 BoardFile or HashMap에 저장
		Map<String, String> fMap = new HashMap<String, String>();
		fMap.put("item", item);  
		boolean result = false;
		
		// 파일 메모리에 저장
		String oriFileName = attachments.getOriginalFilename(); // a.txt
		if(oriFileName.equals("")){
			return false;
		}
		log.info("원조 파일 : {}",oriFileName);
		fMap.put("oriFileName", oriFileName);
		// 4.시스템파일이름 생성 a.txt ==>112323242424.txt
		String sysFileName = System.currentTimeMillis() + "."
				+ oriFileName.substring(oriFileName.lastIndexOf(".") + 1);
		log.info("서버 파일 : {}",sysFileName);
		fMap.put("sysFileName", sysFileName);
		// 5.메모리->실제 파일 업로드
		
		try {
		    // 상대 경로로 파일 저장
	        Path filePath = Paths.get(uploadDirectory, sysFileName);
	        Files.copy(attachments.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

	        // 파일 경로 저장
	        fMap.put("filePath", filePath.toString());

		    // 파일 정보 DB에 저장
		    result = shDao.fileInsertMap(fMap);
		    if (!result) {
		        System.out.println("File insertion failed.");
		    }
		} catch (IOException e) {
		    System.out.println(e.getMessage());
		    System.out.println("파일업로드 예외 발생");
		    e.printStackTrace();
		    result = false;
		}
		return result;
	} // For end
		
	//파일 다운로드
	@ResponseBody
	public ResponseEntity<Resource> ShoppingfileDownload(ShoppingDto shFile, HttpSession session)
			throws FileNotFoundException, UnsupportedEncodingException {
		log.info("fileDownload()");
		// 파일 저장 경로
		String realpath = session.getServletContext().getRealPath("/");
		realpath += "upload/" + shFile.getSysFileName();
		// 실제 파일이 저장된 하드디스크까지 경로를 수립.
		InputStreamResource fResource = new InputStreamResource(new FileInputStream(realpath));

		// 파일명이 한글인 경우의 처리(UTF-8로 인코딩)
		String fileName = URLEncoder.encode(shFile.getOriFileName(), "UTF-8");
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
				// .contentType(MediaType.IMAGE_JPEG)
				.cacheControl(CacheControl.noCache()).body(fResource);
	}  //fileDownload end
	
	//파일 삭제
	public void fileDelete(String[] sysfiles, HttpSession session) {
		String realPath = session.getServletContext().getRealPath("/");
		realPath += "upload/";
		log.info("delete realPath: {}",realPath);
		for (String sysname : sysfiles) {  
			File file = new File(realPath + sysname);
			log.info("File: {}",file.getAbsoluteFile());
			if (file.exists()) {
				file.delete();
				log.info("서버 파일 삭제 완료");
			} else {
				log.info("이미 삭제된 파일");
			}
		}//for end
	}

	public ResponseEntity<Resource> shoppingfileDownload(ShoppingDto shDto, HttpSession session) {
		// TODO Auto-generated method stub
		return null;
	}
}
