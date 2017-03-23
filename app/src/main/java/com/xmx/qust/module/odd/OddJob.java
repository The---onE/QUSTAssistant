package com.xmx.qust.module.odd;

import com.avos.avoscloud.AVObject;
import com.xmx.qust.common.data.cloud.ICloudEntity;

import java.util.Date;

/**
 * Created by The_onE on 2017/3/19.
 */

public class OddJob implements ICloudEntity {

    public String mCloudId;
    public String mRequesterId = ""; // 发起人
    public String mRequesterName = ""; // 发起人昵称
    public String mRespondentId = ""; // 接受人
    public String mRespondentName = ""; // 接受人昵称
    public String mTitle; // 标题
    public String mContent; // 内容
    public String mStart; // 起点
    public String mEnd; // 终点
    public double mStartLatitude = -1000; // 起点纬度
    public double mStartLongitude = -1000; // 起点经度
    public double mEndLatitude = -1000; // 终点纬度
    public double mEndLongitude = -1000; // 终点经度
    public int mStatus; // 状态，1为可用，0为已接受，负数为冲突，65535为已完成，-65535为删除
    public int mType; // 类型
    public Date mTime; // 时间

    @Override
    public AVObject getContent(String tableName) {
        AVObject object = new AVObject(tableName);
        if (mCloudId != null) {
            object.setObjectId(mCloudId);
        }
        object.put("requester", mRequesterId);
        object.put("requesterName", mRequesterName);
        object.put("respondent", mRespondentId);
        object.put("respondentName", mRespondentName);
        object.put("title", mTitle);
        object.put("content", mContent);
        object.put("start", mStart);
        object.put("end", mEnd);
        object.put("sLatitude", mStartLatitude);
        object.put("sLongitude", mStartLongitude);
        object.put("eLatitude", mEndLatitude);
        object.put("eLongitude", mEndLongitude);
        object.put("status", mStatus);
        object.put("type", mType);
        object.put("time", mTime);
        return object;
    }

    @Override
    public OddJob convertToEntity(AVObject object) {
        OddJob job = new OddJob();
        job.mCloudId = object.getObjectId();
        job.mRequesterId = object.getString("requester");
        job.mRequesterName = object.getString("requesterName");
        job.mRespondentId = object.getString("respondent");
        job.mRespondentName = object.getString("respondentName");
        job.mTitle = object.getString("title");
        job.mContent = object.getString("content");
        job.mStart = object.getString("start");
        job.mEnd = object.getString("end");
        job.mStartLatitude = object.getDouble("sLatitude");
        job.mStartLongitude = object.getDouble("sLongitude");
        job.mEndLatitude = object.getDouble("eLatitude");
        job.mEndLongitude = object.getDouble("eLongitude");
        job.mStatus = object.getInt("status");
        job.mType = object.getInt("type");
        job.mTime = object.getDate("time");
        return job;
    }
}
