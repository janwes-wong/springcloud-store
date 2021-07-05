package com.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall
 * @date 2021/7/5 16:21
 * @description
 */
@SpringBootApplication
@EnableHystrix // 开启服务熔断降级功能
@EnableHystrixDashboard
public class HystrixDashboardApplication {

    private static final Logger log = LoggerFactory.getLogger(HystrixDashboardApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ConfigurableApplicationContext context = SpringApplication.run(HystrixDashboardApplication.class, args);
        long time = (System.currentTimeMillis() - startTime) / 1000;

        String message = "启动完成-耗时：%ds，[Hystrix Dashboard address] --> http://%s:%s/hystrix";
        // 服务端口号
        String port = context.getEnvironment().getProperty("server.port");
        // 服务地址
        String address = context.getEnvironment().getProperty("spring.cloud.client.ip-address");
        log.info(String.format(message, time, address, port));
    }
}
