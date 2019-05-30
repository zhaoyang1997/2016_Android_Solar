package net.solar.server.solar;

import android.location.Geocoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;

import net.solar.server.solar.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DakaActivity extends AppCompatActivity {

    private TextView tvDate;
    private TextView tvDingwei;
    private LocationClient mLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka);
        startLocate();
        tvDate=findViewById(R.id.tvDate);
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate=new Date(System.currentTimeMillis());
        String date=format.format(curDate);
        tvDate.setText(date);
        tvDingwei=findViewById(R.id.tvDingwei);

    }
    private void startLocate(){
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(mListener);
        LocationClientOption option=new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd0911");
        option.setScanSpan(1000);
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
        option.setLocationNotify(true);
        option.setIsNeedLocationDescribe(true);
        option.setIgnoreKillProcess(false);
        option.setIsNeedLocationPoiList(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();

    }
    private BDAbstractLocationListener mListener=new BDAbstractLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if (null != bdLocation && bdLocation.getLocType() != BDLocation.TypeServerError) {
                List<Poi> poiList = bdLocation.getPoiList();
                for (Poi p : poiList)
                    Log.e("===", p.getName());
                logMsg(poiList.get(0).getName());

            }
        }
    };



    public void logMsg(final String str){
                tvDingwei.setText(str);
    }
    public void onDestroy(){
        super.onDestroy();
        mLocationClient.unRegisterLocationListener(mListener);
        mLocationClient.stop();
    }
}
