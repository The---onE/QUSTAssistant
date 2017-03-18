package com.xmx.qust.common.web;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by The_onE on 2017/2/18.
 * 自定义页面事件处理(alert,prompt等页面事件)
 * 辅助处理对话框,网站图标,网站标题等
 */
public abstract class BaseWebChromeClient extends WebChromeClient {
    /**
     * 当网页弹出alert消息框时的处理接口
     *
     * @param message 网页alert的内容
     */
    public abstract void onAlert(String message);

    public abstract void onConfirm(String message, JsResult result);

    @Override
    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        onAlert(message);
        // 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
        result.confirm();
        return true;
    }

    @Override
    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        onConfirm(message, result);
        return true;
    }
}