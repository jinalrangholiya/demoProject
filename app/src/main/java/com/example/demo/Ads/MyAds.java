package com.example.demo.Ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.ads.nativetemplates.NativeTemplateStyle;
import com.google.android.ads.nativetemplates.TemplateView;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.ads.nativead.NativeAd;

public class  MyAds extends Activity {

    private static final String Banner_Ad_Id = "ca-app-pub-3940256099942544/6300978111";
    private static final String Interstitrial_Ad_Id = "ca-app-pub-3940256099942544/1033173712";
    private static final String Native_Ad_ID = "ca-app-pub-3940256099942544/2247696110";
    private  Context context;
    AdRequest adRequest = new AdRequest.Builder().build();
    public static InterstitialAd mInterstitialAd=null;

    public MyAds(Context context) {
        this.context = context;
    }

    /**----- Banner Ads------*/
    public void adViewLoad(AdView adView1) {
        AdView adView=new AdView(context);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(Banner_Ad_Id);
        adView1.addView(adView);
        adView.loadAd(adRequest);


    }
    /**----- Native Ads------*/
    public void NativeAds(Context context,TemplateView template)
    {
        AdLoader adLoader = new AdLoader.Builder(context, Native_Ad_ID)
                .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                    @Override
                    public void onNativeAdLoaded(NativeAd nativeAd) {
                        NativeTemplateStyle styles = new
                                NativeTemplateStyle.Builder().build();
                       template.setStyles(styles);
                       template.setNativeAd(nativeAd);
                    }

                })
                .build();

        adLoader.loadAd(new AdRequest.Builder().build());
    }
    /**----- Interstitial Ads------*/
    public void InterstitialADS(Context context,Intent intent) {


            InterstitialAd.load(context.getApplicationContext(), Interstitrial_Ad_Id, adRequest, new InterstitialAdLoadCallback() {
                @Override
                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                    super.onAdFailedToLoad(loadAdError);
                    Log.d("my app", loadAdError.toString());
                    context.startActivity(intent);

                }

                @Override
                public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                    mInterstitialAd = interstitialAd;
                    Log.i("my app", "onAdLoaded");
                    if (mInterstitialAd != null)
                    {
                        mInterstitialAd.show((Activity) context);
                    }
                    else
                    {
                        Log.d("my app", "The interstitial ad wasn't ready yet.");
                    }
                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                        @Override
                        public void onAdClicked() {
                            super.onAdClicked();
                        }

                        @Override
                        public void onAdDismissedFullScreenContent() {
                            Log.d("my app","show new Activity");
                            context.startActivity(intent);
                            super.onAdDismissedFullScreenContent();

                        }

                        @Override
                        public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                            super.onAdFailedToShowFullScreenContent(adError);
                            mInterstitialAd=null;

                        }

                        @Override
                        public void onAdImpression() {
                            super.onAdImpression();
                        }

                        @Override
                        public void onAdShowedFullScreenContent() {
                            super.onAdShowedFullScreenContent();
                        }
                    });
                    super.onAdLoaded(interstitialAd);

                }

             });

    }

}
