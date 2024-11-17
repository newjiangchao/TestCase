package org.airwallex.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentGenerator {
    private static final String RESULTS_DIR = "target/allure-results"; // Allure结果目录

    public static void setupAllureEnvironment(String env) {
        Properties props = new Properties();
        props.setProperty("Environment", env);

        try (FileOutputStream fos = new FileOutputStream(RESULTS_DIR + "/environment.properties")) {
            props.store(fos, "Allure Environment Properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
