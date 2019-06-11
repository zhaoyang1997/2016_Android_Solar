package net.solar.server.solar.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.solar.server.solar.AddTaskActivity;
import net.solar.server.solar.EditActivity;
import net.solar.server.solar.LoginActivity;
import net.solar.server.solar.R;
import net.solar.server.solar.StartTimeActivity;
import net.solar.server.solar.adapter.CustomAdapter;
import net.solar.server.solar.entity.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SecondFragment extends Fragment {

    //链接服务器的url
    private String AUTOURL;

    private int id;

    private ListView lvTask;
    private FloatingActionButton floatingActionButton;
    final List<Task> tasks = new ArrayList<>();
    final List<String> lists = new ArrayList<>();
    private CustomAdapter customAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.secondfragment_task,container,false);

        id = LoginActivity.userId;
        AUTOURL = getString(R.string.client_url);

        lvTask = view.findViewById(R.id.lvTask);
        floatingActionButton = view.findViewById(R.id.float_button);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddTaskActivity.class);
                startActivityForResult(intent,111);
            }
        });

        findItem();

        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                final AlertDialog.Builder builder=new AlertDialog.Builder(getContext());

                //正极按钮
                builder.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        AlertDialog.Builder builder1=new AlertDialog.Builder(getContext());
                        builder1.setMessage("确定删除吗？");
                        //设置提示标题
                        builder1.setTitle("提示");
                        builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //根据TaskId去数据库中删
                                //启动一个子线程将数据库中数据也删除
                                new Thread() {
                                    @Override
                                    public void run() {
                                        OkHttpClient okHttpClient = new OkHttpClient();
                                        FormBody.Builder builder = new FormBody.Builder();
                                        builder.add("Tid", String.valueOf(tasks.get(position).getId()));
                                        builder.add("userId", String.valueOf(id));
                                        FormBody body = builder.build();
                                        final Request request = new Request.Builder()
                                                .url(AUTOURL+"/task/deleteTask")
                                                .post(body)
                                                .build();
                                        final Call call = okHttpClient.newCall(request);
                                        try {
                                            Response response = call.execute();
                                            String str = response.body().string();
                                            Log.e("response",str);
                                            if(str.equals("1\r\n")){
                                                lists.remove(position);
                                                mHandler.sendEmptyMessage(1);
                                            }
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    }

                                }.start();
                            }
                        });
                        builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder1.setCancelable(true);
                        AlertDialog dialog1=builder1.create();
                        dialog1.setCancelable(false);
                        dialog1.show();
                    }
                });
                //中
                builder.setNeutralButton("开始", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //页面跳转
                        Intent intent = new Intent();
                        intent.setClass(getContext(), StartTimeActivity.class);
                        //封装携带数据到另一个页面
                        Bundle bundle = new Bundle();
                        bundle.putInt("TaskId",tasks.get(position).getId());
                        bundle.putString("TaskName",tasks.get(position).getTaskName());
                        bundle.putInt("TaskTime",tasks.get(position).getTaskTime());
                        bundle.putInt("userId",1);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                });
                //负极按钮
                builder.setNegativeButton("编辑", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), EditActivity.class);
                        intent.putExtra("taskName",tasks.get(position).getTaskName());
                        intent.putExtra("taskTime",tasks.get(position).getTaskTime());
                        intent.putExtra("position",position);
                        Log.e("$$$$$$$$$$$$",tasks.get(position).getTaskName()+tasks.get(position).getTaskTime());
                        startActivityForResult(intent,111);
                    }
                });
                builder.setCancelable(true);//弹出框可以按返回取消
                AlertDialog dialog=builder.create();//创建对话框
                dialog.setCancelable(false);
                dialog.show();//弹出
            }
        });
        return view;
    }


    /**
     * 查询item
     */
    public void findItem(){
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType type = MediaType.parse("application/text");
        RequestBody body = RequestBody.create(type, String.valueOf(1));
        final Request request = new Request.Builder()
                .url("http://10.7.89.47:8080/SolarService/task/findTaskName")
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
                        task.setTaskTime(list.get(i).getTaskTime());
                        task.setId(list.get(i).getId());
                        task.setUserId(LoginActivity.userId);
                        tasks.add(task);
                        lists.add(task.getTaskName());
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //更新UI
                            customAdapter = new CustomAdapter(getContext(),lists);
                            lvTask.setAdapter(customAdapter);
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==222 && requestCode==111){
            String taskName = data.getStringExtra("Tname");
            String time = data.getStringExtra("Ttime");
            String userId = data.getStringExtra("userId");
            Task task = new Task();
            task.setUserId(Integer.parseInt(userId));
            task.setTaskName(taskName);
            task.setTaskTime(Integer.parseInt(time));
            lists.add(taskName);
            tasks.add(task);
            customAdapter.notifyDataSetChanged();
        }

        if (resultCode==333 && requestCode==111){
            String name = data.getStringExtra("Tname");
            Integer position = data.getIntExtra("position",0);
            lists.set(position,name);
            customAdapter.notifyDataSetChanged();
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.e("handler","111111111111");
            super.handleMessage(msg);
            if (msg.what == 1) {
                customAdapter.notifyDataSetChanged();
            }
        }
    };

}
