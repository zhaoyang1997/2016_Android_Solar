package net.solar.server.solar.fragment;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import net.solar.server.solar.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ThirdFragment extends Fragment {
    private BarChart barChart;
    private LineChart lineChart;
    private PieChart pieChart;
    private static int userId;
    private OkHttpClient okHttpClient=new OkHttpClient();
    List dayentries=new ArrayList();
    List monthentries=new ArrayList();
    List yearentries=new ArrayList();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_statistics, container, false);
        barChart=view.findViewById(R.id.barchart);
        lineChart=view.findViewById(R.id.linechart);
        pieChart=view.findViewById(R.id.piechart);
        Sum sum=new Sum();
       // sum.execute();

        return view;
    }

    private static String getYear(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        String s = sdf.format(new Date());
        return s;

    }

    private static String getMonth(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM");
        String s = sdf.format(new Date());
        return s;
    }
    private static String getDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd");
        String s = sdf.format(new Date());
        return s;
    }

    private void setBarChart(){
        barChart.setBackgroundColor(getResources().getColor(R.color.colorBlack));
        barChart.setDrawGridBackground(false);
        barChart.setDrawBarShadow(false);
        barChart.setHighlightFullBarEnabled(false);
        barChart.setDrawBorders(true);//显示边界
        XAxis xAxis=barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(10f);
        xAxis.setDrawGridLines(false);
        YAxis leftAxis=barChart.getAxisLeft();
        YAxis rightAxis=barChart.getAxisRight();
        rightAxis.setEnabled(false);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setTextColor(Color.WHITE);
        List entries=monthentries;
        BarDataSet barDataSet=new BarDataSet(entries,"");
        barDataSet.setColors(getResources().getColor(R.color.barc));
        barDataSet.setFormLineWidth(1f);
        barDataSet.setDrawValues(false);
        ArrayList dataSets=new ArrayList();
        dataSets.add(barDataSet);
        BarData data=new BarData(dataSets);
        xAxis.setLabelCount(entries.size()-1,false);
        barChart.getDescription().setEnabled(false);
        barChart.setData(data);

    }
    private void setLineChart(){
        lineChart.getDescription().setEnabled(false);
        lineChart.setDrawGridBackground(false);
        lineChart.setScaleEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        Legend legend=lineChart.getLegend();
        legend.setEnabled(false);
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.WHITE);
        xAxis.setTextSize(10f);
        xAxis.setDrawGridLines(false);
        YAxis yAxis=lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setDrawGridLines(false);
        yAxis.setTextColor(Color.WHITE);
        List entries = dayentries;
        LineDataSet dataSet=new LineDataSet(entries,"");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setColor(getResources().getColor(R.color.linec));
        dataSet.setFillColor(getResources().getColor(R.color.colorBlack));
        dataSet.setFillAlpha(200);
        dataSet.setDrawFilled(true);
        dataSet.setDrawValues(false);
        dataSet.setCircleRadius(3f);
        dataSet.setLineWidth(1f);
        LineData lineData=new LineData(dataSet);
        //添加的图表中
        lineChart.setData(lineData);
    }
    private void setPieChart(){
        pieChart.setUsePercentValues(true);//设置value是否显示百分数
        pieChart.getDescription().setEnabled(false);//设置描述
        pieChart.setDrawHoleEnabled(false);//是否绘制饼状图中间的圆
        pieChart.setHighlightPerTapEnabled(true);//选中是否高亮
        List<PieEntry> entries=new ArrayList<>();
        entries.add(new PieEntry(40,"番茄时钟"));
        entries.add(new PieEntry(50,"任务完成"));
        entries.add(new PieEntry(10,"未完成任务"));
        //设置数据
        PieDataSet dataSet=new PieDataSet(entries,"最近一年");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        //数据和颜色
        ArrayList<Integer> colors=new ArrayList<>();
        colors.add(getResources().getColor(R.color.pie1));
        colors.add(getResources().getColor(R.color.pie2));
        colors.add(getResources().getColor(R.color.pie3));
        dataSet.setColors(colors);
        PieData data=new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.BLACK);
        pieChart.setData(data);
        pieChart.highlightValue(null);
        pieChart.invalidate();
        pieChart.setDrawEntryLabels(true);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(12f);
        Legend l=pieChart.getLegend();
        l.setEnabled(false);

    }

    public class Sum extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            String result;
            String path = "http://10.7.89.47:8080/SolorService/static";
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            bodyBuilder.add("day", getDay());
            bodyBuilder.add("month", getMonth());
            bodyBuilder.add("year", getYear());
            bodyBuilder.add("userId", "1");
            FormBody body = bodyBuilder.build();
            Request request = new Request.Builder().post(body)
                    .url(path).build();
            Call call = okHttpClient.newCall(request);
            Response response = null;
            try {
                response = call.execute();
                result = response.body().string();
                Log.e("fyl",result);
                JSONArray jsonArray=new JSONArray(result);
                for(int i=0;i<7;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    Integer data=jsonObject.getInt("w");
                    dayentries.add(new Entry(i+1,data));
                }
                for(int i=7,j=0;i<jsonArray.length();i++,j++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    int data=jsonObject.getInt("m");
                    monthentries.add(new BarEntry(j+1,data));
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Object o){
            setPieChart();
            setBarChart();
            setLineChart();
        }
    }

}
