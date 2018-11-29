package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.ProductSale;
import java.util.List;

public interface ProductSellService {

    boolean create(ProductSale product);

    List<ProductSale> getProductSaleListFromBill(int userId);

}
