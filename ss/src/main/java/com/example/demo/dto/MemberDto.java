package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MemberDto {
	private String id;
	private String pw;
	private String name;
	private String birth;
	private String addr;
	private String email;
	private String phone;
	private String gender;
	private String type;
}