package com.moommim.moommim_web.model;

import java.math.BigDecimal;

public class CartItem {

    private ProductStock product;
    private BigDecimal salePrice;
    private int quantity;

    public CartItem() {
    }

    public CartItem(ProductStock product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.salePrice = product.getPrice();
    }

    public CartItem(ProductStock product) {
        this(product, 1);
    }

    public BigDecimal getTotalPrice() {
        return this.salePrice.multiply(new BigDecimal(this.quantity));
    }

    public ProductStock getProduct() {
        return product;
    }

    public void setProduct(ProductStock product) {
        this.product = product;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(BigDecimal salePrice) {
        this.salePrice = salePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
