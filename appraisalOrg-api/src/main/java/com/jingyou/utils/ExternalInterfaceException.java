package com.jingyou.utils;

/**
 *  自定义外部接口交互异常类
 * 内部服务之间交互或与第三方接口交互使用
 * controller、service层代码如果抛出自定义异常
 * 则controller、service层的相关方法需要 throws Exception
 * service层逻辑代码中通过 ExceptionUtil.throwException(...)方式抛出
 * @see ExceptionUtil
 */
public class ExternalInterfaceException extends CustomException{

    /** 禁止使用,必须提供错误码 */
    private ExternalInterfaceException(String message){
        super(message);
    }

    /**
     * 错误码为-1
     * 可选择是否提示message内容
     * 附加信息extra为空
     * @param message 异常信息
     * @param showCause 是否提示message内容
     */
    protected ExternalInterfaceException(String message,boolean showCause){
        super(message,showCause);
    }

    /**
     * 不提示message内容
     * 附加信息extra为空
     * @param code 错误码
     * @param message 异常信息
     */
    protected ExternalInterfaceException(Integer code,String message){
        super(code,message);
    }

    /**
     * @param code 错误码
     * @param message 异常信息
     * @param showCause	是否提示message内容
     */
    protected ExternalInterfaceException(Integer code,String message,boolean showCause){
        super(code,message,showCause);
    }

    /**
     * 错误码为-1
     * 不提示message内容
     * @param message 异常信息
     * @param extra	附加信息
     */
    protected ExternalInterfaceException(String message,String extra){
        super(message,extra);
    }

    /**
     * 错误码为-1
     * 可选择是否提示message内容
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected ExternalInterfaceException(String message,boolean showCause,String extra){
        super(message,showCause,extra);
    }

    /**
     * 不提示message内容
     * @param code 错误码
     * @param message 异常信息
     * @param extra 附加信息
     */
    protected ExternalInterfaceException(Integer code,String message,String extra){
        super(code,message,extra);
    }

    /**
     * 可选择是否提示message内容
     * @param code 错误码
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected ExternalInterfaceException(Integer code,String message,boolean showCause,String extra){
        super(code,message,showCause,extra);
    }

    /** 避免JVM构造运行时异常堆栈 */
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
