package com.moommim.moommim_web.repository;

import java.util.List;
import javax.transaction.Transactional;

import com.moommim.moommim_web.model.ProductStock;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class ProductStockRepository extends AbstractEntityRepository<ProductStock, Integer> {

    public abstract ProductStock findById(int id);

    public abstract ProductStock findByName(String name);

    public abstract ProductStock findByNameLike(String name);

    public abstract List<ProductStock> findByCategoryId_id(int categoryId);
    
    public abstract List<ProductStock> findByCategoryId_idAndStatusAndIsShow(int categoryId, String status, String isShow);

    public abstract int removeById(int id);

}
