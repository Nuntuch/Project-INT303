package com.moommim.moommim_web.service;

import com.moommim.moommim_web.repository.AdsRepository;
import com.moommim.moommim_web.model.Ads;
import com.moommim.moommim_web.service.base.ExampleService;
import java.util.List;
import javax.inject.Inject;
import javax.transaction.Transactional;

public class ExampleServiceImpl implements ExampleService {

    @Inject
    private AdsRepository adsRepo;

    @Override
    public Ads getAdsById(int id) {
        return adsRepo.findById(id);
    }

    @Override
    public List<Ads> getAllAds() {
        return adsRepo.findAll();
    }

    @Override
    public List<Ads> getAllAdsByPosition(String position) {
        return adsRepo.findByPosition(position);
    }

    @Override
    public List<Ads> getAllAdsByType(String type) {
        return adsRepo.findByType(type);
    }

    @Override
    public Ads updateAds(Ads adsModel) {
        return adsRepo.save(adsModel);
    }

    @Override
    public boolean removeAdsById(int id) {
        return adsRepo.removeById(id) > 0;
    }

}
