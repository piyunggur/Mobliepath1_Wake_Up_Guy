package com.work.wakeupguyv5;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText user,pass;
    private Button create,login,admin;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.pass);
        create = (Button)findViewById(R.id.create);
        login = (Button)findViewById(R.id.login);
        admin = (Button)findViewById(R.id.admin);
        mAuth = FirebaseAuth.getInstance();


        final MediaPlayer songbutton = MediaPlayer.create(this, R.raw.songbutton2);

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songbutton.start();songbutton.start();songbutton.start();songbutton.start();songbutton.start();songbutton.start();
                startActivity(new Intent(LoginActivity.this,PushGameActivity.class));
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songbutton.start();
                if(user.getText().toString().length() != 0 && pass.getText().toString().length() != 0) {
                    String email = user.getText().toString();
                    String password = pass.getText().toString();
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "คุณไม่สามารถสร้างผู้ใช้ใหม่ได้",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "สร้างผู้ใช้ใหม่สำเร็จ",
                                                Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "คุณสามารถสร้างผู้ใช้ใหม่ได้ในหน้านี้ กรุณากรอกข้อมูลให้ครบ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                songbutton.start();
                if(user.getText().toString().length() != 0 && pass.getText().toString().length() != 0) {
                    String email = user.getText().toString();
                    String password = pass.getText().toString();
                    mAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (!task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "อีเมลหรือรหัสไม่ถูกต้อง",
                                                Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "ยินดีต้อนรับ",
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,Choose4Activity.class));
                                    }

                                    // ...
                                }
                            });
                }else{
                    Toast.makeText(LoginActivity.this, "กรุณากรอกข้อมูลให้ครบ",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                } else {
                    // User is signed out
                }
                // ...
            }
        };


    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
