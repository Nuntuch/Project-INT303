package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.ProductCategory;
import java.util.List;

public interface ProductCategoryService extends BaseService {

    ProductCategory getCategoryById(int id);

    List<ProductCategory> getAllCategory();

    boolean updateCategory(ProductCategory category);

    boolean removeCategoryById(int id);

}
