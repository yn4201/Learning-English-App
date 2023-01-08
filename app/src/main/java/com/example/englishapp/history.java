package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class history extends AppCompatActivity {
    private HashMap<String,write> hashMap=new HashMap<String,write>();
    private ArrayList<String> list=new ArrayList<String>();
    private ArrayList<String> major= new ArrayList<String>();
    Spinner spin;
    ListView listView;
    Button button;
    ImageButton btOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        spin = (Spinner) findViewById(R.id.DsChedo);
        button = (Button) findViewById(R.id.btXem);
        listView = (ListView) findViewById(R.id.ListView);
        AddFromFile();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, major);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spin.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(history.this,R.raw.click );
                mediaPlayer.start();
                Print(spin.getSelectedItem().toString());
            }
        });
        btOut=(ImageButton)findViewById(R.id.BtnQLCuaLS);
        btOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(history.this,R.raw.click );
                mediaPlayer.start();
                Intent intent=new Intent(history.this,MainActivity.class);
                finish();
            }
        });
    }
    public void AddFromFile()
    {
        try {
            String splitBy=",";
            FileInputStream in = this.openFileInput("fileHistory.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            major.add("ALL");
            while (br!=null)
            {
                String line=br.readLine();
                String[] value=line.split(splitBy);
                hashMap.put(value[0],new write(value[0],value[1],value[2],value[3]));
                if(!major.contains(value[1]))
                {
                    major.add(value[1]);
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
    public void Print(String s)
    {
        list.clear();
        if(s.equals("ALL"))
        {
            for(write x:hashMap.values())
            {
                list.add(x.toString());
            }
        }
        else{
            for(write x:hashMap.values())
            {
                if(s.equals(x.getChedo()))
                {
                    list.add(x.toString());
                }
            }
        }
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter1);
    }
}