package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class QuestionC1
{
    public String ID;
    public String Q;
    public String Answer;

}

class QuestionC2
{
    public String ID;
    public String Q;
    public String Answer;
}

class QuestionC3
{
    public String ID;
    public String Q;
    public String Answer;

}

class QuestionC4
{
    public String ID;
    public String Q;

}

class QuestionC5
{
    public String ID;
    public String Q;

}

class QuestionC6
{
    public String ID;
    public String Q;
}

class QuestionC7
{
    public String ID;
    public String Q;

}

class QuestionC8
{
    public String ID;
    public String Q;

}

class QuestionC9
{

    public String ID;
    public String Q;

}

class QuestionC10
{
    public String ID;
    public String Q;

}

public class correctword_test1 extends AppCompatActivity {
    TextView CauHoi, Diem, ThoiGian, Star, Tilte;
    Button TroVe, KiemTra, TiepTuc;
    EditText TraLoi;
    int star = 0;
    int count = 0;
    int pos = 0;
    int kq = 0;
    int scd = 10;
    int qc = 0, qc1 = 0, qc2 = 0, qc3 = 0, qc4 = 0, qc5 = 0, qc6 = 0, qc7 = 0, qc8 = 0, qc9 = 0, qc10 = 0;
    int gh = 3;
    int[] integers = new int[21];
    int[] integers1 = new int[20];
    CountDownTimer ThoiGianC;
    ArrayList<QuestionC1> L1 = new ArrayList();
    ArrayList<QuestionC2> L2 = new ArrayList();
    ArrayList<QuestionC3> L3 = new ArrayList();
    ArrayList<QuestionC4> L4 = new ArrayList();
    ArrayList<QuestionC5> L5 = new ArrayList();
    ArrayList<QuestionC6> L6 = new ArrayList();
    ArrayList<QuestionC7> L7 = new ArrayList();
    ArrayList<QuestionC8> L8 = new ArrayList();
    ArrayList<QuestionC9> L9 = new ArrayList();
    ArrayList<QuestionC10> L10 = new ArrayList();

