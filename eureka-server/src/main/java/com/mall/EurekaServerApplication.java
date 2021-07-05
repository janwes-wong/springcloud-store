package com.mall;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes
 * @date 2021/6/30 18:15
 * @description
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    private static final Logger log = LoggerFactory.getLogger(EurekaServerApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ConfigurableApplicationContext context = SpringApplication.run(EurekaServerApplication.class, args);
        long time = (System.currentTimeMillis() - startTime) / 1000;

        String message1 = "启动完成-耗时：%ds，[registry center address] --> http://%s:%s";
        // 服务端口号
        String port = context.getEnvironment().getProperty("server.port");
        // 服务地址
        String address = context.getEnvironment().getProperty("spring.cloud.client.ip-address");

        log.info(String.format(message1, time, address, port));
    }
}
