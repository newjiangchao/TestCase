# Airwallex自动化测试案例

> 结合TestNG使用SpringBoot实现Airwallex接口测试需求。

### 测试执行
```
1、执行测试用例
mvn clean test -Dspring.profiles.active=test -Dsurefire.suiteXmlFiles=src/test/java/org/airwallex/test/TestCaseRun.xml
```

### 测试报告查看
```
1. 本地安装allure
brew install allure

2. 查看安装是否成功
allure --version

3. 可以直接创建allure服务查看
allure serve allure-results
```

### 测试用例描述
```
在返回参数中汇率正确的情况下，判断转换后的币种金额是否正确。汇率也可单独从获取汇率的接口读取，和被测接口返回的汇率进行比较，测试目标是根据被测接口的改动点设计测试用例。
USDToAUDTestCase1：USD兑换AUD正例，购买的货币金额为100.00
USDToAUDTestCase2：USD兑换AUD反例，购买的货币金额为0.00
USDToAUDTestCase3：USD兑换AUD反例，购买的货币金额为0.01
USDToAUDTestCase4：USD兑换AUD反例，购买的货币金额为空
USDToAUDTestCase5：USD兑换AUD反例，出售的货币类型为空
USDToAUDTestCase6：USD兑换AUD反例，购买的货币类型为空
USDToAUDTestCase7：USD兑换AUD反例，出售的货币类型传非法值
USDToAUDTestCase8：USD兑换AUD反例，购买的货币类型传非法值
USDToAUDTestCase9：USD兑换AUD反例，大额转换
USDToAUDTestCase10：USD兑换AUD正例，购买的货币金额为999.99
USDToAUDTestCase11：USD兑换AUD正例，购买的货币金额为1000.01
其它测试用例：和上述测试场景类似，只是参数不同，测试结果也类似。无需一一列举。
```

### CI/CD配置部署
```
todo
```

### 代码覆盖率实现
```
todo
```

### 自动化测试结果总结：
1、各别币种汇率转换后的currency_pair字段值buy_currency和sell_currency拼接字符串的前后顺序不一致。
2、USDToAUDTestCase4测试案例中不传购买额度的情况下，默认转10000澳元的原因是什么？