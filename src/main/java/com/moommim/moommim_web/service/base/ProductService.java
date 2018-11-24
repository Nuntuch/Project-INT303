package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.ProductStock;
import java.util.List;

public interface ProductService extends BaseService {

    ProductStock getProductById(int id);

    List<ProductStock> getProductByName(String productName);

    List<ProductStock> getAllProduct();

    List<ProductStock> getAllProductByCategoryId(int categoryId);

    boolean updateProduct(ProductStock product);

    boolean removeProductById(int id);

}
