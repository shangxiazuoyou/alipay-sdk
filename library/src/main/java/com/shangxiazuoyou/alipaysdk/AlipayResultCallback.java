package com.shangxiazuoyou.alipaysdk;

/**
 * 支付宝支付结果回调
 */
public interface AlipayResultCallback {

    /**
     * 支付成功
     */
    void onSuccess();

    /**
     * 支付失败
     *
     * @param errorCode 错误码
     */
    void onError(int errorCode);

    /**
     * 支付取消
     */
    void onCancel();
}
