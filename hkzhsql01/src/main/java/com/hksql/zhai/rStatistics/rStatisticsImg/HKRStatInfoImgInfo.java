package com.hksql.zhai.rStatistics.rStatisticsImg;

import java.math.BigDecimal;

public class HKRStatInfoImgInfo {
    private Integer statisticsDate;

    private String statisticsImgId;

    private String statisticsTitle;

    private Integer statisticsTypeId;

    private String statisticsTypeName;

    private Integer statisticsCompanyId;

    private String statisticsCompanyName;

    private Long statisticsExp;

    private Long statisticsClick;

    private BigDecimal statisticsRate;

    public Integer getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Integer statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getStatisticsImgId() {
        return statisticsImgId;
    }

    public void setStatisticsImgId(String statisticsImgId) {
        this.statisticsImgId = statisticsImgId;
    }

    public String getStatisticsTitle() {
        return statisticsTitle;
    }

    public void setStatisticsTitle(String statisticsTitle) {
        this.statisticsTitle = statisticsTitle;
    }

    public Integer getStatisticsTypeId() {
        return statisticsTypeId;
    }

    public void setStatisticsTypeId(Integer statisticsTypeId) {
        this.statisticsTypeId = statisticsTypeId;
    }

    public String getStatisticsTypeName() {
        return statisticsTypeName;
    }

    public void setStatisticsTypeName(String statisticsTypeName) {
        this.statisticsTypeName = statisticsTypeName;
    }

    public Integer getStatisticsCompanyId() {
        return statisticsCompanyId;
    }

    public void setStatisticsCompanyId(Integer statisticsCompanyId) {
        this.statisticsCompanyId = statisticsCompanyId;
    }

    public String getStatisticsCompanyName() {
        return statisticsCompanyName;
    }

    public void setStatisticsCompanyName(String statisticsCompanyName) {
        this.statisticsCompanyName = statisticsCompanyName;
    }

    public BigDecimal getStatisticsRate() {
        return statisticsRate;
    }

    public void setStatisticsRate(BigDecimal statisticsRate) {
        this.statisticsRate = statisticsRate;
    }

    public Long getStatisticsExp() {
        return statisticsExp;
    }

    public void setStatisticsExp(Long statisticsExp) {
        this.statisticsExp = statisticsExp;
    }

    public Long getStatisticsClick() {
        return statisticsClick;
    }

    public void setStatisticsClick(Long statisticsClick) {
        this.statisticsClick = statisticsClick;
    }
}
