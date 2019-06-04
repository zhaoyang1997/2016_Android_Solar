package net.solar.server.solar;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class AddDateActivity extends AppCompatActivity implements View.OnClickListener ,DatePicker.OnDateChangedListener{

    private Context context;
    private TextView newTime;
    private int year,month,day;
    private StringBuffer date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_date);

        newTime=findViewById(R.id.newTime);
        newTime.setOnClickListener(this);
        context=this;
        date=new StringBuffer();
        initDateTime();

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
        this.month=monthOfYear;
        this.day=dayOfMonth;
    }

}
