package com.app.common.network.help;

/**
 * Created by 26050 on 2017/12/8.
 * QinQin
 */

public class CodeException {
    //HTTP错误
    public static final int BAD_NETWORK = 0x1;
    //连接错误
    public static final int CONNECT_ERROR = 0x2;
    //连接超时
    public static final int CONNECT_TIMEOUT = 0x3;
    //解析错误
    public static final int PARSE_ERROR = 0x4;
    //未知错误
    public static final int UNKNOWN_ERROR = 0x5;
}
