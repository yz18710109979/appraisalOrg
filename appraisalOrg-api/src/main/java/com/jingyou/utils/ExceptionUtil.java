package com.jingyou.utils;

import java.lang.reflect.Constructor;
import java.util.Objects;

/**
 * @author yangzhi
 * @create 2020/10/16
 */
public final class ExceptionUtil {

    /** 私有构造函数 */
    private ExceptionUtil(){}

    /**
     * 抛出自定义异常,仅适用只提供异常信息[message]
     * 该方法建议抛出BusinessException异常使用
     * @param exClass 自定义异常Class
     * @param message 异常信息
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, String message) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        if(exClass.getSimpleName().equals(ExternalInterfaceException.class.getSimpleName())){
            throw new BusinessException("异常类型不可以是[ExternalInterfaceException]");
        }
        notClass(exClass);
        notEmpty(message, "自定义异常信息[message]不能为空");
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(String.class);
        throw (CustomException)exceptionConstrustor.newInstance(message);
    }

    /**
     * 抛出自定义异常,仅适用只提供异常信息[message]
     * 该方法建议抛出BusinessException异常使用
     * @param exClass 自定义异常Class
     * @param message 异常信息
     * @param showCause message内容是否提示给客户端
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, String message,boolean showCause) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notEmpty(message, "自定义异常[message]不能为空");
        notNull(showCause,"是否提示标识[showCause]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(String.class,boolean.class);
        throw (CustomException)exceptionConstrustor.newInstance(message,showCause);
    }

    /**
     * 抛出自定义异常,只提供错误码和异常信息[code,message]
     * 该方法建议在服务之间调用或第三方平台交互时
     * 抛出ExternalInterfaceException使用
     * @param exClass 自定义异常Class
     * @param code 异常码
     * @param message 异常信息
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, Integer code, String message) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notNull(code, "自定义异常信息[code]不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(Integer.class,String.class);
        throw (CustomException)exceptionConstrustor.newInstance(code,message);
    }

    /**
     * 抛出自定义异常,只提供错误码和异常信息[code,message]
     * 该方法建议在服务之间调用或第三方平台交互时
     * 抛出ExternalInterfaceException使用
     * @param exClass 自定义异常Class
     * @param code 异常码
     * @param message 异常信息
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, Integer code, String message,boolean showCause) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notNull(code, "自定义异常信息[code]不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notNull(showCause,"是否提示标识[showCause]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(Integer.class,String.class,boolean.class);
        throw (CustomException)exceptionConstrustor.newInstance(code,message,showCause);
    }

    /**
     * 抛出自定义异常,只提供异常信息和附加信息[message,extra]
     * 该方法建议抛出BusinessException异常使用
     * @param exClass 自定义异常Class
     * @param message 异常信息
     * @param extra 附加信息(会返回给客户端)
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, String message, String extra) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notNull(extra, "附加信息[extra]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(String.class,String.class);
        throw (CustomException)exceptionConstrustor.newInstance(message,extra);
    }

    /**
     * 抛出自定义异常,只提供异常信息和附加信息[message,extra]
     * 该方法建议抛出BusinessException异常使用
     * @param exClass 自定义异常Class
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息(会返回给客户端)
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, String message,boolean showCause,String extra) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notNull(showCause,"是否提示标识[showCause]不能为空");
        notNull(extra, "附加信息[extra]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(String.class,boolean.class,String.class);
        throw (CustomException)exceptionConstrustor.newInstance(message,showCause,extra);
    }

    /**
     * 抛出自定义异常,提供错误码、异常信息、附加信息[code,message,extra]
     * 该方法建议在服务之间调用或第三方平台交互时
     * 抛出ExternalInterfaceException使用
     * @param exClass 自定义异常Class
     * @param code 异常码
     * @param message 异常信息
     * @param extra 附加信息(会返回给客户端)
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, Integer code, String message, String extra) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notNull(code, "自定义异常信息[code]不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notNull(extra, "附加信息[extra]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(Integer.class,String.class,String.class);
        throw (CustomException)exceptionConstrustor.newInstance(code,message,extra);
    }

    /**
     * 抛出自定义异常,提供错误码、异常信息、附加信息[code,message,extra]
     * 该方法建议在服务之间调用或第三方平台交互时
     * 抛出ExternalInterfaceException使用
     * @param exClass 自定义异常Class
     * @param code 异常码
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息(会返回给客户端)
     * @throws Exception
     */
    public static void throwException(Class<? extends CustomException> exClass, Integer code, String message,boolean showCause,String extra) throws Exception {
        notNull(exClass, "自定义异常[Class]参数不能为空");
        notNull(code, "自定义异常信息[code]不能为空");
        notEmpty(message, "自定义异常信息[message]不能为空");
        notNull(showCause,"是否提示标识[showCause]不能为空");
        notNull(extra, "附加信息[extra]不能为空");
        notClass(exClass);
        /** 调用构造函数 */
        Constructor exceptionConstrustor = exClass.getDeclaredConstructor(Integer.class,String.class,boolean.class,String.class);
        throw (CustomException)exceptionConstrustor.newInstance(code,message,showCause,extra);
    }

    public static void throwBusinessException(String message) throws Exception {
        throwException(BusinessException.class,message);
    }

    public static void throwBusinessException(String message,boolean showCause) throws Exception {
        throwException(BusinessException.class,message,showCause);
    }

    public static void throwBusinessException(String message,boolean showCause,String extra) throws Exception {
        throwException(BusinessException.class,message,showCause,extra);
    }

    public static void throwBusinessException(String message,String extra) throws Exception {
        throwException(BusinessException.class,message,extra);
    }

    /**
     * 对象不能为NULL
     * @param obj 目标对象
     * @param message 异常信息
     */
    private static void notNull(Object obj, String message){
        Objects.requireNonNull(obj,message);
    }

    /**
     * 字符串不能是空格
     * @param text 字符串
     * @param message 异常信息
     */
    private static void notEmpty(String text, String message){
//        if(StringUtils.isEmpty(text.trim())){
//            throw new NullPointerException(message);
//        }
    }

    private static boolean notClass(Class<?> clazz) throws Exception {
        String name = CustomException.class.getSimpleName();
        boolean flag = !Objects.equals(clazz.getSimpleName(),CustomException.class.getSimpleName());
        if(!flag){
            throw new BusinessException("异常类型不可以是 [" + name + "]");
        }
        return flag;
    }
}
