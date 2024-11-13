package org.airwallex.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

public class CommonUtils {

    public static String getLoginToken() {
        HttpResponse<String> response = Unirest.post("https://api-demo.airwallex.com/api/v1/authentication/login")
                .header("Content-Type", "application/json")
                .header("x-client-id", "CVwQh88xTUeqYV-zMpLahg")
                .header("x-api-key", "194dcb42f76d6c19ee0bd52812320d3d20eb69288832197802b31e2d03379bfaf044ccf348ddf46a290f87114e0b84aa")
                .body("{}").asString();

        JSONObject responseBody = JSONUtil.parseObj(response.getBody());
        String token = (String) responseBody.get("token");
        System.out.println(token);
        return token;
    }

}
