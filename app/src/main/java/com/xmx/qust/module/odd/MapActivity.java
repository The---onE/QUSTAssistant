package com.xmx.qust.module.odd;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.maps.AMap;
import com.xmx.qust.R;
import com.xmx.qust.common.map.amap.activity.BaseLocationDirectionActivity;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;

/**
 * Created by The_onE on 2017/3/20.
 */

@ContentView(R.layout.activity_map)
public class MapActivity extends BaseLocationDirectionActivity {

    @Event(R.id.btn_location)
    private void onLocationClick(View view) {
        focusLocation();
    }

    @Override
    protected void getMapView() {
        mMapView = getViewById(R.id.map);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);

        strokeColor = Color.argb(180, 3, 145, 255);
        fillColor = Color.argb(64, 128, 192, 192);
        markerFlag = "myLocation";
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        super.processLogic(savedInstanceState);

        mAMap.getUiSettings().setMyLocationButtonEnabled(false);//设置默认定位按钮是否显示
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);//设置定位的类型为定位模式
        mAMap.setMapType(AMap.MAP_TYPE_SATELLITE);
    }

    @Override
    protected void setLocationOption(AMapLocationClientOption locationOption) {
        //设置为高精度定位模式
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
    }

    @Override
    protected void whenFirstLocation(AMapLocation aMapLocation) {
        focusLocation();
    }

    @Override
    protected void whenNewLocation(AMapLocation aMapLocation) {
    }

    @Override
    protected void whenLocationError(int errorCode, String errorInfo) {
        String errText = "定位失败," + errorCode + ": " + errorInfo;
        showToast(errText);
    }

    @Override
    protected void whenResume() {
    }

    @Override
    protected void whenPause() {
    }

    @Override
    protected void whenDestroy() {
    }
}
