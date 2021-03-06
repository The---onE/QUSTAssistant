package com.xmx.qust.module.web;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.xmx.qust.R;
import com.xmx.qust.base.activity.BaseTempActivity;
import com.xmx.qust.common.web.BaseWebChromeClient;
import com.xmx.qust.common.web.BaseWebViewClient;

/**
 * Created by The_onE on 2017/2/17.
 * 打开QUST认证页
 */
public class WebPageActivity extends BaseTempActivity {
    WebView webBrowser;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web_page);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        webBrowser = getViewById(R.id.webBrowser);

        // 允许JS执行
        webBrowser.getSettings().setJavaScriptEnabled(true);

        // 设置自定义浏览器属性(对不同协议的URL分别处理)
        webBrowser.setWebViewClient(new BaseWebViewClient());
        // 设置自定义页面事件处理(alert,prompt等页面事件)
        webBrowser.setWebChromeClient(new BaseWebChromeClient() {
            @Override
            public void onAlert(String message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebPageActivity.this);
                builder.setMessage(message)
                        .setTitle("提示")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .show();
            }

            @Override
            public void onConfirm(String message, final JsResult result) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WebPageActivity.this);
                builder.setMessage(message)
                        .setTitle("提示")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.confirm();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                result.cancel();
                            }
                        })
                        .show();
            }
        });

        WebSettings settings = webBrowser.getSettings();
        // 设置可以支持缩放
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置可在大视野范围内上下左右拖动，并且可以任意比例缩放
        settings.setUseWideViewPort(true);
        //设置默认加载的可视范围是大视野范围
        settings.setLoadWithOverviewMode(true);

        String url = getIntent().getStringExtra("url");
        // 打开网络网页
        webBrowser.loadUrl(url);
    }

    @Override
    protected void setActionBar(ActionBar actionBar, Toolbar toolbar) {
        actionBar.setDisplayHomeAsUpEnabled(true);
        // 点击返回按钮直接关闭
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // 重写返回键事件，后退到上一个网页
    @Override
    public void onBackPressed() {
        if (webBrowser.canGoBack()) {
            webBrowser.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
