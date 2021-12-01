package com.nareshgediya.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdOptionsView;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeBannerAd;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private AdView adView1;
    private InterstitialAd interstitialAd;
    private NativeAdLayout nativeAdLayout;
    private LinearLayout adViewNative;
    private NativeBannerAd nativeBannerAd;
    private final String TAG = MainActivity.class.getSimpleName();


    ImageView notifyImg;
     ImageView image ;
     ConstraintLayout layout;

    TextView statusview;


     Switch switch1;

    Button restratBtn;
    boolean gameActive = true;
    // Player Represent
    // 0 = x
    // 1 = O


    int activePlayer = 0;
   int gamestat[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};


    Random random;


    //stat
    // 0 = x
    // 1 = O
    // 2 = null Empty

    int[][] WinPositions = {{0, 1, 2,}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    int[] comImag = {
            R.id.imageView0,
            R.id.imageView1,
            R.id.imageView2,
            R.id.imageView3,
            R.id.imageView4,
            R.id.imageView5,
            R.id.imageView6,
            R.id.imageView7,
            R.id.imageView8
    };


    public void gameRestart() {
        // When Tapping On Restart Button...
        ((ImageView) findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);

        notifyImg = findViewById(R.id.notify);

        notifyImg.setVisibility(View.GONE);

        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gamestat.length; i++) {
            gamestat[i] = 2;
        }
        TextView statusview = findViewById(R.id.status);
        statusview.setText("Tap to Play");
    }

 //   ArrayList<Integer> gamestat;

    public void playerTap(View view) {
        random = new Random();
        int randomNo = random.nextInt(9);

        ImageView img = (ImageView) view;


        int TappedImg = Integer.parseInt(img.getTag().toString());



        if (gamestat[TappedImg] == 2) {
            gamestat[TappedImg] = activePlayer;

            // This is a Transition view of image of X and O....
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                // Here This if else Condition is for Changing Turn....And Show TextView of turn and Image view of O and x..
                img.setImageResource(R.drawable.x12);

                activePlayer = 1;
                TextView statusview = findViewById(R.id.status);
                statusview.setText("O Turn");

            } else {
                img.setImageResource(R.drawable.ox);
                activePlayer = 0;
                TextView statusview = findViewById(R.id.status);
                statusview.setText("X Turn");

            }
            img.animate().translationYBy(1000f).setDuration(300);

        }



        // Check for Win...

        notifyImg = findViewById(R.id.notify);

        for (int[] winPosition : WinPositions) {
            final TextView statusview = findViewById(R.id.status);
            String winstr;

            if (gamestat[winPosition[0]] == gamestat[winPosition[1]] && gamestat[winPosition[1]] == gamestat[winPosition[2]]
                    && gamestat[winPosition[0]] != 2
            )
            {
                // Someone is Won !! Find Who ..?

                if (gamestat[winPosition[0]] == 0) {
                    notifyImg.setImageResource(R.drawable.xnoti);

                    statusview.setText("X is Won");

                    Animation animation = AnimationUtils.loadAnimation(this,R.anim.noti);
                    notifyImg.setVisibility(View.VISIBLE);
                    notifyImg.setAnimation(animation);

                    notifyImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            gameActive = false;
                            gameRestart();
                        }
                    });

                }
                else {
                    notifyImg.setImageResource(R.drawable.onoti);

                    statusview.setText("O is Won");

                    Animation animation = AnimationUtils.loadAnimation(this, R.anim.noti);
                    notifyImg.setVisibility(View.VISIBLE);
                    notifyImg.setAnimation(animation);

                    notifyImg.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            gameActive = false;
                            gameRestart();



                        }
                    });
                }
                }
            }
        }

    private int comPlay(int activePlayer, int randomNo) {


            if (gamestat[randomNo] == 2) {
                gamestat[randomNo] = activePlayer;

                ImageView imageView = findViewById(comImag[randomNo]);
                imageView.setImageResource(R.drawable.o1);
                activePlayer = 0;
                 statusview = findViewById(R.id.status);
                statusview.setText("O Turn");

        }else {
            random = new Random();
            randomNo = random.nextInt(9);

            if (gamestat[randomNo] == 2) {
                gamestat[randomNo] = activePlayer;
                Toast.makeText(this, "comPlay  "+randomNo, Toast.LENGTH_SHORT).show();
                ImageView imageView = findViewById(comImag[randomNo]);
                imageView.setImageResource(R.drawable.o1);
                activePlayer = 0;
            statusview = findViewById(R.id.status);
                statusview.setText("O Turn");
            }

        }
        return activePlayer;
    }

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            AudienceNetworkAds.initialize(MainActivity.this);

            switch1 = findViewById(R.id.switch1);
            image = findViewById(R.id.imageView);
            statusview = findViewById(R.id.status);
                  layout = findViewById(R.id.layout1);

