package com.xmx.qust.module.user;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.xmx.qust.R;
import com.xmx.qust.base.activity.BaseTempActivity;
import com.xmx.qust.common.user.UserConstants;
import com.xmx.qust.common.user.UserData;
import com.xmx.qust.common.user.UserManager;
import com.xmx.qust.common.user.callback.RegisterCallback;
import com.xmx.qust.utils.ExceptionUtil;

public class RegisterActivity extends BaseTempActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_register);
    }

    @Override
    protected void setListener() {
        final Button register = getViewById(R.id.register_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nn = getViewById(R.id.register_nickname);
                final String nickname = nn.getText().toString();
                EditText un = getViewById(R.id.register_username);
                final String username = un.getText().toString();
                EditText pw = getViewById(R.id.register_password);
                final String password = pw.getText().toString();
                EditText pw2 = getViewById(R.id.register_password2);
                String password2 = pw2.getText().toString();

                if (nickname.equals("")) {
                    showToast(R.string.nickname_hint);
                    return;
                }
                if (username.equals("")) {
                    showToast(R.string.username_empty);
                    return;
                }
                if (password.equals("")) {
                    showToast(R.string.password_empty);
                    return;
                }
                if (!password.equals(password2)) {
                    showToast(R.string.password_different);
                    return;
                }

                register.setEnabled(false);
                UserManager.getInstance().register(username, password, nickname, new RegisterCallback() {
                    @Override
                    public void success(UserData user) {
                        showToast(R.string.register_success);
                        setResult(RESULT_OK);
                        finish();
                    }

                    @Override
                    public void error(AVException e) {
                        showToast(R.string.network_error);
                        ExceptionUtil.normalException(e, getBaseContext());
                        register.setEnabled(true);
                    }

                    @Override
                    public void error(int error) {
                        switch (error) {
                            case UserConstants.USERNAME_EXIST:
                                showToast(R.string.username_exist);
                                register.setEnabled(true);
                                break;
                            case UserConstants.NICKNAME_EXIST:
                                showToast(R.string.nickname_exist);
                                register.setEnabled(true);
                                break;
                        }
                    }
                });
            }
        });

        getViewById(R.id.register_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void setActionBar(ActionBar actionBar, Toolbar toolbar) {
        super.setActionBar(actionBar, toolbar);
        actionBar.setTitle(R.string.register);
    }
}
