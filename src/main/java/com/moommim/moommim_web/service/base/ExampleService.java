package com.moommim.moommim_web.service.base;

import com.moommim.moommim_web.model.Ads;
import java.util.List;

public interface ExampleService extends BaseService {

    Ads getAdsById(int id);
    
    List<Ads> getAllAds();
    
    List<Ads> getAllAdsByPosition(String position);
    
    List<Ads> getAllAdsByType(String type);
  
    Ads updateAds(Ads ads); 
    
    boolean removeAdsById(int id);

}
