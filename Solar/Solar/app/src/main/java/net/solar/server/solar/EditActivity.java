package net.solar.server.solar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class EditActivity extends AppCompatActivity {
    private EditText taskName;
    private EditText taskTime;
    private Button btnSave;
    private ImageView reback;
    private String name;
    private String time;
    private int position;
    private String AUTOURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        AUTOURL = getResources().getString(R.string.client_url);

        taskName = findViewById(R.id.taskname);
        taskTime = findViewById(R.id.tasktime);
        btnSave = findViewById(R.id.btnSave);
        reback = findViewById(R.id.reback);

        Intent intent =getIntent();

        String tName = intent.getStringExtra("taskName");
        int tTime = intent.getIntExtra("taskTime",1);
        position = intent.getIntExtra("position",1);

        taskName.setText(tName);
        taskTime.setText(tTime+"");

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditTask editTask = new EditTask();
                editTask.execute();
            }
        });

        reback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.finish();
            }
        });
    }


    public class EditTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            name = taskName.getText().toString().trim();
            time = taskTime.getText().toString().trim();
            Log.e("$$$$$$$$$$$$$",name+" "+time+" "+position);
            OkHttpClient okHttpClient = new OkHttpClient();
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("Tname", name);
            builder.add("Ttime", time);
            builder.add("userId", String.valueOf(1));
            builder.add("position", String.valueOf(position+1));
            FormBody body = builder.build();
            final Request request = new Request.Builder()
                    .url("http://10.7.89.47:8080/SolarService/task/updateTask")
                    .post(body)
                    .build();
            final Call call = okHttpClient.newCall(request);
            try {
                Response response = call.execute();
                String mes = response.body().string();
                Log.e("onResponse********", mes);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            Intent intent = new Intent();
            intent.putExtra("Tname",name);
            intent.putExtra("position",position);
            EditActivity.this.setResult(333,intent);
            EditActivity.this.finish();
        }
    }
}
