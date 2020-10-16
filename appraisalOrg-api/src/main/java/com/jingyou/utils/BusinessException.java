package com.jingyou.utils;

/**
 * @author yangzhi
 * @create 2020/10/16
 */
public class BusinessException extends CustomException{

    /**
     * 错误码为-1
     * 不提示message内容
     * 附加信息extra为空
     * @param message 异常信息
     */
    protected BusinessException(String message){
        super(message);
    }

    /**
     * 错误码为-1
     * 可选择是否提示message内容
     * 附加信息extra为空
     * @param message 异常信息
     * @param showCause 是否提示message内容
     */
    protected BusinessException(String message,boolean showCause){
        super(message,showCause);
    }

    /**
     * 错误码被设置后无效(全局异常处理器统一将BusinessException类型的code设置为-1)
     * 不提示message内容
     * 附加信息extra为空
     * @param code 错误码
     * @param message 异常信息
     */
    protected BusinessException(Integer code,String message){
        super(code,message);
    }

    /**
     * 错误码被设置后无效(全局异常处理器统一将BusinessException类型的code设置为-1)
     * @param code 错误码
     * @param message 异常信息
     * @param showCause	是否提示message内容
     */
    protected BusinessException(Integer code,String message,boolean showCause){
        super(code,message,showCause);
    }

    /**
     * 错误码为-1
     * 不提示message内容
     * @param message 异常信息
     * @param extra	附加信息
     */
    protected BusinessException(String message,String extra){
        super(message,extra);
    }

    /**
     * 错误码为-1
     * 可选择是否提示message内容
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected BusinessException(String message,boolean showCause,String extra){
        super(message,showCause,extra);
    }

    /**
     * 错误码被设置后无效(全局异常处理器统一将BusinessException类型的code设置为-1)
     * 不提示message内容
     * @param code 错误码
     * @param message 异常信息
     * @param extra 附加信息
     */
    protected BusinessException(Integer code,String message,String extra){
        super(code,message,extra);
    }

    /**
     * 错误码被设置后无效(全局异常处理器统一将BusinessException类型的code设置为-1)
     * 可选择是否提示message内容
     * @param code 错误码
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected BusinessException(Integer code,String message,boolean showCause,String extra){
        super(code,message,showCause,extra);
    }

    /** 避免JVM构造运行时异常堆栈 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
