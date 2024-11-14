package org.airwallex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * RateDetailsDTO 类用于封装汇率详情信息，包括购买数量、用户等级、交易费率和卖出数量。
 * 该类主要用于数据传输对象（DTO），通过 JSON 序列化和反序列化与外部系统交互。
 */
public class RateDetailsDTO {

    /**
     * 表示购买的数量，使用BigDecimal以确保高精度的数值处理。
     */
    @JsonProperty("buy_amount")
    private BigDecimal buyAmount;

    /**
     * 表示用户的等级，通常用于区分不同类型的用户或客户。
     */
    @JsonProperty("level")
    private String level;

    /**
     * 表示交易的费率，使用BigDecimal以确保高精度的数值处理。
     */
    @JsonProperty("rate")
    private BigDecimal rate;

    /**
     * 表示卖出的数量，使用BigDecimal以确保高精度的数值处理。
     */
    @JsonProperty("sell_amount")
    private BigDecimal sellAmount;


    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }
}