    public void countdown() {
        ThoiGianC = new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                ThoiGian.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                scd--;
                gh--;
                if (scd < 1) {
                    scd = 1;
                }
                if (gh == 0 && star > 0) {
                    star--;
                    gh = 3;
                } else if (gh == 0 && star == 0) {
                    Intent intent = new Intent(correctword_test1.this, correctword_thongbao.class);
                    startActivity(intent);
                    finish();
                }
                if (qc >= 10) {
                    Intent callerIntent = getIntent();
                    Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                    String name = packageFromCaller.getString("name");
                    Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("num", qc);
                    bundle.putInt("star", star);
                    bundle.putString("name", name);
                    intent.putExtra("package", bundle);
                    startActivity(intent);
                    finish();
                } else if (scd <= 1) {
                    DisplayQ1(pos);
                } else if (scd == 2) {
                    DisplayQ2(pos);
                } else if (scd == 3) {
                    DisplayQ3(pos);
                } else if (scd == 4) {
                    DisplayQ4(pos);
                } else if (scd == 5) {
                    DisplayQ5(pos);
                } else if (scd == 6) {
                    DisplayQ6(pos);
                } else if (scd == 7) {
                    DisplayQ7(pos);
                } else if (scd == 8) {
                    DisplayQ8(pos);
                } else if (scd == 9) {
                    DisplayQ9(pos);
                } else if (scd == 10) {
                    DisplayQ10(pos);
                }
            }
        }.start();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correctword_test1);
        CauHoi = (TextView) findViewById(R.id.Question);
        Diem = (TextView) findViewById(R.id.CorrectAnswer);
        ThoiGian = (TextView) findViewById(R.id.Time);
        Tilte = (TextView) findViewById(R.id.Title);
        TroVe = (Button) findViewById(R.id.Back);
        TroVe.setOnClickListener(view -> {
            startActivity(new Intent(correctword_test1.this, MainActivity.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(correctword_test1.this, R.raw.click);
            mediaPlayer.start();
        });
        KiemTra = (Button) findViewById(R.id.Answer);
        TiepTuc = (Button) findViewById(R.id.BtnTiepTuc);
        TraLoi = (EditText) findViewById(R.id.YourAnswer);
        Star = (TextView) findViewById(R.id.Star);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.click);
        countdown();
        random();
        ReadDatac1();
        ReadDatac2();
        ReadDatac3();
        ReadDatac4();
        ReadDatac5();
        ReadDatac6();
        ReadDatac7();
        ReadDatac8();
        ReadDatac9();
        ReadDatac10();
        DisplayQ10(pos);

        TroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.start();
                Intent intent = new Intent(correctword_test1.this, correctword.class);
                startActivity(intent);
                finish();
            }
        });

        KiemTra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThoiGianC.cancel();
                if (scd <= 1) {
                    pos = integers[qc1];
                    if (L1.get(pos).Answer.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        scd--;
                        if (scd < 1) {
                            scd = 1;
                        }
                        count = 0;
                        gh--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc1++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd <= 1) {
                        DisplayQ1(pos);
                    } else if (scd == 2) {
                        DisplayQ2(pos);
                    }
                } else if (scd == 2) {
                    pos = integers[qc2];
                    if (L2.get(pos).Answer.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc2++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 1) {
                        DisplayQ1(pos);
                    } else if (scd == 2) {
                        DisplayQ2(pos);
                    } else if (scd == 3) {
                        DisplayQ3(pos);
                    }
                } else if (scd == 3) {
                    pos = integers[qc3];
                    if (L3.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc3++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 2) {
                        DisplayQ2(pos);
                    } else if (scd == 3) {
                        DisplayQ3(pos);
                    } else if (scd == 4) {
                        DisplayQ4(pos);
                    }
                } else if (scd == 4) {
                    pos = integers[qc4];
                    if (L4.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc4++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 3) {
                        DisplayQ3(pos);
                    } else if (scd == 4) {
                        DisplayQ4(pos);
                    } else if (scd == 5) {
                        DisplayQ5(pos);
                    }
                } else if (scd == 5) {
                    pos = integers[qc5];
                    if (L5.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_thongbao.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc5++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 4) {
                        DisplayQ4(pos);
                    } else if (scd == 5) {
                        DisplayQ5(pos);
                    } else if (scd == 6) {
                        DisplayQ6(pos);
                    }
                } else if (scd == 6) {
                    pos = integers[qc6];
                    if (L6.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc6++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 5) {
                        DisplayQ5(pos);
                    } else if (scd == 6) {
                        DisplayQ6(pos);
                    } else if (scd == 7) {
                        DisplayQ7(pos);
                    }
                } else if (scd == 7) {
                    pos = integers[qc7];
                    if (L7.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc7++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 6) {
                        DisplayQ6(pos);
                    } else if (scd == 7) {
                        DisplayQ7(pos);
                    } else if (scd == 8) {
                        DisplayQ8(pos);
                    }
                } else if (scd == 8) {
                    pos = integers[qc8];
                    if (L8.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc8++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 7) {
                        DisplayQ7(pos);
                    } else if (scd == 8) {
                        DisplayQ8(pos);
                    } else if (scd == 9) {
                        DisplayQ9(pos);
                    }
                } else if (scd == 9) {
                    pos = integers[qc9];
                    if (L9.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc9++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 8) {
                        DisplayQ8(pos);
                    } else if (scd == 9) {
                        DisplayQ9(pos);
                    } else if (scd == 10) {
                        DisplayQ10(pos);
                    }
                } else if (scd == 10) {
                    pos = integers[qc10];
                    if (L10.get(pos).Q.compareTo(TraLoi.getText().toString().trim()) == 0) {
                        kq = kq + 1;
                        count++;
                        gh = 3;
                        scd++;
                        if (count == 3) {
                            star++;
                            Star.setText("" + star);
                            count = 0;
                        }
                    } else {
                        count = 0;
                        gh--;
                        scd--;
                    }
                    if (gh < 1 && star > 0) {
                        star--;
                        gh = 3;
                    } else if (gh < 1 && star == 0) {
                        Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                        startActivity(intent);
                        finish();
                    }
                    qc++;
                    qc10++;
                    if (qc >= 10) {
                        Intent callerIntent = getIntent();
                        Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                        String name = packageFromCaller.getString("name");
                        Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("KQ", kq);
                        bundle.putInt("num", qc);
                        bundle.putInt("star", star);
                        bundle.putString("name", name);
                        intent.putExtra("package", bundle);
                        startActivity(intent);
                        finish();
                    } else if (scd == 9) {
                        DisplayQ9(pos);
                    } else if (scd == 10) {
                        DisplayQ10(pos);
                    }
                }
            }
        });

        TiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer =
                        MediaPlayer.create(correctword_test1.this, R.raw.click);
                mediaPlayer.start();
                ThoiGianC.cancel();
                gh--;
                scd--;
                if (scd < 1) {
                    scd = 1;
                }
                if (gh < 1 && star > 0) {
                    star--;
                    gh = 3;
                } else if (gh < 1 && star < 1) {
                    Intent intent = new Intent(correctword_test1.this, correctword_notification.class);
                    startActivity(intent);
                    finish();
                }
                if (qc >= 10) {
                    Intent callerIntent = getIntent();
                    Bundle packageFromCaller = callerIntent.getBundleExtra("bundle");
                    String name = packageFromCaller.getString("name");
                    Intent intent = new Intent(correctword_test1.this, correctword_result.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt("KQ", kq);
                    bundle.putInt("num", qc);
                    bundle.putInt("star", star);
                    bundle.putString("name", name);
                    intent.putExtra("package", bundle);
                    startActivity(intent);
                    finish();
                } else if (scd <= 1) {
                    DisplayQ1(pos);
                } else if (scd == 2) {
                    DisplayQ2(pos);
                } else if (scd == 3) {
                    DisplayQ3(pos);
                } else if (scd == 4) {
                    DisplayQ4(pos);
                } else if (scd == 5) {
                    DisplayQ5(pos);
                } else if (scd == 6) {
                    DisplayQ6(pos);
                } else if (scd == 7) {
                    DisplayQ7(pos);
                } else if (scd == 8) {
                    DisplayQ8(pos);
                } else if (scd == 9) {
                    DisplayQ9(pos);
                } else if (scd == 10) {
                    DisplayQ10(pos);
                }
            }
        });
    }


    void DisplayQ1(int pos) {
        ThoiGianC.cancel();
        pos = integers[qc1];
        countdown();
        CauHoi.setText(L1.get(pos).Q);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();
        Star.setText("" + star);
    }


    void DisplayQ2(int pos) {
        ThoiGianC.cancel();
        pos = integers[qc2];
        countdown();
        CauHoi.setText(L2.get(pos).Q);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();
        Star.setText("" + star);
    }

    void DisplayQ3(int pos) {
        ThoiGianC.cancel();
        pos = integers[qc3];
        countdown();
        String chuoitam = "";
        chuoitam = L3.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();
    }

    void DisplayQ4(int pos) {

        ThoiGianC.cancel();
        pos = integers[qc4];
        countdown();
        String chuoitam = "";
        chuoitam = L4.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }

    void DisplayQ5(int pos) {

        ThoiGianC.cancel();
        pos = integers[qc5];
        countdown();
        String chuoitam = "";
        chuoitam = L5.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }

    void DisplayQ6(int pos) {

        ThoiGianC.cancel();
        pos = integers[qc6];
        countdown();
        String chuoitam = "";
        chuoitam = L6.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }

    void DisplayQ7(int pos) {

        ThoiGianC.cancel();
        pos = integers[qc7];
        countdown();
        String chuoitam = "";
        chuoitam = L7.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }

    void DisplayQ8(int pos) {

        ThoiGianC.cancel();
        pos = integers[qc8];
        countdown();
        String chuoitam = "";
        chuoitam = L8.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }

    void DisplayQ9(int pos) {
        ThoiGianC.cancel();
        pos = integers[qc9];
        countdown();
        String chuoitam = "";
        chuoitam = L9.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();
    }

    void DisplayQ10(int pos) {
        ThoiGianC.cancel();
        pos = integers[qc10];
        countdown();
        String chuoitam = "";
        chuoitam = L10.get(pos).Q;
        String chuoi = "";
        char[] chuoich = chuoitam.toCharArray();
        random1(chuoich.length);
        for (int i = 0; i <= chuoich.length - 1; i++) {
            chuoi += chuoich[integers1[i]] + "/";
        }
        Star.setText("" + star);
        CauHoi.setText(chuoi);
        Diem.setText("Câu đúng: " + kq);
        TraLoi.getText().clear();

    }


    public void random()
    {
        Random r = new Random();
        for (int i = 0; i < 5; i++)
        {
            integers[i] = r.nextInt(5);
            for (int j = 0; j < i; j++) {
                if (integers[i] == integers[j])
                {
                    i--;
                }
            }
        }
    }

    public void random1(int N)
    {
        Random r = new Random();
        for (int i = 0; i < N; i++)
        {
            integers1[i] = r.nextInt(N);
            for (int j = 0; j < i; j++)
            {
                if (integers1[i] == integers1[j])
                {
                    i--;
                }
            }
        }
    }

    void ReadDatac1()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac1.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    QuestionC1 Q1 = new QuestionC1();
                    Q1.ID = ID;
                    Q1.Q = Question;
                    Q1.Answer = Answer;
                    L1.add(Q1);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac2()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac2.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node instanceof Element) {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    QuestionC2 Q2 = new QuestionC2();
                    Q2.ID = ID;
                    Q2.Q = Question;
                    Q2.Answer = Answer;
                    L2.add(Q2);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac3()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac3.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    QuestionC3 Q3 = new QuestionC3();
                    Q3.ID = ID;
                    Q3.Q = Question;
                    Q3.Answer = Answer;
                    L3.add(Q3);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac4()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac4.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element) {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC4 Q4 = new QuestionC4();
                    Q4.ID = ID;
                    Q4.Q = Question;
                    L4.add(Q4);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac5()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac5.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node instanceof Element) {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC5 Q5 = new QuestionC5();
                    Q5.ID = ID;
                    Q5.Q = Question;
                    L5.add(Q5);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac6()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac6.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC6 Q6 = new QuestionC6();
                    Q6.ID = ID;
                    Q6.Q = Question;
                    L6.add(Q6);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac7() {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac7.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC7 Q7 = new QuestionC7();
                    Q7.ID = ID;
                    Q7.Q = Question;
                    L7.add(Q7);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac8()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac8.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC8 Q8 = new QuestionC8();
                    Q8.ID = ID;
                    Q8.Q = Question;
                    L8.add(Q8);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac9()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac9.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC9 Q9 = new QuestionC9();
                    Q9.ID = ID;
                    Q9.Q = Question;
                    L9.add(Q9);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    void ReadDatac10()
    {
        try {
            DocumentBuilderFactory DBF = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = DBF.newDocumentBuilder();
            InputStream in = getAssets().open("datac10.xml");
            Document doc = builder.parse(in);
            Element root = doc.getDocumentElement();
            NodeList list = root.getChildNodes();
            for (int i = 0; i < list.getLength(); i++)
            {
                Node node = list.item(i);
                if (node instanceof Element)
                {
                    Element Item = (Element) node;
                    NodeList listChild = Item.getElementsByTagName("ID");
                    String ID = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();
                    QuestionC10 Q10 = new QuestionC10();
                    Q10.ID = ID;
                    Q10.Q = Question;
                    L10.add(Q10);
                }
            }
        } catch (ParserConfigurationException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (SAXException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}