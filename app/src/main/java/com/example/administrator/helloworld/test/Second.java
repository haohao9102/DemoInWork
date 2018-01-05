package com.example.administrator.helloworld.test;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.allen.apputils.ToastUtils;
import com.example.administrator.helloworld.R;
import com.example.administrator.helloworld.util.KeyboradUtils;
import com.qf.chenhao.mr_chenlibrary.base.BaseActivity;
import com.qf.chenhao.mr_chenlibrary.eventbus.C;
import com.qf.chenhao.mr_chenlibrary.eventbus.Event;
import com.qf.chenhao.mr_chenlibrary.eventbus.EventBusUtil;

import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;

/**
 * Created by Administrator on 2017/5/12.
 */

public class Second extends BaseActivity {

    private static final int MY_LOCATION_CODE = 1;
    private EditText edit;
    private Context ctx;
    private Activity act;
    private LocationManager locationManager;
    private TextView textView;

    @Override
    protected int getContentId() {
        return R.layout.second_layout;
    }

    @Override
    protected void init() {
        super.init();
        ctx = this;
        act = this;
        edit = findViewByIds(R.id.edit);
        edit.setInputType(InputType.TYPE_NULL);
//        Editable editable = edit.getText();
//        edit.setSelection(editable.length());
//        edit.requestFocus();
        edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                new KeyboradUtils(act, ctx, edit).showKeyboard(); //显示自定义键盘
                return false;
            }
        });

        textView = findViewByIds(R.id.text);
    }

    @Override
    public void setListener() {

    }

    public void send(View view) {
        switch (view.getId()) {
            case R.id.send1:
                EventBusUtil.sendEvent(new Event(C.EventCode.A, "hello"));
                EventBusUtil.sendEvent(new Event(C.EventCode.B));
                break;
            case R.id.send2:
                HiPermission.create(this)
                        .checkMutiPermission(new PermissionCallback() {
                            @Override
                            public void onClose() {

                            }

                            @Override
                            public void onFinish() {

                            }

                            @Override
                            public void onDeny(String permisson, int position) {

                            }

                            @Override
                            public void onGuarantee(String permisson, int position) {

                            }
                        });
                break;
        }

    }

    /**
     * android自带定位
     * @param view，android6.0权限申请
     */
    public void location(View view) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, MY_LOCATION_CODE);
        } else {
            startLocation();
        }
    }

    /**
     * 权限申请后，开始定位
     */
    private void startLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.NETWORK_PROVIDER;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        List<String> providers = locationManager.getProviders(true);//
        if (providers.contains(LocationManager.GPS_PROVIDER)) {//判断是否包含GPS
            provider = LocationManager.GPS_PROVIDER;
        } else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            provider = LocationManager.NETWORK_PROVIDER;
        } else {
            ToastUtils.showLongToast("No location provide to use");
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);//获取location地理位置方法
        if (location != null){
            showLocation(location);
        }
    }

    /**
     * 获取地理位置
     * @param location
     */
    private void showLocation(final Location location) {
        String currentPositon = "latitude is "+ location.getLatitude()+"\n"+
                "longitude is "+ location.getLongitude();

        textView.setText(currentPositon);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager!= null){
            locationManager.removeUpdates(locationListener);
        }
    }
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            showLocation(location);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    protected boolean isOpenStatus() {
        return false;
    }

    /**
     * 6.0权限回调方法
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_LOCATION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startLocation();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
