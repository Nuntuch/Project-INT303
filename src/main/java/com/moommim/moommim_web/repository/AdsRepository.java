package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.Ads;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Transactional
@Repository
public abstract class AdsRepository extends AbstractEntityRepository<Ads, Integer> {
    
    public abstract Ads findOptionalById(int id);

    public abstract Ads findOptionalByName(String name);
    
    public abstract List<Ads> findOptionalByPosition(String position);
    
    public abstract List<Ads> findOptionalByType(String type);
    
    public abstract int removeById(int id);

}
