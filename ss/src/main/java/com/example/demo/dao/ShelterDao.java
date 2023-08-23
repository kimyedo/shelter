package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.ShelterDto;

import jakarta.servlet.http.HttpSession;


@Mapper
public interface ShelterDao {
	@Select("SELECT * FROM SHELTER")
	List<ShelterDto> findShelterList();

	List<ShelterDto> getAllShelters();



	boolean shelterWrite(ShelterDto shelterList, List<MultipartFile> attachments, HttpSession session);

	ShelterDto getShelterDetail(Integer snum);

	

	 @Insert("INSERT INTO SHELTER VALUES(${snum},${id},${name},${s_title},${s_content},null,${s_date},${s_view})")
	boolean writePro(ShelterDto sDto);

	 @Insert("Insert INTO SHELTER_VIEW(image,fileName,filePath) VALUES(${fileName},${fileName},${filePath})")
		void save(ShelterDto sDto);	 



}
