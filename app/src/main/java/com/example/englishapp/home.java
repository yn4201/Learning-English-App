package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.cardview.widget.CardView;

public class home extends AppCompatActivity {
    CardView Cd1, Cd2, Cd3, Cd4, Cd5;
    ImageButton quitBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        quitBtn =findViewById(R.id.logout);
        Cd1 = findViewById(R.id.card1);
        Cd2 = findViewById(R.id.card2);
        Cd3 = findViewById(R.id.card3);
        Cd4 = findViewById(R.id.card4);
        Cd5 = findViewById(R.id.card5);

        quitBtn.setOnClickListener(view ->{
            mAuth.signOut();
            startActivity(new Intent(home.this, login.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd1.setOnClickListener(v -> {
            startActivity(new Intent(home.this, guidegame.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd2.setOnClickListener(v -> {
            startActivity(new Intent(home.this, multiple.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd3.setOnClickListener(v -> {
            startActivity(new Intent(home.this, correctword_test.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd4.setOnClickListener(v -> {
            startActivity(new Intent(home.this, history.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });

        Cd5.setOnClickListener(v -> {
            startActivity(new Intent(home.this, guidegame.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(home.this,R.raw.click );
            mediaPlayer.start();
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(home.this, login.class));
        }
    }
}





