package com.moommim.moommim_web.service;

import com.moommim.moommim_web.model.ProductSale;
import com.moommim.moommim_web.repository.BillRepository;
import com.moommim.moommim_web.repository.ProductSellRepository;
import com.moommim.moommim_web.service.base.ProductSellService;
import com.moommim.moommim_web.util.Util;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Nuntuch Thongyoo
 */
public class ProductSellServiceImpl implements ProductSellService {

    @Inject
    ProductSellRepository productSellRepository;

    @Override
    public boolean create(ProductSale product) {
        return Util.isNotEmpty(productSellRepository.save(product));
    }

    @Override
    public List<ProductSale> getProductSaleListFromBill(int billId) {
        return productSellRepository.findOptionalByBillId_id(billId);
    }

}
