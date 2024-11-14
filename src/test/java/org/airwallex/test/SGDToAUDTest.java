package org.airwallex.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.airwallex.dto.ResponseBodyDTO;
import org.airwallex.utils.CommonUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SGDToAUDTest {

    @Test(groups = {"smoke", "positive"}, testName = "SGD to AUD test")
    public void SGDToAUDTestCase1() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "SGD";
        // 购买的货币类型
        String buyCurrency = "AUD";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("100.00");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, buyAmount.toString(), sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        ResponseBodyDTO responseBody = JSONUtil.toBean(response.getBody(), ResponseBodyDTO.class);
        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(responseBody.getBuyCurrency(), buyCurrency, "购买货币类型");
        // 获取当前时间
        String date = DateUtil.formatDate(DateUtil.date());
        Assert.assertEquals(responseBody.getConversionDate().toString(), date, "汇率转换日期");
        Assert.assertEquals(responseBody.getCurrencyPair(), buyCurrency + sellCurrency, "货币对");
        Assert.assertEquals(responseBody.getDealtCurrency(), buyCurrency, "交易货币类型");

        // 汇率
        BigDecimal exchangeRate = responseBody.getRateDetails().get(0).getRate();
        // 计算美元金额（保留两位小数，四舍五入）
        BigDecimal calculatedSellAmount = buyAmount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

        Assert.assertEquals(responseBody.getRate(), exchangeRate, "汇率");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getBuyAmount(), buyAmount, "购买数量");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getLevel(), "CLIENT", "用户等级");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getRate(), exchangeRate, "交易费率");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getSellAmount(), calculatedSellAmount, "卖出数量");
        Assert.assertEquals(responseBody.getSellCurrency(), sellCurrency, "出售货币类型");

    }

    @Test(groups = {"Negative"}, testName = "SGD to AUD negative test")
    public void SGDToAUDTestCase2() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "SGD";
        // 购买的货币类型
        String buyCurrency = "AUD";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("0.00");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, buyAmount.toString(), sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        System.out.println(response.getBody());
        Assert.assertEquals(response.getStatus(), 400);

        JSONObject responseBody = JSONUtil.parseObj(response.getBody());
        Assert.assertEquals(responseBody.get("code"), "invalid_parameter", "返回码");
    }

    @Test(groups = {"Negative"}, testName = "SGD to AUD negative test")
    public void SGDToAUDTestCase3() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "SGD";
        // 购买的货币类型
        String buyCurrency = "AUD";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("0.01");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, buyAmount.toString(), sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 400);

        JSONObject responseBody = JSONUtil.parseObj(response.getBody());
        Assert.assertEquals(responseBody.get("code"), "validation_error", "返回码");
    }

    @Test(groups = {"Negative"}, testName = "SGD to AUD negative test")
    public void SGDToAUDTestCase4() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "SGD";
        // 购买的货币类型
        String buyCurrency = "AUD";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("10000.00");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, "", sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        ResponseBodyDTO responseBody = JSONUtil.toBean(response.getBody(), ResponseBodyDTO.class);
        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 200);
        Assert.assertEquals(responseBody.getBuyCurrency(), buyCurrency, "购买货币类型");
        // 获取当前时间
        String date = DateUtil.formatDate(DateUtil.date());
        Assert.assertEquals(responseBody.getConversionDate().toString(), date, "汇率转换日期");
        Assert.assertEquals(responseBody.getCurrencyPair(), buyCurrency + sellCurrency, "货币对");
        Assert.assertEquals(responseBody.getDealtCurrency(), buyCurrency, "交易货币类型");

        // 汇率
        BigDecimal exchangeRate = responseBody.getRateDetails().get(0).getRate();
        // 计算美元金额（保留两位小数，四舍五入）
        BigDecimal calculatedSellAmount = buyAmount.multiply(exchangeRate).setScale(2, RoundingMode.HALF_UP);

        Assert.assertEquals(responseBody.getRate(), exchangeRate, "汇率");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getBuyAmount(), buyAmount, "购买数量");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getLevel(), "CLIENT", "用户等级");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getRate(), exchangeRate, "交易费率");
        Assert.assertEquals(responseBody.getRateDetails().get(0).getSellAmount(), calculatedSellAmount, "卖出数量");
        Assert.assertEquals(responseBody.getSellCurrency(), sellCurrency, "出售货币类型");
    }

    @Test(groups = {"Negative"}, testName = "SGD to AUD negative test")
    public void SGDToAUDTestCase5() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "";
        // 购买的货币类型
        String buyCurrency = "AUD";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("100.00");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, buyAmount.toString(), sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 400);

        JSONObject responseBody = JSONUtil.parseObj(response.getBody());
        Assert.assertEquals(responseBody.get("code"), "invalid_argument", "返回码");
    }

    @Test(groups = {"Negative"}, testName = "SGD to AUD negative test")
    public void SGDToAUDTestCase6() {
        String token = CommonUtils.getLoginToken();
        // 出售的货币类型
        String sellCurrency = "AUD";
        // 购买的货币类型
        String buyCurrency = "";
        // 购买的数量
        BigDecimal buyAmount = new BigDecimal("100.00");

        String url = StrUtil.format("https://api-demo.airwallex.com/api/v1/fx/rates/current?" +
                        "buy_currency={}&buy_amount={}&sell_currency={}",
                buyCurrency, buyAmount.toString(), sellCurrency);
        HttpResponse<String> response = Unirest.get(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + token).asString();

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 400);

        JSONObject responseBody = JSONUtil.parseObj(response.getBody());
        Assert.assertEquals(responseBody.get("code"), "invalid_argument", "返回码");
    }

}
