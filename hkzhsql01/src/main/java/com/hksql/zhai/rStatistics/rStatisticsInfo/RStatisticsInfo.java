package com.hksql.zhai.rStatistics.rStatisticsInfo;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class RStatisticsInfo {

    private Integer mydate;
    private Integer typeId;
    private String typeName;
    private Integer companyId;
    private String companyName;
    private Long exp;
    private Long click;
    private BigDecimal rateClick;
    private Timestamp createTime;
    private String createUser;
    private Timestamp updateTime;
    private String updateUser;

    public Integer getMydate() {
        return mydate;
    }

    public void setMydate(Integer mydate) {
        this.mydate = mydate;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getRateClick() {
        return rateClick;
    }

    public void setRateClick(BigDecimal rateClick) {
        this.rateClick = rateClick;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Long getClick() {
        return click;
    }

    public void setClick(Long click) {
        this.click = click;
    }
}
