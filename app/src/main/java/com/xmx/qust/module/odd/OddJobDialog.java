package com.xmx.qust.module.odd;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.avos.avoscloud.AVException;
import com.xmx.qust.R;
import com.xmx.qust.common.data.callback.InsertCallback;
import com.xmx.qust.common.map.amap.utils.ToastUtil;
import com.xmx.qust.common.user.UserData;
import com.xmx.qust.utils.ExceptionUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

/**
 * Created by The_onE on 2017/2/28.
 * 添加杂务对话框
 */
public class OddJobDialog extends DialogFragment {
    Context mContext;
    String mTitle;
    String mStart;
    String mEnd;
    String mContent;
    int mType;
    String mUserId;
    String mNickname;

    public void initCreateDialog(Context context, int type, String userId, String nickname) {
        mContext = context;
        mType = type;
        mUserId = userId;
        mNickname = nickname;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_odd_job, container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 不显示默认标题栏
        getDialog().requestWindowFeature(STYLE_NO_TITLE);

        final EditText editTitle = (EditText) view.findViewById(R.id.editTitle);
        final EditText editStart = (EditText) view.findViewById(R.id.editStart);
        final EditText editEnd = (EditText) view.findViewById(R.id.editEnd);
        final EditText editContent = (EditText) view.findViewById(R.id.editContent);

        // 确认
        view.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTitle.getText().toString();
                String start = editStart.getText().toString();
                String end = editEnd.getText().toString();
                String content = editContent.getText().toString();
                // 非空校验
                if (title.length() == 0 || start.length() == 0 ||
                        end.length() == 0 || content.length() == 0) {
                    ToastUtil.show(mContext, "请将信息填写完整");
                    return;
                }
                // 生成杂务
                final OddJob job = new OddJob();
                job.mTitle = title;
                job.mContent = content;
                job.mStart = start;
                job.mEnd = end;
                job.mStatus = 1;
                job.mType = mType;
                job.mTime = new Date();
                job.mRequesterId = mUserId;
                job.mRequesterName = mNickname;

                // 添加杂务
                OddJobEntityManager.getInstance().insertToCloud(job, new InsertCallback() {
                    @Override
                    public void success(UserData user, String objectId) {
                        // 添加成功
                        ToastUtil.show(mContext, "添加成功");
                        job.mCloudId = objectId;
                        EventBus.getDefault().post(new ChangeListEvent());
                        dismiss();
                    }

                    @Override
                    public void syncError(int error) {
                        OddJobEntityManager.defaultError(error, mContext);
                    }

                    @Override
                    public void syncError(AVException e) {
                        ToastUtil.show(mContext, "添加失败");
                        ExceptionUtil.normalException(e, mContext);
                    }
                });
            }
        });
        // 取消
        view.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}
