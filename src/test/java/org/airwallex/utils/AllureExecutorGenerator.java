package org.airwallex.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class AllureExecutorGenerator {
    private static final String RESULTS_DIR = "target/allure-results"; // Allure结果目录

    public static void generateExecutorJson() {
        JSONObject executor = new JSONObject();

        // 动态填充执行器信息
        executor.set("name", System.getenv("CI_NAME") != null ? System.getenv("CI_NAME") : "Local Execution");
        executor.set("type", System.getenv("CI_TYPE") != null ? System.getenv("CI_TYPE") : "Local");
        executor.set("environment", getEnvironmentInfo());
        executor.set("buildName", System.getenv("BUILD_NAME") != null ? System.getenv("BUILD_NAME") : "Local Build");
        executor.set("branch", System.getenv("BRANCH_NAME") != null ? System.getenv("BRANCH_NAME") : "master");
        executor.set("commit", System.getenv("GIT_COMMIT") != null ? System.getenv("GIT_COMMIT") : "jiangchao");

        try {
            // 确保目标目录存在
            File resultsDir = new File(RESULTS_DIR);
            if (!resultsDir.exists()) {
                resultsDir.mkdirs();
            }

            // 写入 executor.json 文件
            File jsonFile = new File(resultsDir, "executor.json");
            FileUtil.writeString(executor.toStringPretty(), jsonFile, StandardCharsets.UTF_8);

            System.out.println("executor.json 文件已生成: " + jsonFile.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getEnvironmentInfo() {
        return "OS: " + System.getProperty("os.name") + ", Java: " + System.getProperty("java.version");
    }
}


