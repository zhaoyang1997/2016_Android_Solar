package net.solar.server.solar;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
public class StartTimeActivity extends AppCompatActivity {
    private String AUTOURL;
    private ImageView backLeft;
    private LinearLayout countDown;
    // 倒计时
    private TextView minutesTv, secondsTv;
    private long mMin;
    private long mSecond = 00;// 天 ,小时,分钟,秒
    private MyThread myThread;
    private Thread thread;
    private boolean flag;
    private int mCount=10;
    private int  TaskId;
    private String TaskName;
    private int TaskTime;
    private  Bundle bundle;
    private int userId;
    private Handler timeHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if (what==1) {
                computeTime();
                minutesTv.setText(mMin+"");
                secondsTv.setText(mSecond+"");
                if (mMin==0&&mSecond==0) {
                    //countDown.setVisibility(View.GONE);
                    //跳转-->去数据库中改任务状态为完成《--根据taskId
                    new Thread(){
                        @Override
                        public void run() {
                            Process.setThreadPriority(10);
                            OkHttpClient okHttpClient = new OkHttpClient();
                            FormBody.Builder builder = new FormBody.Builder();
                            builder.add("Tid",String.valueOf(TaskId));
                            builder.add("userId", String.valueOf(1));
                            FormBody body = builder.build();
                            final Request request = new Request.Builder()
                                    .url("http://10.7.89.47:8080/SolarService/task/updateTaskState")
                                    .post(body)
                                    .build();
                            final Call call = okHttpClient.newCall(request);
                            try {
                                Response response = call.execute();
                                String str = response.body().string();

                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }.start();
//完成任务
                    StartTimeActivity.this.finish();
                }
            }
        }


    };

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_time);

        AUTOURL = getResources().getString(R.string.client_url);
        userId=LoginActivity.userId;
        //监听返回
        backLeft = findViewById(R.id.clock_back_left);
        backLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTimeActivity.this.finish();
            }
        });
        //获得任务的名字/时间/Id
        final Intent i = getIntent();
        bundle=i.getExtras();
        TaskId=bundle.getInt("TaskId");
        TaskName=bundle.getString("TaskName");
        TextView tv = findViewById(R.id.task_name1);
        tv.setText(TaskName);
        TaskTime= bundle.getInt("TaskTime");
        userId=bundle.getInt("userId");
        Log.e("时间", String.valueOf(bundle.getInt("TaskTime")));


        myThread = new MyThread();
        thread = new Thread(myThread);

        countDown = findViewById(R.id.count_time_layout);
        minutesTv = findViewById(R.id.minutes_tv);
        secondsTv = findViewById(R.id.seconds_tv);
        flag=false;
        thread.interrupt();
        mMin=TaskTime;
        mSecond = 00;
        minutesTv.setText(mMin+"");
        secondsTv.setText(mSecond+"0");
        thread = new Thread(myThread);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //开始计时器或者是重启计时器，设置标记为true
        flag=true;
        //直接进行倒计时
        thread.start();
        //监听暂停和开始
        Button button = findViewById(R.id.zanting);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                if(!thread.isAlive()){
                    //开始计时器或者是重启计时器，设置标记为true
                    flag=true;
                    //判断是否是第一次启动，如果是不是第一次启动，那么状态就是Thread.State.TERMINATED
                    //不是的话，就需要重新的初始化，因为之前的已经结束了。
                    //并且要判断这个mCount 是否为-1，如果是的话，说名上一次的计时已经完成了，那么要重新设置。
                    if(thread.getState()==Thread.State.TERMINATED){
                        thread = new Thread(myThread);
                        if(mCount==-1) mCount=10;
                        thread.start();
                    }else{
                        thread.start();
                    }
                }else {
                    //暂停计时器，设置标记为false
                    flag=false;
                    //不可以使用 stop 方法，会报错，java.lang.UnsupportedOperationException
                    //mThread.stop();
                }
            }
        });
//鉴定中止按钮
        Button stop=findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //给出列表弹框
                final String[] items = new String[]{"放弃当前计时", "提前完成计时", "取消"};//创建item
                AlertDialog alertDialog3 = new AlertDialog.Builder(StartTimeActivity.this)
                        .setTitle("请选择选择您的选项")
                        .setIcon(R.mipmap.ic_launcher)
                        .setItems(items, new DialogInterface.OnClickListener() {//添加列表
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(items[i].equals("放弃当前计时")){
                                    StartTimeActivity.this.finish();
                                }else if(items[i].equals("提前完成计时")){
                                    new Thread(){
                                        @Override
                                        public void run() {
                                            Process.setThreadPriority(10);

                                            OkHttpClient okHttpClient = new OkHttpClient();
                                            FormBody.Builder builder = new FormBody.Builder();
                                            builder.add("Tid",String.valueOf(TaskId));
                                            builder.add("userId", String.valueOf(userId));
                                            FormBody body = builder.build();
                                            final Request request = new Request.Builder()
                                                    .url(AUTOURL+"/task/updateTaskState")
                                                    .post(body)
                                                    .build();
                                            final Call call = okHttpClient.newCall(request);
                                            try {
                                                Response response = call.execute();
                                                String str = response.body().string();

                                            } catch (IOException e1) {
                                                e1.printStackTrace();
                                            }
                                        }
                                    }.start();
                                    //完成任务
                                    StartTimeActivity.this.finish();
                                }else{
                                    //继续执行倒计时！！！
                                    dialogInterface.dismiss();
                                }
                            }
                        }).create();
                alertDialog3.show();

            }
        });
        Button begin = findViewById(R.id.btn_begin);
        begin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=false;
                thread.interrupt();
                mMin=TaskTime;
                mSecond = 00;
                minutesTv.setText(mMin+"");
                secondsTv.setText(mSecond+"0");
                thread = new Thread(myThread);
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private class MyThread implements Runnable{

        @Override
        public void run() {
            while (flag&&mCount>=0) {
                try {
                    Thread.sleep(1000);
                    Message message = Message.obtain();
                    message.what = 1;
                    message.arg1=mCount;
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
        flag = false;
    }

    /**
     * 倒计时计算
     */
    private void computeTime() {
        mSecond--;
        if (mSecond < 0) {
            mMin--;
            mSecond = 59;
            if (mMin < 0) {
                mMin = 25;
            }
        }
    }
}
