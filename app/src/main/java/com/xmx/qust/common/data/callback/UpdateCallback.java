package com.xmx.qust.common.data.callback;

import com.avos.avoscloud.AVException;
import com.xmx.qust.common.user.UserData;

/**
 * Created by The_onE on 2016/5/29.
 */
public abstract class UpdateCallback {

    public abstract void success(UserData user);

    public abstract void syncError(int error);

    public abstract void syncError(AVException e);
}
