package net.solar.server.solar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AddDateActivity extends AppCompatActivity implements View.OnClickListener ,DatePicker.OnDateChangedListener{

    private String AUTOURL;

    private Context context;
    private TextView newTime;
    private int year,month,day;
    private StringBuffer date;

    private String dateStr;
    private OkHttpClient okHttpClient=new OkHttpClient();
    private Button queding;
    private String matter;
    private EditText newShijian;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        AUTOURL = getResources().getString(R.string.client_url);

        newTime=findViewById(R.id.newTime);
        queding=findViewById(R.id.queding);
        newShijian=findViewById(R.id.newShijian);
        newTime.setOnClickListener(this);
        context=this;
        date=new StringBuffer();
        initDateTime();
        queding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddMatter addMatter=new AddMatter();
                addMatter.execute();
            }
        });
    }

    private void initDateTime(){
        Calendar calendar=Calendar.getInstance();
        year=calendar.get(Calendar.YEAR);
        month=calendar.get(Calendar.MONTH+1);
        day=calendar.get(Calendar.DAY_OF_MONTH);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.newTime:
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (date.length()>0){
                            date.delete(0,date.length());
                        }
                        newTime.setText(date.append(String.valueOf(year)).append("年").append(String.valueOf(month)).append("月").append(day).append("日"));
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                final AlertDialog dialog=builder.create();
                View dialogView=View.inflate(context,R.layout.dialog_date,null);
                final DatePicker datePicker=(DatePicker)dialogView.findViewById(R.id.datePicker);
                dialog.setTitle("设置日期");
                dialog.setView(dialogView);
                dialog.show();

                datePicker.init(year,month-1,day,this);
                break;
        }
    }

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        this.year=year;
        this.month=monthOfYear+1;
        this.day=dayOfMonth;
    }

    public class AddMatter extends AsyncTask {
        Response response = null;
        String result;
        @Override
        protected Object doInBackground(Object[] objects) {

            String path = AUTOURL + "/addmatter";
            dateStr=newTime.getText().toString();
            matter=newShijian.getText().toString();
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("date", dateStr);
            bodyBuilder.add("matter", matter);
            bodyBuilder.add("userId", String.valueOf(LoginActivity.userId));
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
            Intent intent=new Intent();
            SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
            Date curDate=new Date(System.currentTimeMillis());
            String curStr=format.format(curDate);
            try {
                Date matterDate=format.parse(dateStr);
                Date curDatet=format.parse(curStr);
                long day=(matterDate.getTime()-curDatet.getTime())/1000/60/60/24;
                intent.putExtra("day",day);
                intent.putExtra("matter",matter);
                setResult(222,intent);
                AddDateActivity.this.finish();
            }
             catch (ParseException e) {
                e.printStackTrace();
            }
        }

    }

}
