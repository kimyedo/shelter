package com.example.demo.dto;

import lombok.Data;

@Data
public class ShoppingDto {
	private String item;
	private Integer price;
	private Integer ea;
	private String sh_content;
	private String OriFileName;
	private String SysFileName;
	private String filePath;
}
