package com.xmx.qust.core.activity;

import android.os.Bundle;
import android.view.View;

import com.avos.avoscloud.AVException;
import com.xmx.qust.common.user.LoginEvent;
import com.xmx.qust.common.user.UserData;
import com.xmx.qust.common.user.UserManager;
import com.xmx.qust.common.user.callback.AutoLoginCallback;
import com.xmx.qust.core.Constants;
import com.xmx.qust.R;
import com.xmx.qust.base.activity.BaseSplashActivity;
import com.xmx.qust.utils.ExceptionUtil;
import com.xmx.qust.utils.Timer;

import org.greenrobot.eventbus.EventBus;

public class SplashActivity extends BaseSplashActivity {

    Timer timer;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void setListener() {
        getViewById(R.id.btn_skip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timer.stop();
                timer.execute();
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        timer = new Timer() {
            @Override
            public void timer() {
                startMainActivity();
            }
        };
        timer.start(Constants.SPLASH_TIME, true);
        UserManager.getInstance().autoLogin(new AutoLoginCallback() {
            @Override
            public void success(UserData user) {
                EventBus.getDefault().post(new LoginEvent());
            }

            @Override
            public void error(int error) {
            }

            @Override
            public void error(AVException e) {
                ExceptionUtil.normalException(e, getBaseContext());
            }
        });
    }
}