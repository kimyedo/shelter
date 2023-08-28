package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class hosDto {
	private String code;
	private String hostitle;
	private String hosphone;
	private String hosaddr;
	private int hoshour;
	private int hosmin;
	private int hoshour2;
	private int hosmin2;
	private int mincheck;
	private String hostype;
	private String OriFileName;
	private String SysFileName;
	private String filePath;
	
	//세션에 저장된 code를 hidden에 숨겨서 가져오는 방식으로 업데이트 하기 위해 추가
	private String BeforeCode;
}
