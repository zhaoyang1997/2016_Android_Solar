package net.solar.server.solar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class DakaActivity extends AppCompatActivity {

    private String AUTOURL;

    private TextView tvDate;
    private TextView tvDingwei;
    private LocationClient mLocationClient;
    private Button daka;
    private String date;
    private OkHttpClient okHttpClient=new OkHttpClient();
    private TextView sumDate;
    private int sumresult;
    private int tag=0;
    private String address;
    private int userId = LoginActivity.userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daka);

        AUTOURL = getResources().getString(R.string.client_url);

        startLocate();
        tvDate=findViewById(R.id.tvDate);
        daka=findViewById(R.id.daka);
        sumDate=findViewById(R.id.sunDate);
        SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
        Date curDate=new Date(System.currentTimeMillis());
        date=format.format(curDate);
        tvDate.setText(date);
        tvDingwei=findViewById(R.id.tvDingwei);
        DateNum dateNum=new DateNum();
        dateNum.execute();

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
                address=poiList.get(0).getName();
                mLocationClient.unRegisterLocationListener(mListener);
                mLocationClient.stop();
            }
        }
    };

    public void logMsg(final String str){
        tvDingwei.setText(str);
    }
    public void onDestroy(){
        super.onDestroy();

    }

    public class Daka extends AsyncTask {
        Response response = null;
        String result;
        @Override
        protected Object doInBackground(Object[] objects) {

            String path = AUTOURL + "/daka/daka";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("date", date);
            bodyBuilder.add("address", address);
            bodyBuilder.add("userId", "1");
            FormBody body = bodyBuilder.build();
            Request request = new Request.Builder().post(body)
                    .url(path).build();
            Call call = okHttpClient.newCall(request);

            try {
                response = call.execute();
                result = response.body().string();
                Log.e("fyl",result);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o){
            daka.setClickable(false);
            daka.setText("今天已打卡");
        }

    }

    public class DateNum extends AsyncTask {
        Response response = null;
        @Override
        protected Object doInBackground(Object[] objects) {

            String path = AUTOURL + "/daka/dakanum";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("date", date);
            bodyBuilder.add("userId", String.valueOf(userId));
            FormBody body = bodyBuilder.build();
            Request request = new Request.Builder().post(body)
                    .url(path).build();
            Call call = okHttpClient.newCall(request);
            String result;
            try {
                response = call.execute();
                result = response.body().string();
                Log.e("fyl",result);
                JSONArray jsonArray=new JSONArray(result);
                JSONObject jsonObject1=jsonArray.getJSONObject(1);
                tag=jsonObject1.getInt("tag");
                JSONObject jsonObject=jsonArray.getJSONObject(0);
                sumresult=jsonObject.getInt("num");


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o){
            sumDate.setText(sumresult+"天");
            if (tag!=1){
                daka.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Daka daka=new Daka();
                        daka.execute();
                        sumresult++;
                        sumDate.setText(sumresult+"天");

                    }
                });
            }
            else if(tag==1){
                daka.setText("今天已打卡");
            }


        }

    }

}
