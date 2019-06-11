package net.solar.server.solar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.solar.server.solar.adapter.ScoreAdapter;
import net.solar.server.solar.entity.ScoreDetails;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ScoreActivity extends AppCompatActivity {

    private String AUTOURL;
    private int userId = LoginActivity.userId;

    private ListView lvScore;
    private ScoreAdapter scoreAdapter;

    private List<ScoreDetails> list;
    private OkHttpClient okHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        AUTOURL = getResources().getString(R.string.client_url);

        lvScore = findViewById(R.id.lv_score);
        list = new ArrayList<ScoreDetails>();
        okHttpClient = new OkHttpClient();

        getDataForAdapter(userId);

    }

    /**
     * 根据用户id查找，为Adapter准备积分详情页的数据源
     */
    protected void getDataForAdapter(int uid){
        String id = String.valueOf(uid);
        FormBody formBody = new FormBody.Builder()
                .add("uid",id)
                .build();
        Request request = new Request.Builder()
                .url(AUTOURL+"/tomato/score")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("zn--test--failure","传送到服务器");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                Log.e("zn--test--response",response.body().string());
                String result = response.body().string();
                Gson gson = new Gson();
                Type type = new TypeToken<List<ScoreDetails>>(){}.getType();
                final List<ScoreDetails> sds = gson.fromJson(result,type);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        scoreAdapter = new ScoreAdapter(getApplicationContext(),sds);
                        lvScore.setAdapter(scoreAdapter);
                    }
                });
            }
        });

    }



}
