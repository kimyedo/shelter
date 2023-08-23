package com.example.demo.dto;

import java.sql.Timestamp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class hosCheckDto {
    private String code;
    private String id;
    private String checkdate;
    private Timestamp checkDateTime;
    private String h_pro;
    private String dogtype;
    private String animal;
    
    public Timestamp getCheckDateTime() {
        return checkDateTime;
    }

    public void setCheckDateTime(Timestamp checkDateTime) {
        this.checkDateTime = checkDateTime;
    }
}