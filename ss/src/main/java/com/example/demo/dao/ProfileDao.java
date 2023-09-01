package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.ProfileDto;

@Mapper
public interface ProfileDao {

	@Select("select * from profile where id = #{id}")
	public List<ProfileDto> getProfileSelect(String id);
	
	@Insert("insert into profile values(#{id},#{animal},#{p_name},#{p_gender},#{p_age},null,null,null)")
	public boolean setProfileInsert(ProfileDto pDto);

	@Update("update profile set OriFileName = #{oriFileName}, SysFileName = #{sysFileName}, filePath = #{filePath} where animal = #{animal}")
	public boolean proffileInsertMap(Map<String, String> fMap);
	
	@Update("update profile set p_name = #{p_name}, p_gender = #{p_gender}, p_age = #{p_age} where id = #{id} and animal = #{animal}")
	public boolean setProfileUpdate(ProfileDto pDto);
	
	@Delete("delete from profile where id = #{id} and animal = #{animal}")
	public boolean profileDelete(ProfileDto pDto);
	
	@Select("select count(*) from profile where id = #{id} and p_name = #{pName}")
	public boolean pNameEqauls(String pName, String id);

}
