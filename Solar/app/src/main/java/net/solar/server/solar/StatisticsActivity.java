package net.solar.server.solar;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

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
import com.github.mikephil.charting.utils.ColorTemplate;

import net.solar.server.solar.R;

import java.util.ArrayList;
import java.util.List;

public class StatisticsActivity extends AppCompatActivity {
    private BarChart barChart;
    private LineChart lineChart;
    private PieChart pieChart;
    private static int userId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        int height = metric.heightPixels;   // 屏幕高度（像素）
        barChart=findViewById(R.id.barchart);
        lineChart=findViewById(R.id.linechart);
        pieChart=findViewById(R.id.piechart);
        setPieChart();
        setBarChart();
        setLineChart();

    }
    private void setBarChart(){
        barChart.setBackgroundColor(getResources().getColor(R.color.background));
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
        ArrayList entries=new ArrayList();
        entries.add(new BarEntry(1,2));
        entries.add(new BarEntry(2,8));
        entries.add(new BarEntry(3,5));
        entries.add(new BarEntry(4,4));
        entries.add(new BarEntry(5,8));
        entries.add(new BarEntry(6,3));
        entries.add(new BarEntry(7,7));
        BarDataSet barDataSet=new BarDataSet(entries,"");
        barDataSet.setColors(new int[]{Color.rgb(127,255,0)
                });
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
        List entries=new ArrayList();
        entries.add(new Entry(1,15));
        entries.add(new Entry(2,20));
        entries.add(new Entry(3,25));
        entries.add(new Entry(4,16));
        entries.add(new Entry(5,20));
        entries.add(new Entry(7,26));
        entries.add(new Entry(8,21));
        entries.add(new Entry(9,25));
        entries.add(new Entry(10,20));
        LineDataSet dataSet=new LineDataSet(entries,"");
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setCubicIntensity(0.2f);
        dataSet.setCircleColor(Color.rgb(154,192,205));
        dataSet.setColor(Color.rgb(255,106,106));
        dataSet.setFillColor(Color.rgb(255,255,0));
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
        for(int c: ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);
        for(int c:ColorTemplate.JOYFUL_COLORS)
            colors.add(c);
        for(int c:ColorTemplate.COLORFUL_COLORS)
            colors.add(c);
        colors.add(ColorTemplate.getHoloBlue());
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
}
