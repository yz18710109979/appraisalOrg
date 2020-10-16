package com.jingyou.utils;

import java.util.Map;

public final class MapUtil {
    public static boolean isEmpty(Map map){
        return map == null || map.isEmpty();
    }

    public static boolean isNotEmpty(Map map){
        return !isEmpty(map);
    }
}
