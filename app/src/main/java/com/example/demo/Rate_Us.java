package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
public class Rate_Us extends AppCompatActivity {

    CardView cv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        getSupportActionBar().hide();
        setContentView(R.layout.activity_rate_us);
        cv=findViewById(R.id.rates);

        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent rate=new Intent(Intent.ACTION_VIEW);
                rate.setData(Uri.parse("https://support.google.com/business/answer/7035772?hl=en"));
                startActivity(rate);
            }
        });
    }

}