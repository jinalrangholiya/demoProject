package com.example.demo;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ProcessLifecycleOwner;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.appopen.AppOpenAd;

import java.util.Date;

public class AppOpenManager implements LifecycleObserver, Application.ActivityLifecycleCallbacks {

    private static final String LOG_TAG = "AppOpenManager";
    private static final String AD_UNIT_ID = "ca-app-pub-3940256099942544/3419835294";
    private AppOpenAd appOpenAd = null;
    private Activity currentActivity;
    private static boolean AdShowingAd = false;
    private long loadtime = 0;


    private AppOpenAd.AppOpenAdLoadCallback loadCallback;

    private final MyApplication myApplication;

    public AppOpenManager(MyApplication myApplication) {
        this.myApplication = myApplication;
        this.myApplication.registerActivityLifecycleCallbacks(this);
        ProcessLifecycleOwner.get().getLifecycle().addObserver(this);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    public void onStart() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ShowAdIfAvailable();
                fetchAd();
                Log.d(LOG_TAG, "onStart");
            }
        }, 2000);

    }

    private void fetchAd() {
        if (isAdAvailable()) {
            return;
        }
        loadCallback = new AppOpenAd.AppOpenAdLoadCallback() {
            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                super.onAdFailedToLoad(loadAdError);
                Log.e( "onAdFailedToLoad: app open ::", loadAdError.getMessage());
            }

            @Override
            public void onAdLoaded(@NonNull AppOpenAd ad) {
                Log.e( "onAdLoaded: ", "app open");
                AppOpenManager.this.appOpenAd = ad;
                AppOpenManager.this.loadtime = (new Date().getTime());
                super.onAdLoaded(ad);
            }
        };
        AdRequest request = getAdRequest();
        AppOpenAd.load(myApplication, AD_UNIT_ID, request, AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback);

    }

    private AdRequest getAdRequest() {
        return new AdRequest.Builder().build();
    }

    public Boolean isAdAvailable() {
        return appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4);
    }

    private boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        long dateDifference = (new Date()).getTime() - this.loadtime;
        long numMilliSecondsPerHour = 3600000;
        return (dateDifference < (numMilliSecondsPerHour * numHours));
    }

    public void ShowAdIfAvailable() {
        if (!AdShowingAd && isAdAvailable()) {
            Log.d(LOG_TAG, "Ad show ");

            FullScreenContentCallback fullScreenContentCallback = new FullScreenContentCallback() {

                @Override
                public void onAdDismissedFullScreenContent() {
                    AppOpenManager.this.appOpenAd = null;
                    AdShowingAd = false;
                    fetchAd();
                }

                @Override
                public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                    super.onAdFailedToShowFullScreenContent(adError);
                }


                @Override
                public void onAdShowedFullScreenContent() {
                    AdShowingAd = true;
                }
            };
            appOpenAd.setFullScreenContentCallback(fullScreenContentCallback);
            appOpenAd.show(currentActivity);
        } else {
            Log.d(LOG_TAG, "not Show Ad");
            fetchAd();
        }
    }


    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {

        currentActivity = activity;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

        currentActivity = activity;
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {

    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

        currentActivity = null;
    }



}


