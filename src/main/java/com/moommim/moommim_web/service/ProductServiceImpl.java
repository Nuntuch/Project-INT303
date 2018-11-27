package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.ProductStock;
import com.moommim.moommim_web.repository.ProductStockRepository;
import com.moommim.moommim_web.service.base.ProductService;

import javax.inject.Inject;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    @Inject
    ProductStockRepository productStockRepository;

    @Override
    public ProductStock getProductById(int id) {
        return productStockRepository.findOptionalById(id);
    }

    @Override
    public List<ProductStock> getProductByName(String productName) {
        return null;
    }

    @Override
    public List<ProductStock> getAllProduct() {
        return null;
    }

    @Override
    public List<ProductStock> getAllProductByCategoryId(int categoryId) {
        return productStockRepository.findByCategoryId_id(categoryId);
    }

    @Override
    public List<ProductStock> getAllProductByCategoryId(int categoryId, String isShow) {
        return productStockRepository.findByCategoryId_idAndIsShow(categoryId, isShow);
    }

    @Override
    public boolean updateProduct(ProductStock product) {
        return false;
    }

    @Override
    public boolean removeProductById(int id) {
        return false;
    }
}
