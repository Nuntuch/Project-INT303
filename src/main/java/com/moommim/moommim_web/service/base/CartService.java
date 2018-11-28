package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.ProductStock;
import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    int getTotalQuantity();

    BigDecimal getTotalPrice();

    List<ProductStock> getProductList();

    void addProduct(ProductStock product);

    void removeProduct(int productId);

}
