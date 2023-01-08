package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class bieudo extends AppCompatActivity {
    PieChart pieChart;
    Button bt;
    private ArrayList<String> list1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bieudo);
        pieChart=(PieChart)findViewById(R.id.bieudo);
        bt=(Button)findViewById(R.id.btThoatBieuDo);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller=callerIntent.getBundleExtra("MyPackageBD");

        ArrayList<PieEntry> visitors=new ArrayList<>();
        visitors.add(new PieEntry(packageFromCaller.getInt("Dung"),"Đúng"));
        visitors.add(new PieEntry(packageFromCaller.getInt("Sai"),"Sai"));

        PieDataSet pieDataSet=new PieDataSet(visitors,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16f);

        PieData pieData=new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterText("Biểu đồ"+"\n"+(packageFromCaller.getInt("Dung")+packageFromCaller.getInt("Sai"))+" câu");
        pieChart.setCenterTextSize(20f);
        pieChart.animate();
        AddFromFile1();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(bieudo.this,R.raw.click );
                mediaPlayer.start();
                AddFromFile2();
                writeFile();
                finish();
            }
        });
    }
    public void AddFromFile1()
    {
        try {
            String splitBy="\n";
            FileInputStream in = this.openFileInput("file.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            while (br!=null)
            {
                String line=br.readLine();
                String[] value=line.split(splitBy);
                list1.add(value[0]);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
    public void AddFromFile2()
    {
        try {
            String splitBy="\n";
            FileInputStream in = this.openFileInput("fileHistory.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            while (br!=null)
            {
                String line=br.readLine();
                String[] value=line.split(splitBy);
                list1.add(value[0]);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
    private void writeFile()
    {
        try {
            FileOutputStream fileOutputStream=openFileOutput("fileHistory.txt",0);
            OutputStreamWriter writer= new OutputStreamWriter(fileOutputStream);
            for(String str : list1)
            {
                writer.write(str+"\n");
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
