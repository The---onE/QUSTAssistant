package com.xmx.qust.module.odd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xmx.qust.R;
import com.xmx.qust.common.data.BaseEntityAdapter;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by xmx on 2017/3/21.
 */
public class OddJobAdapter extends BaseEntityAdapter<OddJob> {

    private String mUserId;

    static class ViewHolder {
        @ViewInject(R.id.tvTitle)
        TextView titleView;
        @ViewInject(R.id.tvStart)
        TextView startView;
        @ViewInject(R.id.tvEnd)
        TextView endView;
        @ViewInject(R.id.tvContent)
        TextView contentView;
        @ViewInject(R.id.tvTime)
        TextView timeView;
    }

    public OddJobAdapter(Context context, List<OddJob> data, String userId) {
        super(context, data);
        mUserId = userId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_odd_job, null);
            holder = new ViewHolder();
            x.view().inject(holder, convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (position < mData.size()) {
            OddJob job = mData.get(position);
            holder.titleView.setText(job.mTitle);
            holder.startView.setText("起点:" + job.mStart);
            holder.endView.setText("终点:" + job.mEnd);
            holder.contentView.setText(job.mContent);
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timeString = df.format(job.mTime);
            holder.timeView.setText(timeString);
        } else {
            holder.titleView.setText("加载失败");
        }

        return convertView;
    }
}
