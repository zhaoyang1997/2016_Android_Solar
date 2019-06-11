package net.solar.server.solar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.solar.server.solar.util.EmailUtil;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText mViewEmail;
    private EditText mViewYzm;
    private Button mViewCode;
    private Button tijiao;
    private String emailTo;//收件人邮箱地址
    private String yamCode;
    private String yamCodeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        OnClickListenerImpl listener = new OnClickListenerImpl();
        mViewEmail=findViewById(R.id.email);
        mViewYzm = findViewById(R.id.yangzhengma);
        mViewCode=findViewById(R.id.f_get_yanzhengma);
        tijiao = findViewById(R.id.tijiao);
        mViewCode.setOnClickListener(listener);
        tijiao.setOnClickListener(listener);

    }

    private class OnClickListenerImpl implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.f_get_yanzhengma:
                    emailTo = mViewEmail.getText().toString().trim();
                    if ( TextUtils.isEmpty(emailTo)) {
                        Toast.makeText(ForgetPasswordActivity.this, "请输入邮箱", Toast.LENGTH_LONG).show();
                        return;
                    }
                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            //第一个参数需改成真实接收消息用户的邮箱地址
//                        EmailUtil.getInstance().sendEmail(emailTo, "发送验证码","***发送来验证码:028469,如非本人操作请忽略。");//耗时操作，必须在线程中进行

                            yamCode = EmailUtil.verifyCode();
                            EmailUtil.getInstance().sendEmail(emailTo, "发送验证码", "Solar发送来验证码:"+yamCode+",如非本人操作请忽略。");//耗时操作，必须在线程中进行

                            //线程中弹出Toast
                            Looper.prepare();
                            Toast.makeText(ForgetPasswordActivity.this, "验证码已发送", Toast.LENGTH_LONG).show();
                            Looper.loop();
                        }
                    };
                    thread.start();
                    break;
                case R.id.tijiao:
                    yamCodeInput = mViewYzm.getText().toString();
                    if(yamCodeInput.equals(yamCode)){
                        Intent intent = new Intent();
                        intent.setClass(ForgetPasswordActivity.this,ChangePasswordActivity.class);
                        intent.putExtra("email",emailTo);
                        startActivity(intent);
                    }
            }

        }

    }

}
