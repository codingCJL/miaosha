package com.cjl.miaosha.result;

/**
 * @author cjl
 * @date 2020/3/24 14:38
 */
public class CodeMsg {
    private Integer code;
    private String msg;

    //通用异常
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    //登录模块 5002XX
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500212, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500213, "手机号格式不正确");
    public static CodeMsg USER_NULL = new CodeMsg(500214, "用户不存在");
    public static CodeMsg USER_EXITS= new CodeMsg(500215, "用户已存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500216, "密码错误");
    //商品模块 5003XX

    //订单模块 5004XX

    //秒杀模块 5005XX


    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object args){
        int code=this.code;
        String message=String.format(this.msg,args);
        return new CodeMsg(code,message);
    }

    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
