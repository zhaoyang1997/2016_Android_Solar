package net.solar.server.solar;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import net.solar.server.solar.entity.Bells;

import java.util.ArrayList;
import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private TextView textView;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

    }
    /**
     * 创建铃声列表Adapter
     */
    private class SettingAdapter extends BaseAdapter {
        private Context context;
        private int itemLayout;
        private List<Bells> bells = new ArrayList<>();

        public SettingAdapter(Context context,int itemLayout,List<Bells> bells){
            this.context = context;
            this.itemLayout = itemLayout;
            this.bells = bells;
        }

        @Override
        public int getCount() {
            return bells.size();
        }

        @Override
        public Object getItem(int position) {
            return bells.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if(null==convertView){
                LayoutInflater layoutInflater=LayoutInflater.from(context);
                convertView=layoutInflater.inflate(itemLayout,null);
            }
            textView = convertView.findViewById(R.id.setting_lv);
//            textView.setText(bells.getName());
            return convertView;
        }
    }
}
