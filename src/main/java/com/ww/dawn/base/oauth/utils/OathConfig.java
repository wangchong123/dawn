package com.ww.dawn.base.oauth.utils;

import com.ww.dawn.base.oauth.APIConfig;


public class OathConfig {
    public static String getValue(String key) {
        return APIConfig.getInstance().getValue(key);
    }
}
