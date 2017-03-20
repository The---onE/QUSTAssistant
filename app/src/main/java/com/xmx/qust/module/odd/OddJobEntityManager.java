package com.xmx.qust.module.odd;

import com.xmx.qust.common.data.cloud.BaseCloudEntityManager;

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
}
