package com.moommim.moommim_web.util;

import com.moommim.moommim_web.model.ProductCategory;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;

public class CacheHelper {

    private static CacheHelper cacheHelper = null;
    private static CacheManager cacheManager;
    private static Cache<Integer, ProductCategory> menuCache;
    private static final String MENU_CACHE_KEY = "MENU_CACHE_KEY";

    private CacheHelper() {
        cacheManager = CacheManagerBuilder
                .newCacheManagerBuilder()
                .build();
        cacheManager.init();
    }

    public static CacheHelper getInstance() {
        if (cacheHelper == null) {
            cacheHelper = new CacheHelper();
        }
        return cacheHelper;
    }

    public static void createMenuCache() {
        menuCache = cacheManager
                .createCache(MENU_CACHE_KEY, CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(
                                Integer.class, ProductCategory.class,
                                ResourcePoolsBuilder.heap(10)));
    }

    public static Cache<Integer, ProductCategory> getMenuFromCacheManager() {
        return cacheManager.getCache(MENU_CACHE_KEY, Integer.class, ProductCategory.class);
    }

}
