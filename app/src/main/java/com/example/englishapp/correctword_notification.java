package com.example.englishapp;

import android.app.Activity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class correctword_notification extends Activity {
//    TextView Txt1;
//    Button BT;
    ImageButton BT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correctword_notification);
//        Txt1 = (TextView)findViewById(R.id.TxtThongbao);
        BT = (ImageButton) findViewById(R.id.BtnBacktb);
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(correctword_notification.this,R.raw.click );
                mediaPlayer.start();
                finish();
            }
        });
    }
}