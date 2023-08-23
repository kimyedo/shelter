package com.example.demo.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class AuctionBuyDto {

	private String id;
	private int acnum;
	private int toprice;
	private Date curtime;
}
