package net.solar.server.solar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class LoginActivity extends AppCompatActivity {
    private ImageView ivEye;
    private EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ivEye = findViewById(R.id.ivEye);
        etPassword = findViewById(R.id.etPassword);
        ivEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etPassword.getInputType() == 128){
                    etPassword.setInputType(129);
                    ivEye.setImageResource(R.drawable.login_eye1);
                }else{
                    etPassword.setInputType(128);
                    ivEye.setImageResource(R.drawable.login_eye2);
                }
                //设置光标的位置到末尾
                etPassword.setSelection(etPassword.getText().length());

                Intent intent = new Intent(LoginActivity.this,IndexActivity.class);
                startActivity(intent);
            }
        });

    }
}
