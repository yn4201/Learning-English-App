package com.example.englishapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.example.englishapp.R;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class correctword_highscore extends Activity {

    ImageButton Back;
    ListView ListPlayer;

    ArrayList<nguoichoi2> list = new ArrayList<nguoichoi2>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correctword_highscore);

        Back = (ImageButton) findViewById(R.id.Back);
        ListPlayer = (ListView) findViewById(R.id.ListView);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.click);


        Print();
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(correctword_highscore.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void Print()
    {
        list.clear();
        readFromFile();
        sortDiem();
        ArrayList<String> temp = new ArrayList<String>();
        for (nguoichoi2 in:list) {
            temp.add(in.toString1());
        }
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,temp);
        ListPlayer.setAdapter(adapter2);
    }

    public void readFromFile() {
        try{
            String splitBy = ",";
            FileInputStream in = this.openFileInput("nguoichoi2.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br != null) {
                String line = br.readLine();
                String[] value = line.split(splitBy);
                list.add(new nguoichoi2(value[0], Integer.parseInt(value[1])));
            }
            br.close();
        }
        catch (Exception e){
            System.out.println(""+e.getMessage());
        }
    }

    public void sortDiem()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            list.sort(new Comparator<nguoichoi2>()
            {
                @Override
                public int compare(nguoichoi2 s, nguoichoi2 s1)
                {
                    if (s.getDiem()<s1.getDiem()) return 1;
                    if (s.getDiem() == s1.getDiem()) return 0;
                    return -1;
                }
            });
        }
    }
}

