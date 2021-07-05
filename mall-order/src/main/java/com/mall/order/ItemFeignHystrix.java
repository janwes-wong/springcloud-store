package com.mall.order;

import com.mall.order.domain.ItemInfo;
import com.mall.order.feign.ItemFeign;
import org.springframework.stereotype.Component;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall.order
 * @date 2021/7/5 11:06
 * @description 断路器类 实现feign接口服务降级，也可在feign接口创建内部类并实现feign接口
 */
@Component
public class ItemFeignHystrix implements ItemFeign {

    @Override
    public ItemInfo getItemInfoByItemId(String itemId) {
        // 演示 当被调用方服务 mall-item服务宕机或关闭时，则被触发并返回该实现类的数据(或者直接返回null也可以)
        return new ItemInfo(null, "hello,message：" + itemId + " send failed", null, 0);
    }
}
