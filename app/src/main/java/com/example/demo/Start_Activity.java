package com.example.demo;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.demo.Ads.MyAds;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Start_Activity extends AppCompatActivity {


    ImageButton share, rate, privacy;
    Button start;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_start);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        start=findViewById(R.id.start);
        share=findViewById(R.id.share);
        rate=findViewById(R.id.rate);
        privacy=findViewById(R.id.privacy);

        MyAds myAds=new MyAds(this);
        /**----Banner Ad-----*/

        myAds.adViewLoad(findViewById(R.id.adViewAd));
        /**----Native Ad-----*/
        myAds.NativeAds(Start_Activity.this,findViewById(R.id.my_template));

        start.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                /**----Interstitial Ad-----*/

                Intent intent = new Intent(Start_Activity.this,MainActivity.class);
                new MyAds(getApplicationContext()).InterstitialADS(Start_Activity.this,intent);
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent share=new Intent(Intent.ACTION_SEND);
                share.setType("text/plain");
                startActivity(Intent.createChooser(share,"share via"));
            }
        });
        rate.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent rate=new Intent(getApplicationContext(),Rate_Us.class);
                startActivity(rate);
            }
        });
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                Intent privacy = new Intent(Intent.ACTION_VIEW);
                privacy.setData(Uri.parse("https://www.freeprivacypolicy.com/blog/privacy-policy-url/"));
                startActivity(privacy);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Dialog customdialog = new Dialog(this);
        customdialog.getWindow().setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        customdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        customdialog.getWindow().getAttributes().windowAnimations = android.R.style.Animation_Dialog;
        customdialog.setContentView(R.layout.activity_dialog_box);
        new MyAds(this).NativeAds(Start_Activity.this,customdialog.findViewById(R.id.my_template_Ad));
        Button yesbtn = customdialog.findViewById(R.id.yes);
        Button nobtn = customdialog.findViewById(R.id.no);
        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Thankyou.class);
                startActivity(intent);
            }
        });
        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customdialog.cancel();
            }
        });
        customdialog.show();
    }

}