package com.zhangwuji.api.Enum;

public enum ResponseCodeEnum {

    SUCCESS(10000, "成功"),

    SYSTEM_ERROR(20000, "系统异常"),

    UNKNOWN_ERROR(20001, "未知错误"),

    PARAMS_ERROR(20002, "参数错误"),

    NO_DATA(30000, "没有数据"),

    EXISTED_DATA(30001, "数据已存在"),

    LOGIN_EXPIRES(40000, "登录状态过期"),

    NO_PERMISSION(40001, "没有权限"),

    ERROR_SMSCODE(400004, "验证码错误"),

    NO_ACTIVITY(50000, "活动不存在"),

    ACTIVITY_END(50001, "活动已结束"),

    ACTIVITY_NOT_BEGIN(50002, "活动未开始"),

    ACTIVITY_ERROR(50003, "活动未开始/已结束"),

    HAVE_SIGNED(50004, "当前已签到不能重复签到"),

    ACTIVITY_NOT_EXIT(50005, "活动不存在"),

    GET_TIMES_LIMIT(50006, "领取已达上限，不能再领取");


    private int code;

    private String msg;

    ResponseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
