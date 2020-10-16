package com.jingyou.utils;

public class CustomException extends Exception{

    /** 异常状态码 */
    private Integer code;

    /** 是否提示cause的内容 */
    private boolean showCause;

    /** 附加信息 */
    private String extra;

    /** 禁止使用 */
    private CustomException(){}

    /**
     * 当构造ExternalInterfaceException类型异常
     * 不可以使用该构造方法
     * @param message 异常信息
     */
    protected CustomException(String message){
        super(message);
    }

    /**
     * @param message 异常信息
     * @param showCause 是否提示message内容
     */
    protected CustomException(String message,boolean showCause){
        super(message);
        this.showCause = showCause;
    }

    /**
     * @param code 错误码
     * @param message 异常信息
     */
    protected CustomException(Integer code,String message){
        super(message);
        this.code = code;
    }

    /**
     * @param code 错误码
     * @param message 异常信息
     * @param showCause 是否提示message内容
     */
    protected CustomException(Integer code,String message,boolean showCause){
        super(message);
        this.code = code;
        this.showCause = showCause;
    }

    /**
     * @param message 异常信息
     * @param extra 附加信息
     */
    protected CustomException(String message,String extra){
        super(message);
        this.extra = extra;
    }

    /**
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected CustomException(String message,boolean showCause,String extra){
        super(message);
        this.showCause = showCause;
        this.extra = extra;
    }

    /**
     * @param code 错误码
     * @param message 异常信息
     * @param extra 附加信息
     */
    protected CustomException(Integer code,String message,String extra){
        super(message);
        this.code = code;
        this.extra = extra;
    }

    /**
     * @param code 错误码
     * @param message 异常信息
     * @param showCause 是否提示message内容
     * @param extra 附加信息
     */
    protected CustomException(Integer code,String message,boolean showCause,String extra){
        super(message);
        this.code = code;
        this.showCause = showCause;
        this.extra = extra;
    }

    /** getter/setter */
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public boolean getShowCause() {
        return showCause;
    }

    public void setShowCause(boolean showCause) {
        this.showCause = showCause;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}
