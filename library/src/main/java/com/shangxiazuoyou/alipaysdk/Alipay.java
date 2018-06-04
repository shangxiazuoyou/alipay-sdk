package com.shangxiazuoyou.alipaysdk;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;

import java.util.Map;

/**
 * 支付宝支付
 */
public class Alipay {

    private static Alipay mAlipay;
    private PayTask mPayTask;

    private Alipay(Context context) {
        mPayTask = new PayTask((Activity) context);
    }

    public static void init(Context context) {
        if (null == mAlipay) {
            mAlipay = new Alipay(context);
        }
    }

    public static Alipay getInstance() {
        return mAlipay;
    }

    public void doPay(Context context, final String payParams, boolean isSupportH5, final AlipayResultCallback callback) {
        if (!isSupportH5) {
            if (!isSupportAlipay(context)) {
                callback.onError(Constants.NO_OR_LOW_VISION);
                return;
            }
        }
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Map<String, String> payResult = mPayTask.payV2(payParams, true);
                if (callback == null) {
                    return;
                }
                if (payResult == null) {
                    callback.onError(Constants.ERROR_RESULT);
                    return;
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        for (Map.Entry<String, String> entry : payResult.entrySet()) {
                            if (!TextUtils.isEmpty(entry.getValue())) {
                                if ("resultStatus".equals(entry.getKey())) {
                                    if (Constants.ALIPAY_RESULT_STATUS_9000 == Integer.parseInt(entry.getValue())) {
                                        callback.onSuccess();
                                    } else if (Constants.ALIPAY_RESULT_STATUS_8000 == Integer.parseInt(entry.getValue())) {
                                        callback.onError(Constants.ERROR_DEALING);
                                    } else if (Constants.ALIPAY_RESULT_STATUS_6001 == Integer.parseInt(entry.getValue())) {
                                        callback.onCancel();
                                    } else if (Constants.ALIPAY_RESULT_STATUS_6002 == Integer.parseInt(entry.getValue())) {
                                        callback.onError(Constants.ERROR_NETWORK);
                                    } else if (Constants.ALIPAY_RESULT_STATUS_4000 == Integer.parseInt(entry.getValue())) {
                                        callback.onError(Constants.ERROR_PAY);
                                    } else if (Constants.ALIPAY_RESULT_STATUS_5000 == Integer.parseInt(entry.getValue())) {
                                        callback.onError(Constants.ERROR_REPEAT);
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }).start();
    }

    private boolean isSupportAlipay(Context context) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(context.getPackageManager());
        return componentName != null;
    }
}
