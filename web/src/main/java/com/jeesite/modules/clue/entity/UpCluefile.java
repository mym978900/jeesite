package com.jeesite.modules.clue.entity;

import java.util.Date;

public class UpCluefile {
	private String upId;
	
    private String upUsercode;

    private String upUsername;

    private Date upDate;

    private String upFilename;

    private String upFilepath;

    private String upFiletype;

    private String upDeptcode;

    private String upType;

    private String extra1;

    private String extra2;

    private String extra3;

    private String extra4;

    private String extra5;

    private Integer upEffectivecount;

    private Integer upIneffectivecount;

    private String upIneffectiveinfo;
    
    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId == null ? null : upId.trim();
    }

    public String getUpUsercode() {
        return upUsercode;
    }

    public void setUpUsercode(String upUsercode) {
        this.upUsercode = upUsercode == null ? null : upUsercode.trim();
    }

    public String getUpUsername() {
        return upUsername;
    }

    public void setUpUsername(String upUsername) {
        this.upUsername = upUsername == null ? null : upUsername.trim();
    }

    public Date getUpDate() {
        return upDate;
    }

    public void setUpDate(Date upDate) {
        this.upDate = upDate;
    }

    public String getUpFilename() {
        return upFilename;
    }

    public void setUpFilename(String upFilename) {
        this.upFilename = upFilename == null ? null : upFilename.trim();
    }

    public String getUpFilepath() {
        return upFilepath;
    }

    public void setUpFilepath(String upFilepath) {
        this.upFilepath = upFilepath == null ? null : upFilepath.trim();
    }

    public String getUpFiletype() {
        return upFiletype;
    }

    public void setUpFiletype(String upFiletype) {
        this.upFiletype = upFiletype == null ? null : upFiletype.trim();
    }

    public String getUpDeptcode() {
        return upDeptcode;
    }

    public void setUpDeptcode(String upDeptcode) {
        this.upDeptcode = upDeptcode == null ? null : upDeptcode.trim();
    }

    public String getUpType() {
        return upType;
    }

    public void setUpType(String upType) {
        this.upType = upType == null ? null : upType.trim();
    }

    public String getExtra1() {
        return extra1;
    }

    public void setExtra1(String extra1) {
        this.extra1 = extra1 == null ? null : extra1.trim();
    }

    public String getExtra2() {
        return extra2;
    }

    public void setExtra2(String extra2) {
        this.extra2 = extra2 == null ? null : extra2.trim();
    }

    public String getExtra3() {
        return extra3;
    }

    public void setExtra3(String extra3) {
        this.extra3 = extra3 == null ? null : extra3.trim();
    }

    public String getExtra4() {
        return extra4;
    }

    public void setExtra4(String extra4) {
        this.extra4 = extra4 == null ? null : extra4.trim();
    }

    public String getExtra5() {
        return extra5;
    }

    public void setExtra5(String extra5) {
        this.extra5 = extra5 == null ? null : extra5.trim();
    }

    public Integer getUpEffectivecount() {
        return upEffectivecount;
    }

    public void setUpEffectivecount(Integer upEffectivecount) {
        this.upEffectivecount = upEffectivecount;
    }

    public Integer getUpIneffectivecount() {
        return upIneffectivecount;
    }

    public void setUpIneffectivecount(Integer upIneffectivecount) {
        this.upIneffectivecount = upIneffectivecount;
    }

    public String getUpIneffectiveinfo() {
        return upIneffectiveinfo;
    }

    public void setUpIneffectiveinfo(String upIneffectiveinfo) {
        this.upIneffectiveinfo = upIneffectiveinfo == null ? null : upIneffectiveinfo.trim();
    }
}