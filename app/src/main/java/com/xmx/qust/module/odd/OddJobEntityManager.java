package com.xmx.qust.module.odd;

import com.xmx.qust.common.data.callback.SelectCallback;
import com.xmx.qust.common.data.cloud.BaseCloudEntityManager;

import java.util.HashMap;
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

    // 查找，type 【-1:id为发起人 -2:id为接受人 正数:按类型查找,id为空】
    public void selectAllByType(int type, String id, SelectCallback<OddJob> callback) {
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
        selectByCondition(con, "time", false, callback);
    }
}
