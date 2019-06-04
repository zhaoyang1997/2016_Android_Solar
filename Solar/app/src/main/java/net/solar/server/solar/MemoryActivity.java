package net.solar.server.solar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.solar.server.solar.adapter.MemoryAdapter;
import net.solar.server.solar.entity.Day;

import java.util.ArrayList;
import java.util.List;

public class MemoryActivity extends AppCompatActivity {

    private ListView listView;
    private List<Day> datas=new ArrayList<>();
    private TextView t20;
    private TextView t45;
    private TextView t60;
    private TextView t90;
    private TextView t120;
    private ImageView daka;
    private ImageView add;
    private MemoryAdapter memoryAdapter;
    private Intent tomatoIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        tomatoIntent = new Intent(getApplicationContext(),TomatoActivity.class);

        init();
        Day day1=new Day("距离高考还有","15");
        datas.add(day1);
        Day day2=new Day("距离中考还有","25");
        datas.add(day2);
        memoryAdapter=new MemoryAdapter(this,R.layout.memory_list_item,datas);
        listView.setAdapter(memoryAdapter);

        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            tomatoIntent.putExtra("tomato",20);
            startActivity(tomatoIntent);
            }
        });

        t45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomatoIntent.putExtra("tomato",45);
                startActivity(tomatoIntent);
            }
        });
        t60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomatoIntent.putExtra("tomato",60);
                startActivity(tomatoIntent);
            }
        });
        t90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomatoIntent.putExtra("tomato",90);
                startActivity(tomatoIntent);
            }
        });
        t120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomatoIntent.putExtra("tomato",120);
                startActivity(tomatoIntent);
            }
        });
        daka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MemoryActivity.this,DakaActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void init(){
        listView=findViewById(R.id.listView);
        t20=findViewById(R.id.btn20);
        t45=findViewById(R.id.btn45);
        t60=findViewById(R.id.btn60);
        t90=findViewById(R.id.btn90);
        t120=findViewById(R.id.btn120);
        daka=findViewById(R.id.daka);
        add=findViewById(R.id.btn_add);
    }
}
