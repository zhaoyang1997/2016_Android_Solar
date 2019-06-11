package net.solar.server.solar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.solar.server.solar.R;

import java.util.List;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private List<String> tasks;

    public CustomAdapter(Context context, List<String> tasks) {
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public int getCount() {
        return tasks.size();
    }

    @Override
    public Object getItem(int position) {
        return tasks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null;
        //1.加载布局
        if(null == convertView){
            convertView = LayoutInflater.from(context).inflate(R.layout.task_item,null);
            vh  = new ViewHolder();
            vh.taskName = convertView.findViewById(R.id.task_name);
            //缓存ViewHolder对象
            convertView.setTag(vh);
        }else{
            //获取缓存的ViewHolder对象
            vh = (ViewHolder) convertView.getTag();
        }
        //2.填充数据
        String name = tasks.get(position);
        vh.taskName.setText(name);
        return convertView; // 返回列表项

    }
    //定义内部类ViewHolder，用于缓存item控件
    class ViewHolder{
        private TextView taskName;
    }
}
