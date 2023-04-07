package com.zjnbit.store.framework.web.model;


/**
 * @auther: chenjy
 * @emp: chenjy
 * @mobile: 18158571991
 * @date: 2021/11/11 13:33
 * @Description:
 **/
public enum RequestError implements BaseError {

    A0001("A0001", "用户端错误"),
    A0002("A0002", "ID资源未找到"),
    A0003("A0003", "参数错误"),
    A0004("A0004", "资源重复"),
    A0005("A0005", "父节点资源未找到"),
    A0103("A0103", "密码格式错误，最少8位英文加数字"),
    A0104("A0104", "不被支持的文件格式"),
    A1101("A1101", "用户名已被使用"),
    A1102("A1102", "邮箱已被使用"),
    A1103("A1103", "验证码错误"),
    A1104("A1103", "验证码发送过于频繁"),
    A0200("A0200", "账户或密码错误"),
    A0201("A0201", "不被支持的登录方式"),
    A0202("A0202", "账户未注册"),
    A0203("A0203", "密码不正确"),
    A0204("A0204", "无效的token"),
    A0205("A0205", "token已过期"),
    A0206("A0206", "无访问权限"),
    B0001("B0001", "字典未找到"),
    B0002("B0002", "字典组未找到"),
    B0500("B0500", "系统错误"),
    B0400("B0400", "请求参数有误"),
    B0405("B0405", "不被支持的请求方式"),
    B0415("B0415", "错误的请求编码"),
    C0001("C0001", "第三方接口调用失败"),
    C0002("C0002", "系统未实现"),
    C0101("C0101", "文件上传失败"),
    D0101("D0101", "系统参数配置错误:过滤规则错误"),
    ;

    final String errCode;
    final String errMsg;


    RequestError(String errorCode, String errorMsg) {
        this.errCode = errorCode;
        this.errMsg = errorMsg;
    }

    @Override
    public String getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }
}
