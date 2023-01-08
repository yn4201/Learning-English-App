package com.example.englishapp;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import androidx.annotation.Nullable;

public class entername extends Activity {
    EditText NhapTen;
    Button BatDau;
    ImageButton TroVe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entername1);

        NhapTen = (EditText)findViewById(R.id.UserName);
        BatDau= (Button)findViewById(R.id.Start);
        TroVe = (ImageButton)findViewById(R.id.Back);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.click);

        BatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                String name;
                name = NhapTen.getText().toString().trim();
                Intent intent = new Intent(entername.this, correctword_test.class);
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                intent.putExtra("bundle",bundle);
                startActivity(intent);
            }
        });

        TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent1 = new Intent(entername.this, MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
    }
}
