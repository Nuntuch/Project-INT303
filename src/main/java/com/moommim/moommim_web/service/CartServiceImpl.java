package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.CartItem;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.CartService;
import com.moommim.moommim_web.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {

    private Map<Integer, CartItem> cart;

    public CartServiceImpl() {
        cart = new HashMap();
    }

    @Override
    public List<CartItem> getCartItemList() {
        return new ArrayList(cart.values());
    }

    @Override
    public int getTotalQuantity() {
        int total = 0;
        Collection<CartItem> cartItemList = cart.values();
        for (CartItem item : cartItemList) {
            total += item.getQuantity();
        }
        return total;
    }

    @Override
    public BigDecimal getTotalPrice() {
        BigDecimal result = BigDecimal.ZERO;
        Collection<CartItem> cartItemList = cart.values();
        for (CartItem item : cartItemList) {
            result = result.add(item.getTotalPrice());
        }
        return result;
    }

    @Override
    public void addProduct(ProductStock product) {
        CartItem cartItem = cart.get(product.getId());
        if (Util.isEmpty(cartItem)) {
            cart.put(product.getId(), new CartItem(product));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

    @Override
    public void removeProduct(int productId) {
        CartItem cartItem = cart.get(productId);
        if (Util.isNotEmpty(cartItem)) {
            if (cartItem.getQuantity() >= 1) {
                cartItem.setQuantity(cartItem.getQuantity() - 1);
            }
            if(cartItem.getQuantity() == 0) {
                clearProduct(productId);
            }
        }
    }

    @Override
    public void clearProduct(int productId) {
        cart.remove(productId);
    }

    @Override
    public void clearAll() {
        cart.clear();
    }

}
