package com.xmx.qust.core.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.xmx.qust.R;
import com.xmx.qust.base.fragment.xUtilsFragment;
import com.xmx.qust.module.odd.MapActivity;
import com.xmx.qust.module.odd.OddJobListActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * A simple {@link Fragment} subclass.
 */
@ContentView(R.layout.fragment_home)
public class HomeFragment extends xUtilsFragment {

    @Event(R.id.btnMeal)
    private void onMealClick(View view) {
        Intent i = new Intent(getContext(), OddJobListActivity.class);
        i.putExtra("type", 1);
        i.putExtra("must-login", false);
        startActivity(i);
    }

    @Event(R.id.btnMap)
    private void onMapClick(View view) {
        startActivity(MapActivity.class);
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

}
