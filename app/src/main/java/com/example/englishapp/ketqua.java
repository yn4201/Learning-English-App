package com.example.englishapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ketqua extends AppCompatActivity {
    Button BT,BT1;
    TextView KQ,Ten;
    private HashMap<String,write> hashMap=new HashMap<String,write>();
    private ArrayList<String> major= new ArrayList<String>();
    private ArrayList<String> list1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ketqua);
        KQ=(TextView)findViewById(R.id.InKQ);
        Ten=(TextView)findViewById(R.id.InTen);
        BT=(Button)findViewById(R.id.btKetThuc);
        BT1=(Button)findViewById(R.id.btBieuDoTron);
        AddFromFile();
        Print();
        Intent callerIntent=getIntent();
        Bundle packageFromCaller=callerIntent.getBundleExtra("MyPackage");
        KQ.setText(packageFromCaller.getInt("KQ")+"/"+packageFromCaller.getInt("Socau"));
        BT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(ketqua.this,R.raw.click );
                mediaPlayer.start();
                Intent intent = new Intent(ketqua.this,bieudo.class);
                Bundle bundle=new Bundle();
                bundle.putInt("Dung",packageFromCaller.getInt("KQ"));
                bundle.putInt("Sai",packageFromCaller.getInt("Socau")-packageFromCaller.getInt("KQ"));
                intent.putExtra("MyPackageBD",bundle);
                startActivity(intent);
                finish();
            }
        });
        AddFromFile1();
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(ketqua.this,R.raw.click );
                mediaPlayer.start();
                AddFromFile2();
                writeFile();
                finish();
            }
        });
    }
    public void AddFromFile()
    {
        try {
            String splitBy=",";
            FileInputStream in = this.openFileInput("file.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            major.add("ALL");
            while (br!=null)
            {
                String line=br.readLine();
                String[] value=line.split(splitBy);
                hashMap.put(value[0],new write(value[0],value[1],value[2],value[3]));
                if(!major.contains(value[5]))
                {
                    major.add(value[5]);
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
    public void Print()
    {
        for(write x:hashMap.values())
        {
            Ten.setText(x.getTen());
        }
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