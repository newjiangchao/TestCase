package org.airwallex.demo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * ResponseBodyDTO 类用于封装响应体中的数据，主要包含货币交易的相关信息。
 * 该类主要用于数据传输对象（DTO），通过 JSON 序列化和反序列化与外部系统交互。
 */
public class ResponseBodyDTO {

    /**
     * 用于购买货币的字段，表示用户希望购买的货币类型
     */
    @JsonProperty("buy_currency")
    private String buyCurrency;

    /**
     * 汇率转换日期，用于确定适用的汇率日期
     */
    @JsonProperty("conversion_date")
    private LocalDate conversionDate;

    /**
     * 记录创建时间，采用UTC偏移的日期时间格式
     */
    @JsonProperty("created_at")
    private OffsetDateTime createdAt;

    /**
     * 货币对，表示交易涉及的两种货币
     */
    @JsonProperty("currency_pair")
    private String currencyPair;

    /**
     * 交易货币，表示实际进行交易的货币类型
     */
    @JsonProperty("dealt_currency")
    private String dealtCurrency;

    /**
     * 汇率，表示两种货币之间的兑换比率
     */
    @JsonProperty("rate")
    private BigDecimal rate;

    /**
     * 汇率详情，包含汇率的具体信息和来源等
     */
    @JsonProperty("rate_details")
    private List<RateDetailsDTO> rateDetails;

    /**
     * 用于出售货币的字段，表示用户希望出售的货币类型
     */
    @JsonProperty("sell_currency")
    private String sellCurrency;


    public String getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(String buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public LocalDate getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(LocalDate conversionDate) {
        this.conversionDate = conversionDate;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public String getDealtCurrency() {
        return dealtCurrency;
    }

    public void setDealtCurrency(String dealtCurrency) {
        this.dealtCurrency = dealtCurrency;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public List<RateDetailsDTO> getRateDetails() {
        return rateDetails;
    }

    public void setRateDetails(List<RateDetailsDTO> rateDetails) {
        this.rateDetails = rateDetails;
    }

    public String getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }
}
