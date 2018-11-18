package com.moommim.moommim_web.util;

import org.springframework.util.ObjectUtils;

public class Util {

    public static final boolean isNotEmpty(Object object) {
        return !ObjectUtils.isEmpty(object);
    }
    
    public static final boolean isEmpty(Object object) {
        return ObjectUtils.isEmpty(object);
    }
    
}
