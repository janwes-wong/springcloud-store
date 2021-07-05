package com.mall.order.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Janwes
 * @version 1.0
 * @package com.janwes.order.domain
 * @date 2021/3/11 17:42
 * @description 订单信息类
 */
@ApiModel(value = "订单信息")
public class OrderInfo {

    @ApiModelProperty(value = "用户名", example = "张三")
    private String username;

    @ApiModelProperty(value = "订单id", example = "SM005")
    private String orderId;

    @ApiModelProperty(value = "商品id", example = "xxxx005")
    private String itemId;

    @ApiModelProperty(value = "商品名称", example = "雀巢咖啡")
    private String itemName;

    @ApiModelProperty(value = "价格", example = "50")
    private Long price;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
