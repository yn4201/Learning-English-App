package com.example.englishapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

class Questionare {
    public String ID;
    public String Q;
    public String asA, asB, asC, asD, as;
}
public class multiple extends AppCompatActivity {

    TextView CauHoi,SoCau,ThoiGian, Diem;
//    RadioGroup RG1,RG2;
    RadioGroup RG;
//    Button Sau,Thoat;
    Button Sau;
    ImageButton Thoat;
    ImageView imageView;
    RadioButton A,B,C,D;
    CountDownTimer countDownTimer;
    int pos=0;
    int kq=0;
    int diemcao=0;
    String data;
    ArrayList<Questionare> L=new ArrayList();
    private ArrayList<String> list1=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.click);
        giatri();
        setContentView(R.layout.activity_multiple);
        CauHoi=(TextView)findViewById(R.id.CauHoi);
        SoCau=(TextView)findViewById(R.id.textSoCau);
        Thoat=(ImageButton) findViewById(R.id.BtnQLCuaCHD);
        Sau=(Button)findViewById(R.id.BtnTiep);
        RG=(RadioGroup)findViewById(R.id.GroupAnswer);
        Diem = (TextView) findViewById(R.id.TxtDiem);
//        RG2=(RadioGroup)findViewById(R.id.llout2);
        A=(RadioButton)findViewById(R.id.BtnDapAnA);
        B=(RadioButton)findViewById(R.id.BtnDapAnB);
        C=(RadioButton)findViewById(R.id.BtnDapAnC);
        D=(RadioButton)findViewById(R.id.BtnDapAnD);
        ThoiGian=(TextView)findViewById(R.id.TG);
        imageView=(ImageView)findViewById(R.id.img_HinhCauHoi);
        AddFromFile1();
        LoadDiemCao();
        ReadData();
        Integer[] arr = new Integer[49];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Collections.shuffle(Arrays.asList(arr));
        img(arr[pos]);
        Display(arr[pos]);
        Intent callerIntent=getIntent();
        Bundle packageFromCaller=callerIntent.getBundleExtra("MyPackage_1");
        int x=packageFromCaller.getInt("SoCau");
        list1.add(","+x+",");
        writeFile();
        showNextQuestion();
        Sau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int as=0;
                int idCheck1 = RG.getCheckedRadioButtonId();
                switch(idCheck1) {
                    case R.id.BtnDapAnA: {
                        if (L.get(arr[pos]).as.compareTo("A") == 0) {
                            kq = kq + 1;
                            as = 1;
                        }
                        break;
                    }
                    case R.id.BtnDapAnB: {
                        if (L.get(arr[pos]).as.compareTo("B") == 0) {
                            kq = kq + 1;
                            as = 1;
                        }
                        break;
                    }
                    case R.id.BtnDapAnC: {
                        if (L.get(arr[pos]).as.compareTo("C") == 0) {
                            kq = kq + 1;
                            as = 1;
                        }
                        break;
                    }
                    case R.id.BtnDapAnD: {
                        if (L.get(arr[pos]).as.compareTo("D") == 0) {
                            kq = kq + 1;
                            as = 1;
                        }
                        break;
                    }
                }
                if(as==1) {
                    data="1";
                }
                else {
                    data="0";
                    showNextQuestion();
                }
                showNextQuestion();
                list1.add(data);
                writeFile();
                pos++;
                if(pos >= x)
                {
                    list1.add(","+kq);
                    writeFile();
                    //pauseTime();
                    Intent intent = new Intent(multiple.this,ketqua.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("KQ",kq);
                    bundle.putInt("Socau",x);
                    intent.putExtra("MyPackage",bundle);
                    startActivity(intent);
                    if(kq>diemcao)
                    {
                        diemcao=kq;
                        SaveDiemCao();
                    }
                    MediaPlayer mediaPlayer =
                            MediaPlayer.create(multiple.this,R.raw.intro2 );
                    mediaPlayer.start();
                    finish();
                }
                else
                {
                    img(arr[pos]);
                    Display(arr[pos]);
                }
            }
        });

        Thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(multiple.this,ketqua.class);
                Bundle bundle=new Bundle();
                bundle.putInt("KQ",kq);
                bundle.putInt("Socau",x);
                intent.putExtra("MyPackage",bundle);
                startActivity(intent);
                if(kq>diemcao)
                {
                    diemcao=kq;
                    SaveDiemCao();
                }
                list1.add(","+kq);
                writeFile();
                finish();
            }
        });
    }

    private void showNextQuestion() {

        timer();
    }

    void giatri()
    {
        Sau= (Button) findViewById(R.id.BtnTiep);
    }

    private void timer(){
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                ThoiGian.setText(String.valueOf(millisUntilFinished/1000));
            }
            //Dem het thoi gian thi chuyen sang cau hoi tiep theo
            @Override
            public void onFinish() {
                showNextQuestion();
            }
        }.start();
    }

    void Display(int i)
    {
        Intent callerIntent=getIntent();
        Bundle packageFromCaller=callerIntent.getBundleExtra("MyPackage_1");
        CauHoi.setText(L.get(i).Q);
        Diem.setText("Câu đúng: " + kq);
        A.setText(L.get(i).asA);
        B.setText(L.get(i).asB);
        C.setText(L.get(i).asC);
        D.setText(L.get(i).asD);
        SoCau.setText((pos+1)+"/"+packageFromCaller.getInt("SoCau"));
        RG.clearCheck();
//        RG2.clearCheck();
    }
    void ReadData()
    {
        try {
            DocumentBuilderFactory DBF= DocumentBuilderFactory.newInstance();
            DocumentBuilder builder=DBF.newDocumentBuilder();
            InputStream in=getAssets().open("data.xml");
            Document doc=builder.parse(in);
            Element root=doc.getDocumentElement();
            NodeList list=root.getChildNodes();
            for(int i=0;i<list.getLength();i++)
            {
                Node node=list.item(i);
                if(node instanceof Element)
                {
                    Element Item=(Element) node;
                    NodeList listChild =Item.getElementsByTagName("ID");
                    String ID =listChild.item(0).getTextContent();
                    listChild=Item.getElementsByTagName("Question");
                    String Question = listChild.item(0).getTextContent();//lấy nội dung Question
                    listChild = Item.getElementsByTagName("AnswerA");
                    String AnswerA = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerB");
                    String AnswerB = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerC");
                    String AnswerC = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("AnswerD");
                    String AnswerD = listChild.item(0).getTextContent();
                    listChild = Item.getElementsByTagName("Answer");
                    String Answer = listChild.item(0).getTextContent();
                    Questionare Q1=new Questionare();
                    Q1.ID=ID;
                    Q1.Q=Question;
                    Q1.asA=AnswerA;
                    Q1.asB=AnswerB;
                    Q1.asC=AnswerC;
                    Q1.asD=AnswerD;
                    Q1.as=Answer;
                    L.add(Q1);
                };
            }
        }catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void LoadDiemCao()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData", Context.MODE_PRIVATE);
        if(sharedPreferences != null)
        {
            diemcao=sharedPreferences.getInt("H",0);
        }
    }
    void SaveDiemCao()
    {
        SharedPreferences sharedPreferences=getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("H",diemcao);
        editor.apply();
    }
    public void AddFromFile1()
    {
        try {
            String splitBy="\n";
            FileInputStream in = this.openFileInput("file1.txt");
            BufferedReader br=new BufferedReader(new InputStreamReader(in));
            while (br!=null)
            {
                String line=br.readLine();
                String[] value=line.split(splitBy);
                list1.add(value[0]);
            }
            br.close();
        }
        catch (Exception e)
        {
            System.out.println(""+e.getMessage());
        }
    }
    private void writeFile()
    {
        try {
            FileOutputStream fileOutputStream=openFileOutput("file1.txt",0);
            OutputStreamWriter writer= new OutputStreamWriter(fileOutputStream);
            for(String str : list1)
            {
                writer.write(str);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    void img(int a)
    {
        switch(a)
        {
            case 0:
            {
                imageView.setImageResource(R.drawable.c1);
                break;
            }
            case 1:
            {
                imageView.setImageResource(R.drawable.c2);
                break;
            }
            case 2:
            {
                imageView.setImageResource(R.drawable.c3);
                break;
            }
            case 3:
            {
                imageView.setImageResource(R.drawable.c4);
                break;
            }
            case 4:
            {
                imageView.setImageResource(R.drawable.c5);
                break;
            }
            case 5:
            {
                imageView.setImageResource(R.drawable.c6);
                break;
            }
            case 6:
            {
                imageView.setImageResource(R.drawable.c7);
                break;
            }
            case 7:
            {
                imageView.setImageResource(R.drawable.c8);
                break;
            }
            case 8:
            {
                imageView.setImageResource(R.drawable.c9);
                break;
            }
            case 9:
            {
                imageView.setImageResource(R.drawable.c10);
                break;
            }
            case 10:
            {
                imageView.setImageResource(R.drawable.c11);
                break;
            }
            case 11:
            {
                imageView.setImageResource(R.drawable.c12);
                break;
            }
            case 12:
            {
                imageView.setImageResource(R.drawable.c13);
                break;
            }
            case 13:
            {
                imageView.setImageResource(R.drawable.c14);
                break;
            }
            case 14:
            {
                imageView.setImageResource(R.drawable.c15);
                break;
            }
            case 15:
            {
                imageView.setImageResource(R.drawable.c16);
                break;
            }
            case 16:
            {
                imageView.setImageResource(R.drawable.c17);
                break;
            }
            case 17:
            {
                imageView.setImageResource(R.drawable.c18);
                break;
            }
            case 18:
            {
                imageView.setImageResource(R.drawable.c19);
                break;
            }
            case 19:
            {
                imageView.setImageResource(R.drawable.c20);
                break;
            }
            case 20:
            {
                imageView.setImageResource(R.drawable.c21);
                break;
            }
            case 21:
            {
                imageView.setImageResource(R.drawable.c22);
                break;
            }
            case 22:
            {
                imageView.setImageResource(R.drawable.c23);
                break;
            }
            case 23:
            {
                imageView.setImageResource(R.drawable.c24);
                break;
            }
            case 24:
            {
                imageView.setImageResource(R.drawable.c25);
                break;
            }
            case 25:
            {
                imageView.setImageResource(R.drawable.c26);
                break;
            }
            case 26:
            {
                imageView.setImageResource(R.drawable.c27);
                break;
            }
            case 27:
            {
                imageView.setImageResource(R.drawable.c28);
                break;
            }
            case 28:
            {
                imageView.setImageResource(R.drawable.c29);
                break;
            }
            case 29:
            {
                imageView.setImageResource(R.drawable.c30);
                break;
            }
            case 30:
            {
                imageView.setImageResource(R.drawable.c31);
                break;
            }
            case 31:
            {
                imageView.setImageResource(R.drawable.c32);
                break;
            }
            case 32:
            {
                imageView.setImageResource(R.drawable.c33);
                break;
            }
            case 33:
            {
                imageView.setImageResource(R.drawable.c34);
                break;
            }
            case 34:
            {
                imageView.setImageResource(R.drawable.c35);
                break;
            }
            case 35:
            {
                imageView.setImageResource(R.drawable.c36);
                break;
            }
            case 36:
            {
                imageView.setImageResource(R.drawable.c37);
                break;
            }
            case 37:
            {
                imageView.setImageResource(R.drawable.c38);
                break;
            }
            case 38:
            {
                imageView.setImageResource(R.drawable.c39);
                break;
            }
            case 39:
            {
                imageView.setImageResource(R.drawable.c40);
                break;
            }
            case 40:
            {
                imageView.setImageResource(R.drawable.c41);
                break;
            }
            case 41:
            {
                imageView.setImageResource(R.drawable.c42);
                break;
            }
            case 42:
            {
                imageView.setImageResource(R.drawable.c43);
                break;
            }
            case 43:
            {
                imageView.setImageResource(R.drawable.c51);
                break;
            }
            case 44:
            {
                imageView.setImageResource(R.drawable.c45);
                break;
            }
            case 45:
            {
                imageView.setImageResource(R.drawable.c46);
                break;
            }
            case 46:
            {
                imageView.setImageResource(R.drawable.c47);
                break;
            }
            case 47:
            {
                imageView.setImageResource(R.drawable.c48);
                break;
            }
            case 48:
            {
                imageView.setImageResource(R.drawable.c49);
                break;
            }
            case 49:
            {
                imageView.setImageResource(R.drawable.c50);
                break;
            }
        }
    }
}
