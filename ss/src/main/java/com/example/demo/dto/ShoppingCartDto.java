package com.example.demo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ShoppingCartDto {
	private String id;
	private String item;
	private Integer ea;
}
