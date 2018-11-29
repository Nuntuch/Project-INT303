package com.moommim.moommim_web.repository;

import java.util.List;
import javax.transaction.Transactional;

import com.moommim.moommim_web.model.ProductStock;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Query;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class ProductStockRepository extends AbstractEntityRepository<ProductStock, Integer> {

    public abstract ProductStock findOptionalById(int id);

    public abstract ProductStock findOptionalByName(String name);

    @Query("select p from ProductStock p where lower(p.name) like ?1")
    public abstract List<ProductStock> findWithKeyword(String name);

    public abstract List<ProductStock> findOptionalByCategoryId_id(int categoryId);
    
    public abstract List<ProductStock> findOptionalByCategoryId_idAndIsShow(int categoryId, String isShow);

    public abstract int removeById(int id);

}
