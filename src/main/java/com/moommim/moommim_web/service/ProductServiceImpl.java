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
        List<ProductStock> result = null;
        try {
            result = productStockRepository.findWithKeyword("%" + productName +"%");
        } catch (Exception ex) {
            System.out.println("getProductByName ->" + ex.getMessage());
        }
        return result;
    }

    @Override
    public List<ProductStock> getAllProduct() {
        return productStockRepository.findAll();
    }

    @Override
    public List<ProductStock> getAllProductByCategoryId(int categoryId) {
        return productStockRepository.findOptionalByCategoryId_id(categoryId);
    }

    @Override
    public List<ProductStock> getAllProductByCategoryId(int categoryId, String isShow) {
        return productStockRepository.findOptionalByCategoryId_idAndIsShow(categoryId, isShow);
    }

}
