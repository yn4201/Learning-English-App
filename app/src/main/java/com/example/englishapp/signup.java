package com.example.englishapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class signup extends AppCompatActivity {
    EditText mFullName, mEmail, mPassword, mPhone;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword= findViewById(R.id.password);
//        mPhone = findViewById(R.id.phone);
        mRegisterBtn= findViewById(R.id.registerBtn);
        mLoginBtn= findViewById(R.id.createText);
        mAuth = FirebaseAuth.getInstance();

        // tạo lắng nhe
        mRegisterBtn.setOnClickListener(view -> {
            createUser();
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(signup.this,R.raw.click );
            mediaPlayer.start();
        });
        mLoginBtn.setOnClickListener(view ->{
            startActivity(new Intent(signup.this, login.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(signup.this,R.raw.click );
            mediaPlayer.start();
        });
    }

    private void createUser (){
        String email =mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            mEmail.setError("Email cannot be empty");
            mEmail.requestFocus();}
        else if (TextUtils.isEmpty(password)){
            mPassword.setError("Password cannot be empty");
            mPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(signup.this, "User Created", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(signup.this,login.class));
                    }else {
                        Toast.makeText(signup.this, "Error !"  + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

}