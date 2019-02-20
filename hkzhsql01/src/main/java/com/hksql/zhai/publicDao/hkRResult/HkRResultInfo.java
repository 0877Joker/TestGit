package com.hksql.zhai.publicDao.hkRResult;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HkRResultInfo {
    private String tImageId;

    private Integer tLogDate;

    //曝光
    private BigDecimal tCPv;

    private BigDecimal tCUv;

    private BigDecimal tCPvClick;

    private BigDecimal tCuvClick;

    private BigDecimal tSPv;

    private BigDecimal tSUv;

    private Timestamp tCreateTime;

    private String tCreateUser;

    private Timestamp tUpdateTime;

    private String tUpdateUser;

    public String gettImageId() {
        return tImageId;
    }

    public void settImageId(String tImageId) {
        this.tImageId = tImageId;
    }

    public Integer gettLogDate() {
        return tLogDate;
    }

    public void settLogDate(Integer tLogDate) {
        this.tLogDate = tLogDate;
    }

    public BigDecimal gettCPv() {
        return tCPv;
    }

    public void settCPv(BigDecimal tCPv) {
        this.tCPv = tCPv;
    }

    public BigDecimal gettCUv() {
        return tCUv;
    }

    public void settCUv(BigDecimal tCUv) {
        this.tCUv = tCUv;
    }

    public BigDecimal gettCPvClick() {
        return tCPvClick;
    }

    public void settCPvClick(BigDecimal tCPvClick) {
        this.tCPvClick = tCPvClick;
    }

    public BigDecimal gettCuvClick() {
        return tCuvClick;
    }

    public void settCuvClick(BigDecimal tCuvClick) {
        this.tCuvClick = tCuvClick;
    }

    public BigDecimal gettSPv() {
        return tSPv;
    }

    public void settSPv(BigDecimal tSPv) {
        this.tSPv = tSPv;
    }

    public BigDecimal gettSUv() {
        return tSUv;
    }

    public void settSUv(BigDecimal tSUv) {
        this.tSUv = tSUv;
    }

    public Timestamp gettCreateTime() {
        return tCreateTime;
    }

    public void settCreateTime(Timestamp tCreateTime) {
        this.tCreateTime = tCreateTime;
    }

    public String gettCreateUser() {
        return tCreateUser;
    }

    public void settCreateUser(String tCreateUser) {
        this.tCreateUser = tCreateUser;
    }

    public Timestamp gettUpdateTime() {
        return tUpdateTime;
    }

    public void settUpdateTime(Timestamp tUpdateTime) {
        this.tUpdateTime = tUpdateTime;
    }

    public String gettUpdateUser() {
        return tUpdateUser;
    }

    public void settUpdateUser(String tUpdateUser) {
        this.tUpdateUser = tUpdateUser;
    }
}
