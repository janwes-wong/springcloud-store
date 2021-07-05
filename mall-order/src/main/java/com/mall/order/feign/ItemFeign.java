package com.mall.order.feign;

import com.mall.order.ItemFeignHystrix;
import com.mall.order.domain.ItemInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall.order.feign
 * @date 2021/7/4 23:23
 * @description feign服务调用接口
 */
@FeignClient(name = "mall-item", path = "/item", fallback = ItemFeignHystrix.class)
public interface ItemFeign {

    /**
     * 根据商品id获取商品信息
     *
     * @param itemId
     * @return
     */
    @GetMapping("/getItemInfo")
    ItemInfo getItemInfoByItemId(@RequestParam("itemId") String itemId);
}
