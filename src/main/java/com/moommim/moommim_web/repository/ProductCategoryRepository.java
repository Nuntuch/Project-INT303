package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.ProductCategory;
import javax.transaction.Transactional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class ProductCategoryRepository extends AbstractEntityRepository<ProductCategory, Integer> {
    
    public abstract ProductCategory findById(int id);

    public abstract ProductCategory findByName(String name);
    
    public abstract int removeById(int id);

}
