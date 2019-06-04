package net.solar.server.solar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.solar.server.solar.AddDateActivity;
import net.solar.server.solar.DakaActivity;
import net.solar.server.solar.MyActivity;
import net.solar.server.solar.R;
import net.solar.server.solar.TomatoActivity;
import net.solar.server.solar.adapter.MemoryAdapter;
import net.solar.server.solar.entity.Day;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment{
    private ListView listView;
    private List<Day> datas=new ArrayList<>();
    private TextView t20;
    private TextView t45;
    private TextView t60;
    private TextView t90;
    private TextView t120;
    private ImageView daka;
    private ImageView add;
    private ImageView backLeft;
    private MemoryAdapter memoryAdapter;
    private Intent tomatoIntent;
    public static int TOMATO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_memory, container, false);
        listView=view.findViewById(R.id.listView);
        backLeft = view.findViewById(R.id.iv_back_left);
        t20=view.findViewById(R.id.btn20);
        t45=view.findViewById(R.id.btn45);
        t60=view.findViewById(R.id.btn60);
        t90=view.findViewById(R.id.btn90);
        t120=view.findViewById(R.id.btn120);
        daka=view.findViewById(R.id.daka);
        add=view.findViewById(R.id.btn_add);
        Day day1=new Day("距离高考还有","15");
        datas.add(day1);
        Day day2=new Day("距离中考还有","25");
        datas.add(day2);
        memoryAdapter=new MemoryAdapter(this.getActivity(),R.layout.memory_list_item,datas);
        listView.setAdapter(memoryAdapter);

        backLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

        t20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOMATO = 20;
                tomatoIntent = new Intent(getActivity(),TomatoActivity.class);
                startActivity(tomatoIntent);
            }
        });

        t45.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOMATO = 45;
                tomatoIntent = new Intent(getActivity(),TomatoActivity.class);
                startActivity(tomatoIntent);

            }
        });
        t60.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOMATO = 60;
                tomatoIntent = new Intent(getActivity(),TomatoActivity.class);
                startActivity(tomatoIntent);
            }
        });
        t90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOMATO = 90;
                tomatoIntent = new Intent(getActivity(),TomatoActivity.class);
                startActivity(tomatoIntent);
            }
        });
        t120.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TOMATO = 120;
                tomatoIntent = new Intent(getActivity(),TomatoActivity.class);
                startActivity(tomatoIntent);
            }
        });
        daka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(),DakaActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDateActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
