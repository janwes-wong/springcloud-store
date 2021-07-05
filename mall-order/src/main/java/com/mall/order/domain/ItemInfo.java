package com.mall.order.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @author Janwes
 * @version 1.0
 * @package com.mall.order.domain
 * @date 2021/7/4 23:11
 * @description
 */
@ApiModel(value = "商品信息")
public class ItemInfo {

    @ApiModelProperty(value = "商品id", example = "EX202100025")
    private String itemId;

    @ApiModelProperty(value = "商品名称", example = "伊利牛奶")
    private String itemName;

    @ApiModelProperty(value = "生成日期", example = "2021-05-05 20:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") // 设置查询数据库时间字段时，根据自定义的格式进行展示
    private Date productTime;

    @ApiModelProperty(value = "有效期", example = "12个月")
    private int usefulLife;

    public ItemInfo() {
    }

    public ItemInfo(String itemId, String itemName, Date productTime, int usefulLife) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.productTime = productTime;
        this.usefulLife = usefulLife;
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

    public Date getProductTime() {
        return productTime;
    }

    public void setProductTime(Date productTime) {
        this.productTime = productTime;
    }

    public int getUsefulLife() {
        return usefulLife;
    }

    public void setUsefulLife(int usefulLife) {
        this.usefulLife = usefulLife;
    }
}
