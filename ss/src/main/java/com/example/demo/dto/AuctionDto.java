package com.example.demo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class AuctionDto {

	private int acnum;
	private String id;
	private String animal;
	private String image;
	private int minprice;
	private int toprice;
	private Date starttime;
	private Date endtime;
	
}
