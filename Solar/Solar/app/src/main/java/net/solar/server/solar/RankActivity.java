package net.solar.server.solar;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import net.solar.server.solar.adapter.RankAdapter;
import net.solar.server.solar.entity.Rank;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RankActivity extends AppCompatActivity {
    private ListView listView;
    private RankAdapter rankAdapter;
    private List<Rank> rankList=new ArrayList<>();
    private OkHttpClient okHttpClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        listView=findViewById(R.id.rankListView);
        okHttpClient=new OkHttpClient();
        GetRank getRank=new GetRank();
        getRank.execute();
    }

    public class GetRank extends AsyncTask {
        Response response = null;
        String result;
        @Override
        protected Object doInBackground(Object[] objects) {

            String path = "http://10.7.89.47:8080/SolarService/user/getrank";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
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
                    String img=jsonObject.getString("img");
                    String name=jsonObject.getString("name");
                    int time=jsonObject.getInt("time");
                    String url="http://10.7.89.47:8080/SolarService/images/"+img;
                    String timeStr=String.valueOf(time)+"分钟";
                    Rank rank=new Rank(url,name,timeStr);
                    rankList.add(rank);
                }
                Log.e("fyl",result);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o){
            rankAdapter=new RankAdapter(RankActivity.this,R.layout.rank_list_ietm,rankList);
            listView.setAdapter(rankAdapter);
            rankAdapter.notifyDataSetChanged();

        }

    }
}
