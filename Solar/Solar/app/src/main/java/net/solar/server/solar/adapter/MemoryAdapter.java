package net.solar.server.solar.adapter;

import android.app.Activity;
import android.renderscript.Element;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.solar.server.solar.R;
import net.solar.server.solar.entity.Day;

import java.util.ArrayList;
import java.util.List;

public class MemoryAdapter extends BaseAdapter {
    private Activity context;
    private int item_layout_id;
    private List<Day> list=new ArrayList<Day>();

    public MemoryAdapter(Activity context, int item_layout_id, List list) {
        this.context = context;
        this.item_layout_id = item_layout_id;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(null==convertView){
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            convertView=layoutInflater.inflate(item_layout_id,null);
        }
        TextView textView=convertView.findViewById(R.id.textView);
        textView.setText(list.get(position).getName());
        TextView daty=convertView.findViewById(R.id.daty);
        daty.setText(list.get(position).getDay());

        return convertView;
    }


}
