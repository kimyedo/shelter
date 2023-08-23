package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.hosCheckDto;

@Mapper
public interface hosDao {
    
	public boolean reserve_hos_select(hosCheckDto hDto);

    @Insert("insert into a_hos_check values(#{checkDateTime},#{id},#{code},#{animal},#{h_pro}")
//	@Insert("insert into a_hos_check values('예약일','예약자','예약병원','예약동물','진료내용')")
	public boolean reserve_hos_check(hosCheckDto hDto);
    
}