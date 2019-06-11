package net.solar.server.solar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class StartActivity extends AppCompatActivity {

    private ViewFlipper mViewFlipper = null;
    private ImageView imageDot;
    private float x1,x2;
    private int dot = 0;
    private int[] dots={R.drawable.b3,R.drawable.b1,R.drawable.b2};
    private Button btnStartNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStartNow = findViewById(R.id.btn_start_now);
        mViewFlipper = findViewById(R.id.details);
       // imageDot = findViewById(R.id.dot);
        mViewFlipper.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("NewApi")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //TODO 触摸时按下
                    x1 = event.getX();
                }
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //TODO 触摸时抬起
                    x2 = event.getX();
                    if (x1 - x2 < -300) {
                        x1 = 0;
                        x2 = 0;
                        mViewFlipper.setInAnimation(StartActivity.this,R.anim.right);
                        mViewFlipper.setOutAnimation(StartActivity.this,R.anim.left);
                        mViewFlipper.showPrevious();
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(550);
                              //      last();//休眠3秒
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                /**
                                 * 要执行的操作
                                 */
                                btnStartNow.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }.start();
                    } else if (x1 - x2 > 300) {
                        x1 = 0;
                        x2 = 0;
                        mViewFlipper.setInAnimation(StartActivity.this,R.anim.left_to_right);
                        mViewFlipper.setOutAnimation(StartActivity.this,R.anim.right_to_left);
                        mViewFlipper.showNext();
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                try {
                                    Thread.sleep(550);
                                  //  next();//休眠3秒
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                /**
                                 * 要执行的操作
                                 */
                                btnStartNow.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                                        startActivity(intent);
                                        StartActivity.this.finish();
                                    }
                                });
                            }
                        }.start();

                    }
                }
                return true;
            }
        });

    }

    private void next(){
        switch (dot){
            case 0:
                imageDot.setImageResource(dots[1]);
                dot = 1;
                break;
            case 1:
                imageDot.setImageResource(dots[2]);
                dot = 2;
                break;
            case 2:
                imageDot.setImageResource(dots[0]);
                dot = 0;
                break;
        }
    }
    private void last(){
        switch (dot%3){
            case 0:
                imageDot.setImageResource(dots[2]);
                dot = 2;
                break;
            case 1:
                imageDot.setImageResource(dots[0]);
                dot = 0;
                break;
            case 2:
                imageDot.setImageResource(dots[1]);
                dot = 1;
                break;
        }
    }

}
