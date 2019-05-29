package net.solar.server.solar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import net.solar.server.solar.fragment.FirstFragment;
import net.solar.server.solar.fragment.SecondFragment;
import net.solar.server.solar.fragment.ThirdFragment;

public class MainActivity extends FragmentActivity {
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private ThirdFragment thirdFragment;
    private FragmentManager fragmentManager;
    private LinearLayout tab1;
    private LinearLayout tab2;
    private LinearLayout tab3;
    private Fragment currentFragment=new Fragment();
    private FragmentTransaction ft;
    private FragmentTransaction ft1;
    private FragmentTransaction ft2;
    private FragmentTransaction ft3;
    private FragmentTransaction ft4;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private int userId;
    public static  int USER_ID;
    private int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取视图组件对象
        finViews();
        tab1.bringToFront();
        tab2.bringToFront();
        tab3.bringToFront();
        //绑定点击事件监听器
        setLinstener();
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        thirdFragment=new ThirdFragment();
        imageView1=findViewById(R.id.image1);
        imageView2=findViewById(R.id.image2);
        imageView3=findViewById(R.id.image3);
        /*
         * 添加fragment 设置fragment的tag值
         * */


        fragmentManager= getSupportFragmentManager();
        ft1=fragmentManager.beginTransaction();
        ft1.add(R.id.content,thirdFragment,"fragment3").hide(thirdFragment).commit();
        fragmentManager= getSupportFragmentManager();
        ft=fragmentManager.beginTransaction();

        fragmentManager= getSupportFragmentManager();
        ft2=fragmentManager.beginTransaction();

        showFragment(firstFragment);
        imageView1.setImageResource(R.drawable.fragment1_tab1_2);
        imageView2.setImageResource(R.drawable.fragment2_tab2_1);
        imageView3.setImageResource(R.drawable.fragment3_tab3_3);
    }
    /*
     * 获取tab
     * */
    private void finViews(){
        tab1=findViewById(R.id.tab1);
        tab2=findViewById(R.id.tab2);
        tab3=findViewById(R.id.tab3);
    }
    /*
     *给tab添加点击事件
     */

    private void setLinstener(){
        TabClickLinstener linstener=new TabClickLinstener();
        tab1.setOnClickListener(linstener);
        tab2.setOnClickListener(linstener);
        tab3.setOnClickListener(linstener);
    }
    /*
     *  tab的点击监听器
     * */
    private class TabClickLinstener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tab1:
                    showFragment(firstFragment);
                    imageView1.setImageResource(R.drawable.fragment1_tab1_2);
                    imageView2.setImageResource(R.drawable.fragment2_tab2_1);
                    imageView3.setImageResource(R.drawable.fragment3_tab3_3);
                    break;
                case R.id.tab2:
                    showFragment(secondFragment);
                    imageView1.setImageResource(R.drawable.fragment1_tab1_1);
                    imageView2.setImageResource(R.drawable.fragment2_tab2_2);
                    imageView3.setImageResource(R.drawable.fragment3_tab3_3);
                    break;
                case R.id.tab3:
//                    ThirdFragment.Sum sum=new ThirdFragment.Sum();
//                    sum.execute();
                    showFragment(thirdFragment);
                    imageView1.setImageResource(R.drawable.fragment1_tab1_1);;
                    imageView2.setImageResource(R.drawable.fragment2_tab2_1);
                    imageView3.setImageResource(R.drawable.fragment3_tab3_4);
                    break;
            }
        }
    }
    /*
     * 自定义fragmenttabhost
     * */
    private void showFragment(Fragment fragment){
        //创建Fragment事务
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        if(fragment != currentFragment){
            //隐藏正在显示的Fragment
            transaction.hide(currentFragment);
            if(!fragment.isAdded()){
                transaction.add(R.id.content,fragment);
            }
            //显示Fragment
            transaction.show(fragment);
            //提交事务
            transaction.commit();
            currentFragment=fragment;
        }
    }
}
