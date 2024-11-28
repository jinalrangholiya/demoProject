package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.Ads.MyAds;
import com.google.android.ads.nativetemplates.TemplateView;

public class Android extends AppCompatActivity {


    TextView t1,d1;
    ImageView img;
    TemplateView templateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_android);
        t1=findViewById(R.id.name);
        d1=findViewById(R.id.desc1);
        img=findViewById(R.id.image);
        MyAds myAds=new MyAds(this);
        myAds.NativeAds(Android.this,findViewById(R.id.my_template));
        myAds.NativeAds(Android.this,findViewById(R.id.my_template1));

        Intent intent=getIntent();
        t1.setText(getIntent().getStringExtra("name"));
        d1.setText(getIntent().getStringExtra("details"));
        img.setImageResource(intent.getIntExtra("Image",0));
    }
}



