package com.yao.library;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.yao.library.util.DisplayUtils;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView tvText1 ,tvText2;
    private LocationManager locationManager;
    private String locationProvider;
    private LocationListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText1= (TextView) findViewById(R.id.tv_text);
        tvText2= (TextView) findViewById(R.id.tv_text2);
//        //获取地理位置管理器
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        if(providers.contains(LocationManager.GPS_PROVIDER)){
//            //如果是GPS
            locationProvider = LocationManager.GPS_PROVIDER;
        }else if(providers.contains(LocationManager.NETWORK_PROVIDER)){
//            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }else{
            Toast.makeText(this, "没有可用的位置提供器", Toast.LENGTH_SHORT).show();
            return ;
        }
//        //获取Location
//        Location location = locationManager.getLastKnownLocation(locationProvider);
//        if(location!=null){
//            //不为空,显示地理位置经纬度
//            showLocation(location);
//        }
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d("Location2","onLocationChanged");
                // LocationListener最好在Activity的onCreate（）方法中进行实例化，
                // 当GPS获得Location时，会自动调用onLocationChanged方法.
                if(location!=null){
                    Log.d("Location2","onLocationChanged. latitude:" +location.getLatitude() + ",longtitude: "+location.getLongitude());
                    locationManager.removeUpdates(listener);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {


            }

            @Override
            public void onProviderEnabled(String provider) {
                // 当系统Setting -> Location& Security -> Use wirelessnetworks勾选，
                // Use GPS satellites勾选时调用
                Log.d("Location","onProviderEnabled");

            }

            @Override
            public void onProviderDisabled(String provider) {
                // 当系统Setting -> Location& Security ->
                // Use wirelessnetworks取消勾选，Use GPS satellites取消勾选时调用
                Log.d("Location","onProviderDisabled");

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        locationManager.requestLocationUpdates(locationProvider,100, 1,listener);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void showLocation(Location location) {
        String locationStr = "维度：" + location.getLatitude() +"\n"
                + "经度：" + location.getLongitude();
        tvText1.setText(locationStr);
    }
}
