package com.jingyou.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Slf4j
public class ObjectUtils {


    public static Object callMethod(Object objService,String methodName,Object... paramargs) throws Exception {
        Object returnObj = null;
        try {
            List<Class> classList = new ArrayList<>();
            if(Objects.nonNull(paramargs)){
                for(Object paramarg : paramargs)
                    classList.add(paramarg.getClass());
            }
            Class[] classArr = new Class[classList.size()];
            Method method = objService.getClass().getMethod(methodName,classList.toArray(classArr));
            returnObj = method.invoke(objService, paramargs);
        } catch (Exception e) {
            throw e;
        }
        return returnObj;
    }
}
