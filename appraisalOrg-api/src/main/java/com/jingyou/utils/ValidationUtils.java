package com.jingyou.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author yangzhi
 * @create 2020/10/16
 */
public class ValidationUtils {
    public static <T> T requireNonNull(T var0) {
        if (var0 == null || Objects.equals(var0, "")) {
            throw new NullPointerException();
        } else {
            return var0;
        }

    }

    /**
     * objects类不能满足业务
     * 增加T等于空字符串校验
     * @param var0
     * @param var1
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T var0, String var1) {
        if (var0 == null || Objects.equals(var0, "")) {
            throw new NullPointerException(var1);
        } else {
            return var0;
        }
    }

    /**
     * objects类不能满足业务
     * 增加T等于空字符串校验
     * @param var0
     * @param var1
     * @param <T>
     * @return
     */
    public static <T> T requireNonNull(T var0, String var1, int length) {
        if (var0 == null || Objects.equals(var0, "")) {
            throw new NullPointerException(var1);
        }
        if (var1.length() > length) {
            throw new IllegalArgumentException(var0 + "字段长度超出");
        }
        return var0;

    }

    public static boolean isNull(Object var0) {
        return var0 == null || Objects.equals(var0, "");
    }

    public static boolean nonNull(Object var0) {
        return var0 != null && !Objects.equals(var0, "");
    }

    public static <T> T requireNonNull(T var0, Supplier<String> var1) {
        if (var0 == null || Objects.equals(var0, "")) {
            throw new NullPointerException((String)var1.get());
        } else {
            return var0;
        }
    }

    /**
     * 校验Controller入参VO是否为空
     * @param vo Controller入参VO
     * @return boolean
     * @throws Exception
     */
    public static boolean notNullForVO(Object vo) throws Exception {
        if(Objects.isNull(vo)) ExceptionUtil.throwBusinessException("参数为空");
        return true;
    }

    public static boolean notEquals(String f,String s){
        return !Objects.equals(f,s);
    }
    /*
     * 传入参数不能为空
     * */
    public static void notNull(Object objVo,String... attrs) throws Exception {
        if(Objects.isNull(objVo)){
            ExceptionUtil.throwBusinessException("入口参数为空");
        }
        if(objVo instanceof Map){
            if(MapUtil.isEmpty((Map) objVo)){
                ExceptionUtil.throwBusinessException("入口参数为空");
            }
            if(attrs!=null) {
                for(String key : attrs) {
                    Object obj = ((Map) objVo).get(key);
                    if (obj == null) {
                        ExceptionUtil.throwBusinessException("参数为空");
                    }
                    if ((obj instanceof List)) {
                        if (((List) obj).size() == 0) {
                            ExceptionUtil.throwBusinessException("参数列表为空");
                        }
                    }
                }
            }
        }
        else if(attrs!=null) {
            for (String attr : attrs) {
                if (Objects.isNull(getFieldValue(objVo, attr))) {
                    ExceptionUtil.throwBusinessException("参数为空");
                }
            }
        }
    }
    /*
     * 传入多个参数不能所有都为空
     * 假如有一个不为空，就代表正确，不抛出异常
     * */
    public static void notAllNull(Object objVo,String... attrs) throws Exception {
        if(Objects.isNull(objVo)){
            ExceptionUtil.throwBusinessException("入口参数为空");
        }
        Boolean flag=true;
        if(objVo instanceof Map){
            if(MapUtil.isEmpty((Map) objVo)){
                ExceptionUtil.throwBusinessException("入口参数为空");
            }
            if(attrs!=null) {
                for(String key : attrs) {
                    Object obj = ((Map) objVo).get(key);
                    if (!Objects.isNull(obj)) {
                        flag=false;
                    }
                    if ((obj instanceof List)) {
                        if (!(((List) obj).size() == 0)) {
                            flag=false;
                        }
                    }
                }
            }
        }
        else if(attrs!=null) {
            for (String attr : attrs) {
                if (!Objects.isNull(getFieldValue(objVo, attr))) {
                    flag=false;
                }
            }
        }
        if(flag){
            ExceptionUtil.throwBusinessException("参数为空");
        }
    }
    private static Object getFieldValue(Object obj, String field) throws Exception {
        Class<?> claz = obj.getClass();
        Field f = null;
        Object fieldValue = null;
        try {
            f = claz.getDeclaredField(field);
            f.setAccessible(true);
            fieldValue = f.get(obj);
        } catch (Exception e) {
            ExceptionUtil.throwBusinessException("参数为空");
        }
        return fieldValue;
    }
}
