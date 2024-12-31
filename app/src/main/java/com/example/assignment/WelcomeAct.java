package com.example.assignment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class WelcomeAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        new CountDownTimer(1000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                //trong cong bao nhieu gi thuc hien cong viec nao do
            }

            @Override
            public void onFinish() {
                //sau khi dem het thoi gian
                Intent intent = new Intent(WelcomeAct.this,LoginActivity.class);
                startActivity(intent);
            }
        }.start();

    }
}