// Instantiate an AdView object.
// NOTE: The placement ID from the Facebook Monetization Manager identifies your App.
// To get test ads, add IMG_16_9_APP_INSTALL# to your placement id. Remove this when your app is ready to serve real ads.

            adView1 = new AdView(this,"IMG_16_9_APP_INSTALL#339962074271690_339963117604919", AdSize.BANNER_HEIGHT_50);
            interstitialAd = new InterstitialAd(this, "IMG_16_9_APP_INSTALL#339962074271690_339963117604919");
            nativeBannerAd = new NativeBannerAd(this, "IMG_16_9_APP_INSTALL#339962074271690_339963117604919");

            // For auto play video ads, it's recommended to load the ad
            // at least 30 seconds before it is shown

     LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);

            adContainer.addView(adView1);



  //    showBannerAd();
//          interstitialAdShow();
//            showNativBannerAd();

            switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do something, the isChecked will be
                    // true if the switch is in the On position

                    if (isChecked){
                        image.setImageResource(R.drawable.tbg);
                        switch1.setText("Light   ");
                        switch1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textcolor));

                        statusview.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textcolor));
                        statusview.setBackgroundResource(R.drawable.button_two);
                   layout.setBackgroundResource(R.color.light_bg);
                        restratBtn.setBackgroundResource(R.drawable.button_two);
                        restratBtn.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.textcolor));

                    }else {
                        image.setImageResource(R.drawable.darkmode);
                        switch1.setText("Dark   ");
                        statusview.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        switch1.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                        statusview.setBackgroundResource(R.drawable.buttondark);
                        layout.setBackgroundResource(R.color.dark_bg);
                        restratBtn.setBackgroundResource(R.drawable.buttondark);
                        restratBtn.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white));
                    }
                }
            });





            restratBtn = findViewById(R.id.restartBtn);

            restratBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameRestart();
                }
            });
        }

    private void inflateAd(NativeBannerAd nativeBannerAd) {
        // Unregister last ad
        nativeBannerAd.unregisterView();

        // Add the Ad view into the ad container.
        nativeAdLayout = findViewById(R.id.native_banner_ad_container);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        // Inflate the Ad view.  The layout referenced is the one you created in the last step.
        adViewNative = (LinearLayout) inflater.inflate(R.layout.native_banner_ad_unit, nativeAdLayout, false);
        nativeAdLayout.addView(adViewNative);

        // Add the AdChoices icon
        RelativeLayout adChoicesContainer = adViewNative.findViewById(R.id.ad_choices_container);
        AdOptionsView adOptionsView = new AdOptionsView(MainActivity.this, nativeBannerAd, nativeAdLayout);
        adChoicesContainer.removeAllViews();
        adChoicesContainer.addView(adOptionsView, 0);

        // Create native UI using the ad metadata.
        TextView nativeAdTitle =         adViewNative.findViewById(R.id.native_ad_title);
        TextView nativeAdSocialContext = adViewNative.findViewById(R.id.native_ad_social_context);
        TextView sponsoredLabel =        adViewNative.findViewById(R.id.native_ad_sponsored_label);
        MediaView nativeAdIconView =     adViewNative.findViewById(R.id.native_icon_view);
        Button nativeAdCallToAction =    adViewNative.findViewById(R.id.native_ad_call_to_action);

        // Set the Text.
        nativeAdCallToAction.setText(nativeBannerAd.getAdCallToAction());
        nativeAdCallToAction.setVisibility(
                nativeBannerAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
        nativeAdTitle.setText(nativeBannerAd.getAdvertiserName());
        nativeAdSocialContext.setText(nativeBannerAd.getAdSocialContext());
        sponsoredLabel.setText(nativeBannerAd.getSponsoredTranslation());

        // Register the Title and CTA button to listen for clicks.
        List<View> clickableViews = new ArrayList<>();
        clickableViews.add(nativeAdTitle);
        clickableViews.add(nativeAdCallToAction);
        nativeBannerAd.registerViewForInteraction(adViewNative, nativeAdIconView, clickableViews);
    }

    private void showNativBannerAd() {

        NativeAdListener nativeAdListener = new NativeAdListener() {

            @Override
            public void onMediaDownloaded(Ad ad) {
                // Native ad finished downloading all assets
                Log.e(TAG, "Native ad finished downloading all assets.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Native ad failed to load
                Toast.makeText(MainActivity.this, adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Native ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (nativeBannerAd == null || nativeBannerAd != ad) {
                    return;
                }
                // Inflate Native Banner Ad into Container
                inflateAd(nativeBannerAd);
                // Native ad is loaded and ready to be displayed
                Log.d(TAG, "Native ad is loaded and ready to be displayed!");
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Native ad clicked
                Log.d(TAG, "Native ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Native ad impression
                Log.d(TAG, "Native ad impression logged!");
            }
        };
        // load the ad
        nativeBannerAd.loadAd(
                nativeBannerAd.buildLoadAdConfig()
                        .withAdListener(nativeAdListener)
                        .build());

    }

    private void showBannerAd() {
        AdListener adListener = new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
// Ad error callback

                Toast.makeText(
                        MainActivity.this,
                        "Error: " + adError.getErrorMessage(),
                        Toast.LENGTH_LONG)
                        .show();
                Log.d(TAG,adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {


            }

            @Override
            public void onAdClicked(Ad ad) {
// Ad clicked callback
            }

            @Override
            public void onLoggingImpression(Ad ad) {
// Ad impression logged callback
            }
        };
// Request an ad

        adView1.loadAd(adView1.buildLoadAdConfig().withAdListener(adListener).build());
    }

    private void interstitialAdShow() {
        InterstitialAdListener interstitialAdListener = new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
                // Interstitial ad displayed callback
                Log.d(TAG, "Interstitial ad displayed.");
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                // Interstitial dismissed callback
                Log.d(TAG, "Interstitial ad dismissed.");
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                // Ad error callback
                Toast.makeText(MainActivity.this,"Interstitial : "+ adError.getErrorMessage(), Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Interstitial ad failed to load: " + adError.getErrorMessage());
            }

            @Override
            public void onAdLoaded(Ad ad) {
                // Interstitial ad is loaded and ready to be displayed
                Log.d(TAG, "Interstitial ad is loaded and ready to be displayed!");
                // Show the ad
                interstitialAd.show();
            }

            @Override
            public void onAdClicked(Ad ad) {
                // Ad clicked callback
                Log.d(TAG, "Interstitial ad clicked!");
            }

            @Override
            public void onLoggingImpression(Ad ad) {
                // Ad impression logged callback
                Log.d(TAG, "Interstitial ad impression logged!");
            }
        };

        interstitialAd.loadAd(
                interstitialAd.buildLoadAdConfig()
                        .withAdListener(interstitialAdListener)
                        .build());

    }

    @Override
    protected void onDestroy() {
        if (adView1 != null) {
            adView1.destroy();
        }
        if (interstitialAd != null) {
            interstitialAd.destroy();
        }
        super.onDestroy();
    }

    }
