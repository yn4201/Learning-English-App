package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class guidemultiple2 extends AppCompatActivity {
    Button home;
    ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidemultiple2);
        back=findViewById(R.id.Back);
        back.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidemultiple1.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidemultiple2.this, R.raw.click);
            mediaPlayer.start();
        });

        home=findViewById(R.id.homeBtn);
        home.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidegame.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidemultiple2.this, R.raw.click);
            mediaPlayer.start();
        });

    }
}