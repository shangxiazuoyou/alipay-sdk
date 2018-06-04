package com.shangxiazuoyou.alipaysdk;

public class Constants {

    public static final int NO_OR_LOW_VISION = 1;       //未安装支付宝或支付宝版本过低
    public static final int ERROR_RESULT = 2;           //支付结果解析错误
    public static final int ERROR_PAY = 3;              //支付失败
    public static final int ERROR_NETWORK = 4;          //网络连接错误
    public static final int ERROR_REPEAT = 5;           //重复请求
    public static final int ERROR_DEALING = 6;           //正在处理结果中

    public static final int ALIPAY_RESULT_STATUS_4000 = 4000;//订单支付失败
    public static final int ALIPAY_RESULT_STATUS_5000 = 5000;//重复请求
    public static final int ALIPAY_RESULT_STATUS_6001 = 6001;//用户中途取消
    public static final int ALIPAY_RESULT_STATUS_6002 = 6002;//网络连接出错
    public static final int ALIPAY_RESULT_STATUS_6004 = 6004;//支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    public static final int ALIPAY_RESULT_STATUS_8000 = 8000;//正在处理中，支付结果未知（有可能已经支付成功），请查询商户订单列表中订单的支付状态
    public static final int ALIPAY_RESULT_STATUS_9000 = 9000;//订单支付成功
}
