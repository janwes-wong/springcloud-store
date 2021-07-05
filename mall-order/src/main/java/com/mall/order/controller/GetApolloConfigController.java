package com.mall.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall.order.controller
 * @date 2021/7/3 13:28
 * @description
 */
@Api(tags = "用户中心")
@RestController
@RequestMapping("/user")
public class GetApolloConfigController {

    @Value("${userCenter.username}")
    private String username;

    @ApiOperation(value = "获取用户名")
    @GetMapping("/getUsername")
    public String getUsername() {
        return username;
    }

    /**
     * hystrix熔断，当调用getUserInfoById失败后，调用forBackFindUserById方法
     *
     * @param id
     * @return
     */
    @HystrixCommand(fallbackMethod = "forBackFindUserById")
    @ApiOperation(value = "获取用户信息")
    @GetMapping("/{id}")
    public String getUserInfoById(@PathVariable("id") Long id) {
        // 模拟异常
        int num = 1 / 0;
        return "hello,my name is jack";
    }

    public String forBackFindUserById(Long id) {
        return "search user information failed,please wait for minutes!!!";
    }
}
