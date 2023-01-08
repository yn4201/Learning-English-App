package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class mode extends AppCompatActivity {
    Button multipleChoice, back, correctw;
    private ArrayList<String> list1 = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
        multipleChoice = findViewById(R.id.multipleBtn);
        multipleChoice.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), choose.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(mode.this, R.raw.click);
            mediaPlayer.start();
            AddFromFile1();
            list1.add(",Multiple");
            writeFile();
            finish();
        });
        correctw = findViewById(R.id.CorrectBtn);
        correctw.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), correctword_test.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(mode.this, R.raw.click);
            mediaPlayer.start();
            AddFromFile1();
            list1.add(",Correctword");
            writeFile();
            finish();
        });
        back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(mode.this, R.raw.click);
            mediaPlayer.start();
            finish();
        });
    }

    private void writeFile() {
        try {
            String splitBy = "\n";
            FileInputStream in = this.openFileInput("file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while (br != null) {
                String line = br.readLine();
                String[] value = line.split(splitBy);
                list1.add(value[0]);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("" + e.getMessage());
        }
    }

    private void AddFromFile1() {
        try {
            FileOutputStream fileOutputStream = openFileOutput("file.txt", 0);
            OutputStreamWriter writer = new OutputStreamWriter(fileOutputStream);
            for (String str : list1) {
                writer.write(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}