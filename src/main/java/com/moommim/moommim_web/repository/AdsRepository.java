package com.moommim.moommim_web.repository;

import com.moommim.moommim_web.model.Ads;
import java.util.List;
import org.apache.deltaspike.data.api.AbstractEntityRepository;
import org.apache.deltaspike.data.api.Repository;

@Repository
public abstract class AdsRepository extends AbstractEntityRepository<Ads, Integer> {
    
    public abstract Ads findById(int id);

    public abstract Ads findByName(String name);
    
    public abstract List<Ads> findByPosition(String position);
    
    public abstract List<Ads> findByType(String type);
    
    public abstract int removeById(int id);

}
