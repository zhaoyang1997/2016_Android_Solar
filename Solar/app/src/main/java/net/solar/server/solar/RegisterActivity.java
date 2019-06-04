package net.solar.server.solar;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {
 private Button btn;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etCPassword;
    private TextView tvPassword;
    private String password;
    private String cpassword;
    private String email;
    private String tvText;
    private String userName;
    private OkHttpClient okHttpClient;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
        okHttpClient=new OkHttpClient();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean u=nameEqual();
                if (u) {
                    boolean b=Equal();
                    if(b){
                        boolean e=emailEqual();
                        if(e){
                            RegisterTask registerTask=new RegisterTask();
                            registerTask.execute();

                        }
                    }
                }
                }

        });


    }
    private void init(){
        btn=findViewById(R.id.queren);
        etEmail=findViewById(R.id.etEmail);
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        etCPassword=findViewById(R.id.etCPassword);
        tvPassword=findViewById(R.id.tvPassword);
    }

    /*
    判断两次密码输入是否一致
     */
    public boolean Equal(){
        password = etPassword.getText().toString();
        cpassword = etCPassword.getText().toString();
        if (!password.equals("")){
            if(password.length()<6){
                tvPassword.setText("密码长度不能小于6位");
                return false;
            }else{
                if (password.equals(cpassword)){
                    tvPassword.setText(" ");
                    return true;
                }else {
                    tvPassword.setText("两次密码输入不一致");
                    return false;
                }
            }
        }else {
            tvPassword.setText("密码不能为空");
            return false;
        }
    }

    /*
    *判断邮箱格式是否正确
     */
    public boolean emailEqual(){
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        email=etEmail.getText().toString();
        if(email.matches(str)){
            return true;
        }
        tvText=tvPassword.getText().toString();
        tvPassword.setText(tvText+"   邮箱格式错误");
        return false;
    }
    /*
    * 判断用户名是否为空
    */
    public boolean nameEqual(){
        userName=etUsername.getText().toString();
        if(!userName.equals("")){
            return true;
        }
        tvText = tvPassword.getText().toString();
        tvPassword.setText(tvText + "  用户名不能为空");
        return false;

    }

    public class RegisterTask extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String path = "http://10.7.89.47:8080/SolorService/register";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("email", email);
            bodyBuilder.add("userName", userName);
            bodyBuilder.add("password", password);
            FormBody body = bodyBuilder.build();
            final Message message=Message.obtain();
            Request request = new Request.Builder().post(body)
                    .url(path).build();
            Call call = okHttpClient.newCall(request);
            Response response = null;
            try {
                response = call.execute();
                result = response.body().string();
                Log.e("fyl", result);

                if(result.equals("1")){
                  message.what=1;
                  handler.sendMessage(message);
                }
                else if(result.equals("2")){
                   message.what=2;
                   handler.sendMessage(message);
                }else if(result.equals("3")){
                    message.what=3;
                    handler.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        Handler handler=new Handler(){
            public void handleMessage(Message mes){
                switch (mes.what){
                    case 1:
                        Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(intent);
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),
                                "邮箱已经存在，请返回登录",
                                Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),
                                "用户名存在",
                                Toast.LENGTH_LONG).show();
                        break;
                }
                super.handleMessage(mes);
            }
        };

    }


}
