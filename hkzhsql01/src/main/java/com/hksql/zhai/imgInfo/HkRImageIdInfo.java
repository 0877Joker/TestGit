package com.hksql.zhai.imgInfo;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class HkRImageIdInfo {

    private String RImageId;

    private String RTitle;

    private String RContent;

    private Integer RTypeId;

    private String RTypeName;

    private Timestamp RReleaseTime;

    private Timestamp RCreateTime;

    private String RCreateUser;

    private String RUpdateUser;


    public String getRImageId() {
        return RImageId;
    }

    public void setRImageId(String RImageId) {
        this.RImageId = RImageId;
    }

    public String getRTitle() {
        return RTitle;
    }

    public void setRTitle(String RTitle) {
        this.RTitle = RTitle;
    }

    public String getRContent() {
        return RContent;
    }

    public void setRContent(String RContent) {
        this.RContent = RContent;
    }

    public Integer getRTypeId() {
        return RTypeId;
    }

    public void setRTypeId(Integer RTypeId) {
        this.RTypeId = RTypeId;
    }

    public String getRTypeName() {
        return RTypeName;
    }

    public void setRTypeName(String RTypeName) {
        this.RTypeName = RTypeName;
    }

    public Timestamp getRReleaseTime() {
        return RReleaseTime;
    }

    public void setRReleaseTime(Timestamp RReleaseTime) {
        this.RReleaseTime = RReleaseTime;
    }

    public Timestamp getRCreateTime() {
        return RCreateTime;
    }

    public void setRCreateTime(Timestamp RCreateTime) {
        this.RCreateTime = RCreateTime;
    }

    public String getRCreateUser() {
        return RCreateUser;
    }

    public void setRCreateUser(String RCreateUser) {
        this.RCreateUser = RCreateUser;
    }

    public String getRUpdateUser() {
        return RUpdateUser;
    }

    public void setRUpdateUser(String RUpdateUser) {
        this.RUpdateUser = RUpdateUser;
    }


}
