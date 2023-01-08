package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
//    Button playBtn,highScore,guideBtn;
    CardView Cd2, Cd3, Cd4, Cd5;
    ImageButton quitBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Cd2 = findViewById(R.id.card2);
        Cd3 = findViewById(R.id.card3);
        Cd4 = findViewById(R.id.card4);
        Cd5 = findViewById(R.id.card5);

//        highScore = findViewById(R.id.highScoreBtn);
        quitBtn =findViewById(R.id.logout);
        mAuth= FirebaseAuth.getInstance();
//        playBtn=findViewById(R.id.playBtn);
//
//        guideBtn=findViewById(R.id.guideBtn);
//        guideBtn.setOnClickListener(view ->{
//            startActivity(new Intent(MainActivity.this, guidegame.class));
//            MediaPlayer mediaPlayer =
//                    MediaPlayer.create(MainActivity.this,R.raw.click );
//            mediaPlayer.start();
//        });

        quitBtn.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(MainActivity.this, login.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(MainActivity.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd2.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, choose.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(MainActivity.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd3.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, entername.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(MainActivity.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd4.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, correctword_highscore.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(MainActivity.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd5.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, guidegame.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(MainActivity.this,R.raw.click );
            mediaPlayer.start();
        });

//        playBtn.setOnClickListener(view ->{
//            startActivity(new Intent(MainActivity.this, mode.class));
//            MediaPlayer mediaPlayer =
//                    MediaPlayer.create(MainActivity.this,R.raw.click );
//            mediaPlayer.start();
//        });
//        highScore.setOnClickListener(view ->{
//            startActivity(new Intent(MainActivity.this, history.class));
//            MediaPlayer mediaPlayer =
//                    MediaPlayer.create(MainActivity.this,R.raw.click );
//            mediaPlayer.start();
//        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, login.class));
        }
    }
}