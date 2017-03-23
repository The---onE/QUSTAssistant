package com.xmx.qust.module.odd;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.xmx.qust.common.data.DataConstants;
import com.xmx.qust.common.data.callback.SelectCallback;
import com.xmx.qust.common.data.cloud.BaseCloudEntityManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by The_onE on 2017/3/20.
 */

public class OddJobEntityManager extends BaseCloudEntityManager<OddJob> {
    private static OddJobEntityManager instance;

    public synchronized static OddJobEntityManager getInstance() {
        if (null == instance) {
            instance = new OddJobEntityManager();
        }
        return instance;
    }

    private OddJobEntityManager() {
        tableName = "OddJob";
        entityTemplate = new OddJob();
        userField = "User";
    }

    // 查找所有未删除记录，type 【-1:id为发起人 -2:id为接受人 正数:按类型查找,id为空】
    public void selectAllByType(int type, String id, final SelectCallback<OddJob> callback) {
        Map<String, Object> con = new HashMap<>();
        switch (type) {
            case -1:
                con.put("requester", id);
                break;
            case -2:
                con.put("respondent", id);
                break;
            default:
                con.put("type", type);
                break;
        }

        if (!checkDatabase()) {
            callback.syncError(DataConstants.NOT_INIT);
            return;
        }
        AVQuery<AVObject> query = new AVQuery<>(tableName);
        for (String key : con.keySet()) {
            query.whereEqualTo(key, con.get(key));
        }
        query.whereNotEqualTo("status", OddJobConstants.STATUS_DELETED);
        query.orderByDescending("time");
        query.findInBackground(new FindCallback<AVObject>() {
            public void done(List<AVObject> avObjects, AVException e) {
                if (e == null) {
                    List<OddJob> entities = new ArrayList<>();
                    for (AVObject object : avObjects) {
                        OddJob entity = entityTemplate.convertToEntity(object);
                        entities.add(entity);
                    }
                    callback.success(entities);
                } else {
                    callback.syncError(e);
                }
            }
        });

    }
}
