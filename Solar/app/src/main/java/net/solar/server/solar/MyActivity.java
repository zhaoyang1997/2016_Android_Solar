package net.solar.server.solar;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import net.solar.server.solar.entity.User;
import net.solar.server.solar.util.CircleImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MyActivity extends AppCompatActivity {

    //控件变量
    private CircleImageView ivUserImg;
    private TextView tvSet;
    private TextView tvScore;
    private TextView tvRegister;
    private TextView tvOut;
    private TextView tvChange;
    private View popupView;
    private TextView tvName;
    private PopupWindow window;

    //数据变量
    private OkHttpClient okHttpClient;
    private String picturePath;
    private String filename;
    private Bitmap resultImg;
    private String resultUserName;
    private int userId=1;

    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        AutoClick autoClick = new AutoClick();
        okHttpClient = new OkHttpClient();

        ivUserImg = findViewById(R.id.ivUserImg);
        tvSet = findViewById(R.id.tvSet);
        tvScore = findViewById(R.id.tvScore);
        tvRegister = findViewById(R.id.tvRegister);
        tvOut = findViewById(R.id.tvOut);
        tvChange = findViewById(R.id.tvChange);
        tvName = findViewById(R.id.tvName);

        ivUserImg.setOnClickListener(autoClick);
        tvSet.setOnClickListener(autoClick);
        tvScore.setOnClickListener(autoClick);
        tvRegister.setOnClickListener(autoClick);
        tvOut.setOnClickListener(autoClick);
        tvChange.setOnClickListener(autoClick);

        getUserDate(1);

    }

    /**
     * 我的页面 点击事件
     */
    class AutoClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                //跳转到设置页面
                case R.id.tvSet:
                    break;
                //跳转到 切换账号
                case R.id.tvChange:
                    break;
                //跳转到 注册页面
                case R.id.tvRegister:
                    break;
                //跳转到 积分详情
                case R.id.tvScore:
                    break;
                //退出登录
                case R.id.tvOut:
                    break;
                case R.id.ivUserImg:// 点击更改头像
                    popupView = MyActivity.this.getLayoutInflater().inflate(R.layout.popup_window_view,null);
                    window = new PopupWindow(popupView);
                    window.setWidth(ActionBar.LayoutParams.MATCH_PARENT);
                    window.setHeight(ActionBar.LayoutParams.MATCH_PARENT);
                    window.setAnimationStyle(R.style.popup_window_anim);
                    window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#40FFFFFF")));
                    window.setFocusable(true);
                    window.setOutsideTouchable(true);
                    window.update();
                    window.showAtLocation(findViewById(R.id.parentLayout), Gravity.BOTTOM,0,0);

                    Button btnPickAlbum = popupView.findViewById(R.id.btnPickAlbum);
                    Button btnTakePhoto = popupView.findViewById(R.id.btnTakePhoto);
                    Button btnCancel = popupView.findViewById(R.id.btnCancel);
                    PopupItemClick popupItemClick = new PopupItemClick();
                    btnPickAlbum.setOnClickListener(popupItemClick);
                    btnTakePhoto.setOnClickListener(popupItemClick);
                    btnCancel.setOnClickListener(popupItemClick);
                    break;
            }
        }
    }

    /**
     * 我的页面和客户端的数据传输（上传文件，获取用户头像和名称）
     */
    private void upLoadImg(File file){

        MediaType mediaType = MediaType.parse("application/jpeg");
        RequestBody requestBody = RequestBody.create(
                mediaType,
                file
        );
        Request request = new Request.Builder()
                .url("http://10.7.89.78:8081/MyBatisDemo8/user/index")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Log.i("zn--test--failure","上传图片失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("zn--test--response",response.body().string());
            }
        });

    }

    /**
     * 拍照获取头像资源
     */
    private void getImgByCamera(){
        //打开摄像头应用进行拍照
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCamera,8888);
    }

    /**
     * 从相册获取上传的头像-图片资源
     */
    private void getImgFromAlbum(){
        //打开本地相册
        Intent openAlbum = new Intent(
                Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //设定结果返回
        startActivityForResult(openAlbum, RESULT_LOAD_IMAGE);
    }

    /**
     * 相册和相机的回调
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //获取摄像头拍照的结果
        if(requestCode == 8888 & resultCode == RESULT_OK){
            Bitmap pic = (Bitmap) data.getExtras().get("data");
            ivUserImg.setImageBitmap(pic);

            filename="temp.jpg";
            String path = Environment.getExternalStorageDirectory() + "/Ask";
            File dirFile = new File(path);
            if(!dirFile.exists()){
                dirFile.mkdir();
            }
            File myCaptureFile = new File(path + "temp.jpg");
            BufferedOutputStream bos = null;
            try {
                bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
                pic.compress(Bitmap.CompressFormat.JPEG, 80, bos);
                bos.flush();
                bos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            upLoadImg(myCaptureFile);

            MediaStore.Images.Media.insertImage(getContentResolver(), pic, "title", "description");
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ Environment.getExternalStorageDirectory())));

        }

        //保存照片到相册
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            //获取返回的数据，这里是android自定义的Uri地址
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };
            //获取选择照片的数据视图
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();
            //从数据视图中获取已选择图片的路径
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);

            picturePath = cursor.getString(columnIndex);
            Log.i("zn",picturePath);
            cursor.close();
            //将图片显示到界面上
            ivUserImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            filename=picturePath.substring(picturePath.lastIndexOf("/")+1,picturePath.length());
            Log.i("zn---0.5",filename.toString());
            Log.i("zn---1",picturePath.toString());
            upLoadImg(new File(picturePath));
        }
    }

    /**
     * PopupWindow的选项的点击事件
     */
    private class PopupItemClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPickAlbum:
                    getImgFromAlbum();
                    window.dismiss();
                    break;
                case R.id.btnTakePhoto:
                    getImgByCamera();
                    window.dismiss();
                    break;
                case R.id.btnCancel:
                    window.dismiss();
                    break;
            }
        }
    }


    /**
     * 向服务器查询个人主页信息
     */

    private void getUserDate(int uid){
        OkHttpClient okHttpClient = new OkHttpClient();
        MediaType type = MediaType.parse("application/text");
        String id = String.valueOf(uid);
        RequestBody requestBody = RequestBody.create(type,id);
        Request request = new Request.Builder()
                .url("http://10.7.89.78:8081/MyBatisDemo8/user/find")
                .post(requestBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("zn--test--failure","传送到服务器");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()){
                    String message = response.body().string();
                    Log.e("zn--test--response",message);
                    Gson gson = new Gson();
                    User user = gson.fromJson(message,User.class);
                    String imgPath = "http://10.7.89.78:8081/MyBatisDemo8/images/"+user.getUserHead();
                    resultImg = BitmapFactory.decodeStream(new URL(imgPath).openStream());
                    resultUserName = user.getUserName();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvName.setText(resultUserName);
                            ivUserImg.setImageBitmap(resultImg);
                        }
                    });
                }




            }
        });
    }

}
