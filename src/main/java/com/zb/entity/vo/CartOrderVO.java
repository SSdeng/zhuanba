package com.zb.entity.vo;

import com.zb.entity.CartOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 购物车订单VO
 *
 * @author YeFeng
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartOrderVO {

    /**
     * 商品id
     */
    private long itemId;

    /**
     * 数量
     */
    private int count;

    public CartOrderVO(CartOrder cartOrder) {
        this.itemId = cartOrder.getItem().getId();
        this.count = cartOrder.getItemCount();
    }
}
