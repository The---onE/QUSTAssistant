package com.xmx.qust.common.data.sync;

import com.xmx.qust.common.data.cloud.ICloudEntity;
import com.xmx.qust.common.data.sql.ISQLEntity;

/**
 * Created by The_onE on 2016/5/29.
 */
public interface ISyncEntity extends ICloudEntity, ISQLEntity {
    String getCloudId();

    void setCloudId(String id);
}
