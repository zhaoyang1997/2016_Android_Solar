package net.solar.server.solar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.solar.server.solar.R;
import net.solar.server.solar.entity.ScoreDetails;

import java.util.List;

public class ScoreAdapter extends BaseAdapter {

    private Context context;
    private List<ScoreDetails> list;

    public ScoreAdapter(Context context,List<ScoreDetails> list){
        this.context = context;
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
        ViewHolder viewHolder = null;

        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.score_item,null);
            viewHolder  = new ViewHolder();
            viewHolder.tvName = convertView.findViewById(R.id.tv_name);
            viewHolder.tvScore = convertView.findViewById(R.id.tv_score);
            //缓存ViewHolder对象
            convertView.setTag(viewHolder);
        }else{
            //获取缓存的ViewHolder对象
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //2.填充数据
        ScoreDetails details = list.get(position);
        viewHolder.tvName.setText(details.getName());
        viewHolder.tvScore.setText("+"+details.getCount());
        return convertView; // 返回列表项

    }

    //定义内部类ViewHolder，用于缓存item控件
    class ViewHolder{
        private TextView tvName;
        private TextView tvScore;
    }

}
