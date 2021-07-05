package com.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author janwes
 * @version 1.0
 * @date 2021/2/4 11:13
 * @description 标题
 * @package com.janwes
 */
@SpringBootApplication
@EnableEurekaClient
public class FrontApplication {
    public static void main(String[] args) {
        SpringApplication.run(FrontApplication.class,args);
    }
}
