package com.xmx.qust.core.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xmx.qust.R;
import com.xmx.qust.base.fragment.xUtilsFragment;
import com.xmx.qust.module.map.amap.AMapActivity;
import com.xmx.qust.module.map.amap.AMapPOIActivity;
import com.xmx.qust.module.map.amap.AMapRouteActivity;
import com.xmx.qust.module.map.bmap.BMapActivity;
import com.xmx.qust.module.map.bmap.BMapPOIActivity;
import com.xmx.qust.module.map.bmap.BMapRouteActivity;
import com.xmx.qust.module.web.QUSTOfficialActivity;
import com.xmx.qust.module.web.QUSTStudentActivity;
import com.xmx.qust.module.web.QUSTWifiActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_web)
public class WebFragment extends xUtilsFragment {
    @Event(value = R.id.btnQUSTWifi)
    private void onQUSTWifiClick(View view) {
        startActivity(QUSTWifiActivity.class);
    }

    @Event(value = R.id.btnQUSTOfficial)
    private void onQUSTOfficialClick(View view) {
        startActivity(QUSTOfficialActivity.class);
    }

    @Event(value = R.id.btnQUSTStudent)
    private void onQUSTStudentClick(View view) {
        startActivity(QUSTStudentActivity.class);
    }


    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

}
