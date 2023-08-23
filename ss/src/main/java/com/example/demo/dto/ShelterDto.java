package com.example.demo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ShelterDto {

	private int snum;
	private String id;
	private String name;
	private String s_title;
	private String s_content;
	private String image;
	private Date s_date;
	private int s_views;
	private String filename;
	private String filepath;
	
}
