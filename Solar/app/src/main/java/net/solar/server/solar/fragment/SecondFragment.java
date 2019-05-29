package net.solar.server.solar.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.solar.server.solar.R;
import net.solar.server.solar.adapter.CustomAdapter;
import net.solar.server.solar.entity.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SecondFragment extends Fragment {
    private ListView lvTask;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.secondfragment_task,container,false);
        lvTask = view.findViewById(R.id.lvTask);

        final List<String> tasks = new ArrayList<>();
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType type = MediaType.parse("application/text");
        String id = "1";
        RequestBody body = RequestBody.create(type,id);
        final Request request = new Request.Builder()
                .url("http://10.7.89.37:8080/SolarService/task/findTaskName")
                .post(body)
                .build();
        final Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //当请求失败时调用
                Log.e("onFailure()********","不能获取到服务器端的数据");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //当请求成功时调用
                if (response.isSuccessful()) {
                    String messsage = response.body().string();
                    Log.e("message*******", messsage);
                    Gson gson = new Gson();
                    List<Task> list = gson.fromJson(messsage,new TypeToken<List<Task>>(){}.getType());
                    for(int i=0;i<list.size();i++){
                        Task task = new Task();
                        task.setTaskName(list.get(i).getTaskName());
                        tasks.add(task.getTaskName());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            CustomAdapter customAdapter = new CustomAdapter(getContext(),tasks);
                            lvTask.setAdapter(customAdapter);
                            lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                }
                            });
                        }
                    });
                }
            }
        });
        return view;
    }
}
