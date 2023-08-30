package com.example.demo.dto;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class AuctionDto {

	private int acnum;
	private String id;
	private String ac_animal;
	private String ac_gender;
	private int ac_age;
	private int minprice;
	private int toprice;
	private Date starttime;
	private Date endtime;
	private String OriFileName;
	private String SysFileName;
	private String filepath;
	private String endtime2;
	private Timestamp endTimeSet;
	
    public Timestamp getEndTimeSet() {
        return endTimeSet;
    }

    public void setEndTimeSet(Timestamp endTimeSet) {
        this.endTimeSet = endTimeSet;
    }
}
