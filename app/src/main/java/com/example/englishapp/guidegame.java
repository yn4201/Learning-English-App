package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;

public class guidegame extends AppCompatActivity {
    Button multipleChoice, back, correctw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guidegame);
        multipleChoice=findViewById(R.id.multipleBtn);
        multipleChoice.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidemultiple1.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidegame.this, R.raw.click);
            mediaPlayer.start();
        });
        correctw = findViewById(R.id.CorrectBtn);
        correctw.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), guidecw.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidegame.this, R.raw.click);
        });
        back = findViewById(R.id.backBtn);
        back.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(guidegame.this, R.raw.click);
            mediaPlayer.start();
            finish();
        });
    }
}
