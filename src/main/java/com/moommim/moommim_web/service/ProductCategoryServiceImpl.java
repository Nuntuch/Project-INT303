package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.ProductCategory;
import com.moommim.moommim_web.repository.ProductCategoryRepository;
import com.moommim.moommim_web.service.base.ProductCategoryService;
import com.moommim.moommim_web.util.Util;
import java.util.List;
import javax.inject.Inject;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Inject
    private ProductCategoryRepository categoryRepository;

    @Override
    public ProductCategory getCategoryById(int id) {
        return categoryRepository.findOptionalById(id);
    }

    @Override
    public List<ProductCategory> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public boolean updateCategory(ProductCategory category) {
        return Util.isNotEmpty(categoryRepository.save(category));
    }

    @Override
    public boolean removeCategoryById(int id) {
        return categoryRepository.removeById(id) > 0;
    }

}
