package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.CartItem;
import com.moommim.moommim_web.model.ProductStock;
import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    List<CartItem> getCartItemList();

    int getTotalQuantity();

    BigDecimal getTotalPrice();

    void addProduct(ProductStock product);

    void removeProduct(int productId);

    void clearProduct(int productId);

    public void clearAll();

}
