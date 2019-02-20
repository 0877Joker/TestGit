package com.hksql.zhai.rStatistics.rStatisticsUpdate;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class HKRStatInfoUpdInfo {
    private Integer statisticsDate;

    private String statisticsUpdateUser;

    private Timestamp statisticsCreateTime;

    private String statisticsCreateUser;

    private Long statisticsExp;

    private Long statisticsClick;

    private BigDecimal statisticsRate;

    public Integer getStatisticsDate() {
        return statisticsDate;
    }

    public void setStatisticsDate(Integer statisticsDate) {
        this.statisticsDate = statisticsDate;
    }

    public String getStatisticsUpdateUser() {
        return statisticsUpdateUser;
    }

    public void setStatisticsUpdateUser(String statisticsUpdateUser) {
        this.statisticsUpdateUser = statisticsUpdateUser;
    }

    public Timestamp getStatisticsCreateTime() {
        return statisticsCreateTime;
    }

    public void setStatisticsCreateTime(Timestamp statisticsCreateTime) {
        this.statisticsCreateTime = statisticsCreateTime;
    }

    public String getStatisticsCreateUser() {
        return statisticsCreateUser;
    }

    public void setStatisticsCreateUser(String statisticsCreateUser) {
        this.statisticsCreateUser = statisticsCreateUser;
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
