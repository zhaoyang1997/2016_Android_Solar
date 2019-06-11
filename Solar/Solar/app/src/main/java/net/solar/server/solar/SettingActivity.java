package net.solar.server.solar;

import android.app.Service;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import net.solar.server.solar.adapter.RingAdapter;
import net.solar.server.solar.entity.Bells;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {

    private ListView listView;
    private List<Bells> bells = new ArrayList<>();
    private RingAdapter ringAdapter;
    private AssetFileDescriptor fileDescriptor;
    private MediaPlayer mediaPlayer;
    private AssetManager assetManager;
    private int current = 1;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        listView = findViewById(R.id.listView);
        textView = findViewById(R.id.tv_Ring);
        Bells bell1 = new Bells();
        bell1.setName("铃声1");
        Bells bell2 = new Bells();
        bell2.setName("铃声2");
        bells.add(bell1);
        bells.add(bell2);
        ringAdapter = new RingAdapter(this,R.layout.activity_setting_list,bells);
        listView.setAdapter(ringAdapter);
        init();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!mediaPlayer.isPlaying()) {
                    //启动播放
                    mediaPlayer.start();
                } else {
                    mediaPlayer.pause();
                }
                //播放错误事件
                mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
                //播放完成事件
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.reset();
                        current++;
                        if (current > 3) {
                            current = 1;
                        }
                        try {
                            fileDescriptor = assetManager.openFd("m" + current + ".mp3");
                            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                                    fileDescriptor.getStartOffset(),
                                    fileDescriptor.getLength());
                            mediaPlayer.prepare();
                            mediaPlayer.start();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

    /**
     * 打开震动模式
     */
    private void vibratorButton() {
        Vibrator vibrator = (Vibrator) getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{0, 2000}, -1);
    }

    /**
     * 添加铃声
     */
    private void init() {
        try {
            assetManager = getAssets();
            fileDescriptor = assetManager.openFd("m1.mp3");
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(fileDescriptor.getFileDescriptor(),
                    fileDescriptor.getStartOffset(),
                    fileDescriptor.getLength());
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
