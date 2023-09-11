package com.example.demo.dto;

import java.sql.Date;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShoppingBuyDto {
	private int buynum;
	private String id;
	private String item;
	private int price;
	private int ea;
	private Date buydate;
}
