package com.xmx.qust.module.odd;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.avos.avoscloud.AVException;
import com.xmx.qust.R;
import com.xmx.qust.base.activity.BaseTempActivity;
import com.xmx.qust.common.data.callback.SelectCallback;
import com.xmx.qust.common.user.UserData;
import com.xmx.qust.common.user.UserManager;
import com.xmx.qust.common.user.callback.AutoLoginCallback;
import com.xmx.qust.utils.ExceptionUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class OddJobListActivity extends BaseTempActivity {

    private String mUserId;
    private int mType;
    private boolean mMustLogin;
    private ListView mOddJobList;
    private OddJobAdapter mAdapter;

    SelectCallback<OddJob> callback = new SelectCallback<OddJob>() {
        @Override
        public void success(List<OddJob> oddJobs) {
            if (mAdapter != null) {
                mAdapter.updateList(oddJobs);
            }
        }

        @Override
        public void syncError(int error) {
            OddJobEntityManager.defaultError(error, getBaseContext());
        }

        @Override
        public void syncError(AVException e) {
            showToast("网络连接错误");
            ExceptionUtil.normalException(e, getBaseContext());
        }
    };

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_odd_job_list);
        // 注册EventBus
        EventBus.getDefault().register(this);

        mOddJobList = getViewById(R.id.listOddJob);

        mType = getIntent().getIntExtra("type", 0);
        mMustLogin = getIntent().getBooleanExtra("must-login", true);

        UserManager.getInstance().checkLogin(new AutoLoginCallback() {
            @Override
            public void success(UserData user) {
                mUserId = user.objectId;
                mAdapter = new OddJobAdapter(getBaseContext(), new ArrayList<OddJob>(), mUserId);
                mOddJobList.setAdapter(mAdapter);
                OddJobEntityManager.getInstance().selectAllByType(mType, mUserId, callback);
            }

            @Override
            public void error(int error) {
                if (mMustLogin) {
                    showToast("请先登录");
                    finish();
                } else {
                    mAdapter = new OddJobAdapter(getBaseContext(), new ArrayList<OddJob>(), null);
                    mOddJobList.setAdapter(mAdapter);
                    OddJobEntityManager.getInstance().selectAllByType(mType, null, callback);
                }
            }

            @Override
            public void error(AVException e) {
                showToast("网络连接错误");
                ExceptionUtil.normalException(e, getBaseContext());
            }
        });
    }

    @Override
    protected void setListener() {
        getViewById(R.id.btnAddOddJob).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserManager.getInstance().checkLogin(new AutoLoginCallback() {
                    @Override
                    public void success(UserData user) {
                        OddJobDialog dialog = new OddJobDialog();
                        dialog.initCreateDialog(OddJobListActivity.this, mType,
                                user.objectId, user.nickname);
                        dialog.show(getFragmentManager(), "ObbJob");
                    }

                    @Override
                    public void error(int error) {
                        showToast("请先登录");
                    }

                    @Override
                    public void error(AVException e) {
                        showToast("网络连接错误");
                        ExceptionUtil.normalException(e, getBaseContext());
                    }
                });
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Subscribe
    public void onEventMainThread(ChangeListEvent event) {
        UserManager.getInstance().checkLogin(new AutoLoginCallback() {
            @Override
            public void success(UserData user) {
                mUserId = user.objectId;
                mAdapter = new OddJobAdapter(getBaseContext(), new ArrayList<OddJob>(), mUserId);
                mOddJobList.setAdapter(mAdapter);
                OddJobEntityManager.getInstance().selectAllByType(mType, mUserId, callback);
            }

            @Override
            public void error(int error) {
                if (mMustLogin) {
                    finish();
                } else {
                    mAdapter = new OddJobAdapter(getBaseContext(), new ArrayList<OddJob>(), null);
                    mOddJobList.setAdapter(mAdapter);
                    OddJobEntityManager.getInstance().selectAllByType(mType, null, callback);
                }
            }

            @Override
            public void error(AVException e) {
                showToast("网络连接错误");
                ExceptionUtil.normalException(e, getBaseContext());
            }
        });
    }
}
