package org.airwallex.test;

import org.airwallex.utils.AllureEnvironmentGenerator;
import org.airwallex.utils.AllureExecutorGenerator;
import org.testng.annotations.BeforeSuite;


public class TestSetup {

    @BeforeSuite(alwaysRun = true)
    public void setupAllureExecutor() {
        AllureExecutorGenerator.generateExecutorJson();
    }

    @BeforeSuite(alwaysRun = true)
    public void setupEnvironment() {
        AllureEnvironmentGenerator.setupAllureEnvironment("Test");
    }

}
