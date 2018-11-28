package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.service.base.CartService;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

public class CartServiceImpl implements CartService {

    @Inject
    CartService cartService;

    @Override
    public int getTotalQuantity() {
        return cartService.getTotalQuantity();
    }

    @Override
    public BigDecimal getTotalPrice() {
        return cartService.getTotalPrice();
    }

    @Override
    public List<ProductStock> getProductList() {
        return cartService.getProductList();
    }

    @Override
    public void addProduct(ProductStock product) {
        cartService.addProduct(product);
    }

    @Override
    public void removeProduct(int productId) {
        cartService.removeProduct(productId);
    }

}
