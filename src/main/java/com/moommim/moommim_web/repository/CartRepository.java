package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.CartItem;
import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.util.Util;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartRepository {

    private Map<Integer, CartItem> cart;

    public CartRepository() {
        cart = new HashMap();
    }

    public List<CartItem> getCartItemList() {
        return new ArrayList(cart.values());
    }

    public int getTotalQuantity() {
        int total = 0;
        Collection<CartItem> cartItemList = cart.values();
        for (CartItem item : cartItemList) {
            total += item.getQuantity();
        }
        return total;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal result = BigDecimal.ZERO;
        Collection<CartItem> cartItemList = cart.values();
        for (CartItem item : cartItemList) {
            result = result.add(item.getTotalPrice());
        }
        return result;
    }

    public void addProduct(ProductStock product) {
        CartItem cartItem = cart.get(product.getId());
        if (Util.isEmpty(cartItem)) {
            cart.put(product.getId(), new CartItem(product));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

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

    public void clearProduct(int productId) {
        cart.remove(productId);
    }

    public void clearAll() {
        cart.clear();
    }

}
