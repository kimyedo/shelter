package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.MemberDto;

@Mapper
public interface MemberDao {

	

	@Update("UPDATE MEMBER SET ID = #{id},PW = #{pw},NAME = #{name}, BIRTH = #{birth}, ADDR = #{addr}, EMAIL = #{email}, PHONE = #{phone} WHERE ID = #{id}")
	boolean memberUpdate(MemberDto mDto);
	
	@Select("SELECT PW FROM MEMBER WHERE ID = #{id}")
	String getSecurityPw(String string);
	
	@Select("SELECT * FROM MEMBER WHERE ID = #{id}")
	MemberDto getMemberInfo(String string);

	@Insert("INSERT INTO MEMBER(id,pw,name,birth,addr,email,phone,gender,type) VALUES(#{id},#{pw},#{name},#{birth},#{addr},#{email},#{phone},#{gender},#{type})")
	boolean join(MemberDto mDto);
	
	@Select("select count(*) from member where id=#{id}")
	boolean findPwidCheck(String id);

	@Update("update member set pw=#{pw} where id=#{id}")
	boolean pwChange(MemberDto mDto);

	@Select("SELECT * FROM MEMBER WHERE ID=#{id}")
	List<MemberDto> findMemberList();
	
}
