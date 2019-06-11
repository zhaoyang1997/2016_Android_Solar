package net.solar.server.solar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddTaskActivity extends AppCompatActivity {
    private ImageView ivBack;
    private EditText taskName;
    private EditText taskTime;
    private Button btnFinish;
    private String Tname;
    private String Ttime;
    private String Year;
    private String Month;
    private String Day;

    private String AUTOURL;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        AUTOURL = getResources().getString(R.string.client_url);
        id=LoginActivity.userId;

        ivBack = findViewById(R.id.back);
        taskName = findViewById(R.id.taskname);
        taskTime = findViewById(R.id.tasktime);
        btnFinish = findViewById(R.id.btnFinish);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTaskActivity.this.finish();
            }
        });

        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tname=taskName.getText().toString().trim();
                Ttime= taskTime.getText().toString().trim();
                Year=getYear();
                Month=getMonth();
                Day=getDay();

                AddTask addTask = new AddTask();
                addTask.execute();
            }
        });


    }

    //获得点击事件的年月日
    private String getYear(){
        SimpleDateFormat sdf =new SimpleDateFormat("yyy");
        String s=sdf.format(new Date());
        return s;
    }
    private String getMonth(){
        SimpleDateFormat sdf=new SimpleDateFormat("MM");
        String s=sdf.format(new Date());
        return s;
    }
    private String getDay(){
        SimpleDateFormat sdf=new SimpleDateFormat("dd");
        String s=sdf.format(new Date());
        return s;
    }

    //添加任务
    public class AddTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            final Message message = Message.obtain();
            //判断如果修改的内容和时间为空时，给出弹框
            if (Tname.equals("") || Ttime.equals("")) {
                message.what = 2;
                handler.sendMessage(message);
            } else {
                OkHttpClient okHttpClient = new OkHttpClient();
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("Tname", Tname);
                builder.add("Ttime", Ttime);
                builder.add("Tyear", Year);
                builder.add("Tmonth", Month);
                builder.add("Tday", Day);
                builder.add("userId", String.valueOf(id));
                FormBody body = builder.build();
                final Request request = new Request.Builder()
                        .url(AUTOURL+"/task/addTask")
                        .post(body)
                        .build();
                final Call call = okHttpClient.newCall(request);
                try {
                    Response response = call.execute();
                    String mes = response.body().string();
                    Log.e("onResponse********", mes);

                } catch (IOException e) {
                    e.printStackTrace();
                };
            }
            return  null;
        }

        @Override
        protected void onPostExecute(Object o) {
            Intent intent = new Intent();
            intent.putExtra("Tname",Tname);
            intent.putExtra("Ttime",Ttime);
            intent.putExtra("userId",String.valueOf(1));
            AddTaskActivity.this.setResult(222,intent);
            AddTaskActivity.this.finish();
        }
    }
    //定义Handler
    Handler handler=new Handler(){

        @Override
        public void handleMessage(Message msg) {

            Log.e("what","111111");
            switch (msg.what){
                case 1:
                    AddTaskActivity.this.finish();
                    break;
                case 2:

                    //创建Toast对象
                    Toast toast = Toast.makeText(AddTaskActivity.this,
                            "任务名称和时长不能为空",
                            Toast.LENGTH_LONG);
                    //设置Toast显示的位置
                    toast.setGravity(Gravity.BOTTOM, 0,0);
                    //显示Toast
                    toast.show();
                    break;
            }
            super.handleMessage(msg);
        }
    };

}
