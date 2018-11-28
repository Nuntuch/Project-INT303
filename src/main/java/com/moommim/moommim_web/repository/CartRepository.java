package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.CartItem;
import com.moommim.moommim_web.model.ProductStock;
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

    public List<CartItem> getProductList() {
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
        if (cartItem == null) {
            cart.put(product.getId(), new CartItem(product));
        } else {
            cartItem.setQuantity(cartItem.getQuantity() + 1);
        }
    }

    public void removeProduct(int productId) {
        cart.remove(productId);
    }

}
