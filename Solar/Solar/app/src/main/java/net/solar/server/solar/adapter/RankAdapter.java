package net.solar.server.solar.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import net.solar.server.solar.R;
import net.solar.server.solar.entity.Rank;

import java.util.ArrayList;
import java.util.List;

public class RankAdapter extends BaseAdapter {
    private Activity context;
    private int item_layout_id;
    private List<Rank> list=new ArrayList<>();
    public RankAdapter(Activity context, int item_layout_id, List list) {
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
        ViewHolder holder=null;
        if(null==convertView){
            LayoutInflater layoutInflater= LayoutInflater.from(context);
            convertView=layoutInflater.inflate(item_layout_id,null);
            holder=new ViewHolder();
            holder.headerImg=convertView.findViewById(R.id.img);
            holder.name=convertView.findViewById(R.id.name);
            holder.time=convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        RequestOptions options=new RequestOptions()
                .circleCrop();
        Glide.with(context)
                .load(list.get(position).getImg())
                .apply(options)
                .into(holder.headerImg);
        holder.name.setText(list.get(position).getUserName());
        holder.time.setText(list.get(position).getTime());

        return convertView;
    }


    static class ViewHolder{
        ImageView headerImg;
        TextView name;
        TextView time;
    }
}
