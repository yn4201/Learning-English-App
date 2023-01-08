package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class guidemultiple1 extends AppCompatActivity {
//    Button next;
    ImageButton next, home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidemultiple1);
        home=findViewById(R.id.homeBtn);
        home.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidegame.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidemultiple1.this, R.raw.click);
            mediaPlayer.start();
        });
        next=findViewById(R.id.nextBtn);
        next.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidemultiple2.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidemultiple1.this, R.raw.click);
            mediaPlayer.start();
        });

    }
}