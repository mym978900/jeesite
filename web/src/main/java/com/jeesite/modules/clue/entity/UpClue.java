package com.jeesite.modules.clue.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class UpClue {
    private String upClueCode;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date upClueTime;

    private String upUserCode;

    private String upClueName;

    private String upClueTel;

    private String upClueSex;

    private String upClueAddre;

    private String upClueStatus;

    private String upClueAppraise;

    private Integer upClueAge;

    private Double upClueLongitude;

    private Double upClueLatitude;

    private Date upClueMatchtime;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;
    
    private String upClueDepttype;
    
    private String upClueTaskid;
    
    private Date upAiexecutetime;

    private Date upAipersontime;
    
    private Date upAipersonendtime;

    private Date upAiendtime;

    private Integer upTalktime;
    
    private String upIseffective;
   
}