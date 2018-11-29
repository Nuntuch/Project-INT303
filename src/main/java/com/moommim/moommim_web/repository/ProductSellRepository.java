package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.ProductSale;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class ProductSellRepository extends AbstractEntityRepository<ProductSale, Integer> {

    public abstract List<ProductSale> findOptionalByBillId_id(int billId);
    
}
