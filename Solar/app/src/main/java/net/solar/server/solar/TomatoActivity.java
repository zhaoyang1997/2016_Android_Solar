package net.solar.server.solar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.solar.server.solar.fragment.FirstFragment;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class TomatoActivity extends AppCompatActivity {

    //全局字符串
    private String autoUrl;
    private int userId = 1;

    //从首页传过来的分钟数
    private int targetMin = FirstFragment.TOMATO;

    //UI控件
    private TextView tvMin;
    private TextView tvSec;
    private LinearLayout layoutClock;
    private ImageView clockBackLeft;
    private Button btnGiveUp;
    private AlertDialog.Builder builder;

    private int stop = 1;

    //倒计时
    private long mMins;
    private long mSecs;
    private boolean mflag;
    private int mCount=10;
    private MyThread myThread;
    private Thread thread;

    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            computeTime();

            Log.e("flag", "7");

            if(mMins<10){
                tvMin.setText(" "+mMins);
            }else{
                tvMin.setText(mMins + "");
            }
            if(mSecs<10){
                tvSec.setText("0"+mSecs);
            }else {
                tvSec.setText(mSecs + "");
            }
            if (mMins == 0 && mSecs == 0) {
                uploadData(targetMin);
                Toast.makeText(getApplicationContext(), "当前时间已完成", Toast.LENGTH_SHORT).show();
                TomatoActivity.this.finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomato);

        tvMin = findViewById(R.id.tvMin);
        tvSec = findViewById(R.id.tvSec);
        tvMin.setText(String.valueOf(targetMin));
        layoutClock = findViewById(R.id.layout_clock);
        btnGiveUp = findViewById(R.id.btn_give_up);
        clockBackLeft = findViewById(R.id.clock_back_left);

        myThread = new MyThread();

        layoutClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (stop == 1) {
                    stop--;
                    thread = new Thread(myThread);
                    if (!thread.isAlive()) {
                        mflag= true;
                        if (thread.getState() == Thread.State.TERMINATED) {
                            thread = new Thread(myThread);
                            if(mCount==-1) mCount=10;
                            TomatoActivity.this.finish();
                        } else {
                            thread.start();
                            Log.e("flag", "8");
                        }
                    }
                }

            }
        });

        clockBackLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        //放弃当前计时按钮点击事件
        btnGiveUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
    }

    /**
     * 倒计时线程
     */
    private class MyThread implements Runnable {

        @Override
        public void run() {
            Log.e("zn--test--flag", "9");

            while (mflag&&mCount>=0) {
                try {
                    Thread.sleep(1000); // sleep 1000ms

                    Log.e("zn--test--mMin", String.valueOf(mMins));

                    Message message = Message.obtain();
                    message.what = 1;
                    timeHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mflag = false;
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecs--;
        if (mSecs < 0) {
            mMins--;
            mSecs = 59;
            if (mMins < 0) {
                mMins = targetMin-1;
            }
        }
    }

    //一个AlertDialog生成方法
    private void showAlertDialog(){

        builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.astronaut);
        builder.setTitle("提示");
        builder.setMessage("确定放弃当前计时吗？");
        builder.setPositiveButton("放弃", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                mflag = false;
                Toast.makeText(getApplicationContext(), "已放弃当前计时", Toast.LENGTH_SHORT).show();
                TomatoActivity.this.finish();
            }
        });
        builder.setNegativeButton("不放弃", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "计时继续", Toast.LENGTH_SHORT).show();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

    /**
     * 番茄页面：客户端数据传输
     */
    private void uploadData(final int targetMins){
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType type = MediaType.parse("application/text");
        String finalTime = String.valueOf(targetMins);
        RequestBody requestBody = RequestBody.create(type,finalTime);
        Request request = new Request.Builder()
                .url("http://10.7.89.78:8080/MyBatisDemo8/user/tomato")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("zn--test--failure","传送到服务器");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("zn--test--response",response.body().string());
            }
        });
    }

}
