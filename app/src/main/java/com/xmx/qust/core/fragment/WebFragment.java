package com.xmx.qust.core.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xmx.qust.R;
import com.xmx.qust.base.fragment.xUtilsFragment;
import com.xmx.qust.module.web.WebPageActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_web)
public class WebFragment extends xUtilsFragment {
    @Event(value = R.id.btnQUSTWifi)
    private void onQUSTWifiClick(View view) {
        startActivity(WebPageActivity.class, "url", "http://172.16.10.3");
    }
    @Event(value = R.id.btnQUSTOfficial)
    private void onQUSTOfficialClick(View view) {
        startActivity(WebPageActivity.class, "url", "http://m.qust.edu.cn/");
    }
    @Event(value = R.id.btnQUSTStudent)
    private void onQUSTStudentClick(View view) {
        startActivity(WebPageActivity.class, "url", "http://m.qust.edu.cn/index/ryfl/xs.htm");
    }


    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

}
