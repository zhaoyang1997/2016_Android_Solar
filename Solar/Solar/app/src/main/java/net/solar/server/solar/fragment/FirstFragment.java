package net.solar.server.solar.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.solar.server.solar.AddDateActivity;
import net.solar.server.solar.DakaActivity;
import net.solar.server.solar.LoginActivity;
import net.solar.server.solar.MyActivity;
import net.solar.server.solar.R;
import net.solar.server.solar.TomatoActivity;
import net.solar.server.solar.adapter.MemoryAdapter;
import net.solar.server.solar.entity.Day;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FirstFragment extends Fragment{

    private String AUTOURL;

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
    private OkHttpClient okHttpClient=new OkHttpClient();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_memory, container, false);

        AUTOURL = getString(R.string.client_url);

        listView=view.findViewById(R.id.listView);
        backLeft = view.findViewById(R.id.iv_back_left);
        t20=view.findViewById(R.id.btn20);
        t45=view.findViewById(R.id.btn45);
        t60=view.findViewById(R.id.btn60);
        t90=view.findViewById(R.id.btn90);
        t120=view.findViewById(R.id.btn120);
        daka=view.findViewById(R.id.daka);
        add=view.findViewById(R.id.btn_add);
        GetMatter getMatter=new GetMatter();
        getMatter.execute();
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
                TOMATO = 25;
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
                Intent intent=new Intent(getActivity(),DakaActivity.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddDateActivity.class);
                startActivityForResult(intent,111);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==222&requestCode==111){
            String matter=data.getStringExtra("matter");
            long dayResult=data.getLongExtra("day",0);
            String dayStr=String.valueOf(dayResult);
            Day day=new Day(matter,dayStr);
            datas.add(day);
            memoryAdapter.notifyDataSetChanged();
        }

    }

    public class GetMatter extends AsyncTask {
        Response response = null;
        String result;
        @Override
        protected Object doInBackground(Object[] objects) {

            String path = AUTOURL + "/getmatter";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("userId", String.valueOf(LoginActivity.userId));
            FormBody body = bodyBuilder.build();
            Request request = new Request.Builder().post(body)
                    .url(path).build();
            Call call = okHttpClient.newCall(request);
            try {
                response = call.execute();
                result = response.body().string();
                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    String dateStr=jsonObject.getString("date");
                    String matter=jsonObject.getString("matter");

                    SimpleDateFormat format=new SimpleDateFormat("yyyy年MM月dd日");
                    Date curDate=new Date(System.currentTimeMillis());
                    String curStr=format.format(curDate);

                    Date matterDate=format.parse(dateStr);
                    Date curDatet=format.parse(curStr);
                    long dayLong=(matterDate.getTime()-curDatet.getTime())/1000/60/60/24;
                    String dayStr=String.valueOf(dayLong);
                    Day day=new Day(matter,dayStr);
                    datas.add(day);
                }
                Log.e("fyl",result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o){
            memoryAdapter=new MemoryAdapter(getActivity(),R.layout.memory_list_item,datas);
            listView.setAdapter(memoryAdapter);
            memoryAdapter.notifyDataSetChanged();

        }

    }
}
