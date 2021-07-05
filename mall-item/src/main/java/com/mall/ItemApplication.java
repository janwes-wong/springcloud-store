package com.mall;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall
 * @date 2021/3/11 17:40
 * @description 商品微服务启动类
 */
@SpringBootApplication
@EnableEurekaClient // EurekaClient客户端  将服务地址和端口注册到注册中心
@EnableApolloConfig // apollo配置中心
public class ItemApplication {

    private static final Logger log = LoggerFactory.getLogger(ItemApplication.class);

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ConfigurableApplicationContext context = SpringApplication.run(ItemApplication.class, args);
        long time = (System.currentTimeMillis() - startTime) / 1000;

        String message1 = "启动完成-耗时：%ds，链接：http://%s:%s/swagger-ui.html";
        String message2 = "启动完成-耗时：%ds，链接：http://%s:%s/doc.html";
        // 服务端口号
        String port = context.getEnvironment().getProperty("server.port");
        // 服务地址
        String address = context.getEnvironment().getProperty("spring.cloud.client.ip-address");

        log.info(String.format(message1, time, address, port));
        log.info(String.format(message2, time, address, port));
    }
}
