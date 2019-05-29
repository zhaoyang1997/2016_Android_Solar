package net.solar.server.solar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

import net.solar.server.solar.entity.User;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private ImageView ivEye;
    private EditText etPassword;
    private EditText etUsername;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnForgetPassword;
    private OnClickListener listener;
    private static String s1;
    private static String s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ivEye = findViewById(R.id.ivEye);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        btnForgetPassword = findViewById(R.id.btnForgetPassword);
        listener = new OnClickListener();

        btnLogin.setOnClickListener(listener);
        btnForgetPassword.setOnClickListener(listener);
        btnRegister.setOnClickListener(listener);
        ivEye.setOnClickListener(listener);

        findMessage();

    }
    public void findMessage(){
        new Thread(){
            @Override
            public void run() {
                OkHttpClient okHttpClient = new OkHttpClient();
                MediaType type = MediaType.parse("application/text");
                String id = "1";
                RequestBody body = RequestBody.create(type,id);
                final Request request = new Request.Builder()
                        .url("http://10.7.89.37:8080/SolarService/user/login")
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
                            User u = gson.fromJson(messsage,User.class);
                            s1 = u.getEmail();
                            s2 = u.getPassword();
                        }
                    }
                });
            }
        }.start();
    }
    class OnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.ivEye:                         //密码显示状态
                    if(etPassword.getInputType() == 128){
                        etPassword.setInputType(129);
                        ivEye.setImageResource(R.drawable.login_eye1);
                    }else{
                        etPassword.setInputType(128);
                        ivEye.setImageResource(R.drawable.login_eye2);
                    }
                    //设置光标的位置到末尾
                    etPassword.setSelection(etPassword.getText().length());
                    break;
                case R.id.btnLogin:                    //登录
                    String userName = etUsername.getText().toString().trim();
                    String password = etPassword.getText().toString().trim();
                    if("".equals(userName) || "".equals(password)){
                        Toast.makeText(getApplicationContext(),"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                    Log.e("********",s1+" "+s2);
                    if(userName.equals(s1) && password.equals(s2)){
                        Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(),"用户名或密码不正确",Toast.LENGTH_SHORT).show();
                    }
                    break;
                case R.id.btnRegister:                     //注册
                    Intent intent1 = new Intent(LoginActivity.this,RegisterActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.btnForgetPassword:              //忘记密码
                    Intent intent2 = new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    }
}
