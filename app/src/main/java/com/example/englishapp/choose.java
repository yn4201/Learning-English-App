package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

public class choose extends AppCompatActivity {
    Button BT;
    ImageButton back;
    Spinner Spin;
    ListView ListPlayer;
    String Arr[]={"5","6","7","8","9","10"};
    int vitri;
    int z=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        back = findViewById(R.id.Back);

        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.click);
        giatri();
        TaoSpin();
        BT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(choose.this,R.raw.click );
                mediaPlayer.start();
                Intent intent=new Intent(choose.this,multiple.class);
                Bundle bundle=new Bundle();
                bundle.putInt("SoCau",SoCau());
                intent.putExtra("MyPackage_1",bundle);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(choose.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void giatri()
    {
        BT= (Button) findViewById(R.id.Vao);
        Spin= (Spinner) findViewById(R.id.spinner);
    }
    void TaoSpin()
    {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_item,Arr);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        Spin.setAdapter(adapter);
        Spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                vitri=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                vitri=-1;
            }
        });
    }
    int SoCau()
    {
        switch (vitri)
        {
            case 0: z=5; break;
            case 1: z=6; break;
            case 2: z=7; break;
            case 3: z=8; break;
            case 4: z=9; break;
            case 5: z=10; break;
        }
        return z;
    }
}