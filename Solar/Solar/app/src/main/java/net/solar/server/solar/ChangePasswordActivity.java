
package net.solar.server.solar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private EditText newPaWd;
    private EditText confirmPaWd;
    private String newPaWdCode;
    private String confirmPaWdCode;
    private String emailCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent intent = getIntent();

        newPaWd = findViewById(R.id.new_PaWd);
        confirmPaWd = findViewById(R.id.confirm_PaWd);

        newPaWdCode = newPaWd.getText().toString();
        confirmPaWdCode = confirmPaWd.getText().toString();
        emailCode = intent.getStringExtra("email");

        okHttpClient = new OkHttpClient();

    }

    private void changePsWd(){
        new Thread(){
            public void run(){

                FormBody.Builder builder = new FormBody.Builder();
                builder.add("email",emailCode);
                builder.add("password",newPaWdCode);
                FormBody body = builder.build();

                Request request = new Request.Builder()
                        .post(body)
                        .url("http://10.7.89.229:8080")
                        .build();
                Call call = okHttpClient.newCall(request);

                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //当请求失败时自动回调该方法
                        Log.i("lww", "请求失败");
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        //当成功提交请求并返回响应时自动回调该方法
                        Log.i("lww", response.body().string());
                    }
                });
            }
        }.start();
    }
    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.queren:
                    if(newPaWdCode.equals(confirmPaWdCode)){
                        changePsWd();
                    }
            }

        }
    }

}
