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

public class login extends AppCompatActivity {
    EditText mEmail, mPassword;
    Button mLoginBtn;
    TextView mCreateBtn;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.password);
        mLoginBtn = findViewById(R.id.loginBtn);
        mCreateBtn = findViewById(R.id.createText);
        mAuth = FirebaseAuth.getInstance();

        mLoginBtn.setOnClickListener(view ->{loginUser();
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(login.this,R.raw.click );
            mediaPlayer.start();} );
        mCreateBtn.setOnClickListener(view ->{
            startActivity(new Intent(login.this, signup.class));
            MediaPlayer mediaPlayer =
                    MediaPlayer.create(login.this,R.raw.click );
            mediaPlayer.start();
        });
    }

    private void loginUser() {
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            mEmail.setError("Email cannot be empty");
            mEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            mPassword.setError("Password cannot be empty");
            mPassword.requestFocus();
        } else {
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(login.this, "User logged in successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(login.this, MainActivity.class));
                    } else {
                        Toast.makeText(login.this, "Error !" + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        mCreateBtn.setOnClickListener(view -> startActivity(new Intent(getApplicationContext(), signup.class)));
    }
}
