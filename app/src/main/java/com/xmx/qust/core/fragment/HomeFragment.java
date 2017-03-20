package com.xmx.qust.core.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xmx.qust.R;
import com.xmx.qust.base.fragment.xUtilsFragment;
import com.xmx.qust.module.odd.MapActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_home)
public class HomeFragment extends xUtilsFragment {

    @Event(R.id.btn_map)
    private void onMapClick(View view) {
        startActivity(MapActivity.class);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

}
