package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    /**
     *    添加到购物车
     * @param shoppingCartDTO
     */
    void addToShoppingCart(ShoppingCartDTO shoppingCartDTO);

    /**
     *    查看购物车
     * @return
     */
    List<ShoppingCart> showShoppingCart();

    /**
     *   清空购物车
     */
    void clear();

    /**
     *         减少购物车中一个商品
     * @param shoppingCartDTO
     */
    void subShoppingCart(ShoppingCartDTO shoppingCartDTO);
}
