package com.mall.order.controller;

import com.mall.common.model.Result;
import com.mall.order.domain.ItemInfo;
import com.mall.order.domain.OrderInfo;
import com.mall.order.feign.ItemFeign;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.order.controller
 * @date 2021/7/1 15:06
 * @description
 */
@Api(tags = "订单中心系统")
@RestController
@RequestMapping("/order")
public class OrderController {

    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ItemFeign itemFeign;

    @ApiOperation(value = "获取订单信息")
    @GetMapping("/{username}")
    public Result<OrderInfo> getOderInfo(@PathVariable("username") String username) {
        // 1. 根据用户名username获取订单信息数据
        String orderId = "orderId:001";
        String itemId = "SN:0001";
        log.info("[订单信息数据]---" + username + ", orderId: " + orderId);

        // 使用spring的RestTemplate实现远程服务间调用
        OrderInfo orderInfo = restTemplate.getForObject("http://192.168.215.251:7700/item/" + itemId, OrderInfo.class);
        orderInfo.setOrderId(orderId);
        orderInfo.setUsername(username);
        orderInfo.setItemId(itemId);
        return Result.ok(orderInfo);
    }

    @GetMapping("/getMessage")
    public Result<ItemInfo> getMessage() {
        String itemId = "HK2200669";
        // 远程服务调用
        ItemInfo itemInfo = itemFeign.getItemInfoByItemId(itemId);
        return Result.ok(itemInfo);
    }


}
