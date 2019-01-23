package com.coin06.mine.nbit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;

public class MoreActivity extends AppCompatActivity implements RewardedVideoAdListener,View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button btnShare, btnF,btnFa,btnFaa;
    private Button  btnA,btnAa,btnAaa;
    private Button  btnB,btnBa,btnBaa;
    private Button  btnC,btnCa,btnCaa;
    private RatingBar ratingBar;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;
    private ProgressDialog progressDialog;
    SharedPreferences preferences;
    int btc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("load video reward...");

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }

        preferences = getSharedPreferences("myperfs", Context.MODE_PRIVATE);
        btc = preferences.getInt("btc", 0);


        ratingBar = findViewById(R.id.ratingBar);
        btnShare = findViewById(R.id.btnShare);
        btnF = findViewById(R.id.btnF);
        btnFa = findViewById(R.id.btnFa);
        btnFaa = findViewById(R.id.btnFaa);

        btnA = findViewById(R.id.btnA);
        btnAa = findViewById(R.id.btnAa);
        btnAaa = findViewById(R.id.btnAaa);

        btnB = findViewById(R.id.btnB);
        btnBa = findViewById(R.id.btnBa);
        btnBaa = findViewById(R.id.btnBaa);

        btnC = findViewById(R.id.btnC);
        btnCa = findViewById(R.id.btnCa);
        btnCaa = findViewById(R.id.btnCaa);
            //Banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //InterstitialAd
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7584874423919070/3679232702");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());


        //MobileAds.initialize(this,"ca-app-pub-7584874423919070/6399930666");
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-7584874423919070/6399930666",new AdRequest.Builder().build());


        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.coin06.mine.nbit")));
                        preferences.edit().putInt("btc", btc + 20).commit();
                        Toast.makeText(MoreActivity.this, "10000", Toast.LENGTH_SHORT).show();


                }


            });


            btnShare.setOnClickListener(this);

            btnF.setOnClickListener(this);
            btnFa.setOnClickListener(this);
            btnFaa.setOnClickListener(this);
            btnA.setOnClickListener(this);
            btnAa.setOnClickListener(this);
            btnAaa.setOnClickListener(this);

            btnB.setOnClickListener(this);
            btnBa.setOnClickListener(this);
            btnBaa.setOnClickListener(this);

            btnC.setOnClickListener(this);
            btnCa.setOnClickListener(this);
            btnCaa.setOnClickListener(this);

        }

        public void Share(){
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share the app and get 5000 KH/s on every install");
            String sharbody = "https://play.google.com/store/apps/details?id=com.coin06.mine.nbit";
            // shareIntent.putExtra(Intent.EXTRA_SUBJECT,"Share the app and get 5000 KH/s on every install"+sharbody);
            shareIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<h3><font color=\"#fc8f0b\">Share the app and get 10000 KH/s on every install</font> </h3> ") + sharbody);
            startActivity(Intent.createChooser(shareIntent, "share via"));
            preferences.edit().putInt("btc", btc + 20).commit();
            Toast.makeText(MoreActivity.this, "10000", Toast.LENGTH_SHORT).show();
        }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
 if(rewardItem == btnF){
     preferences.edit().putInt("btc", btc + 6000).commit();
     Toast.makeText(MoreActivity.this, "6500", Toast.LENGTH_SHORT).show();
 }
        if(rewardItem == btnFa){
            preferences.edit().putInt("btc", btc + 6500).commit();
            Toast.makeText(MoreActivity.this, "6500", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnFaa){
            preferences.edit().putInt("btc", btc + 7000).commit();
            Toast.makeText(MoreActivity.this, "7000", Toast.LENGTH_SHORT).show();
        }

        if(rewardItem == btnA){
            preferences.edit().putInt("btc", btc + 7500).commit();
            Toast.makeText(MoreActivity.this, "7500", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnAa){
            preferences.edit().putInt("btc", btc + 8000).commit();
            Toast.makeText(MoreActivity.this, "8000", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnAaa){
            preferences.edit().putInt("btc", btc + 8500).commit();
            Toast.makeText(MoreActivity.this, "8500", Toast.LENGTH_SHORT).show();
        }

        if(rewardItem == btnB){
            preferences.edit().putInt("btc", btc + 9000).commit();
            Toast.makeText(MoreActivity.this, "9000", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnBa){
            preferences.edit().putInt("btc", btc + 9500).commit();
            Toast.makeText(MoreActivity.this, "9500", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnBaa){
            preferences.edit().putInt("btc", btc + 10000).commit();
            Toast.makeText(MoreActivity.this, "10000", Toast.LENGTH_SHORT).show();
        }

        if(rewardItem == btnC){
            preferences.edit().putInt("btc", btc + 10500).commit();
            Toast.makeText(MoreActivity.this, "10500", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnCa){
            preferences.edit().putInt("btc", btc + 11000).commit();
            Toast.makeText(MoreActivity.this, "11000", Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnCaa){
            preferences.edit().putInt("btc", btc + 11500).commit();
            Toast.makeText(MoreActivity.this, "11500", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onClick(View view) {
        if(view == btnShare){
            Share();
        }
        if(view == btnF){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnFa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnFaa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }

        if(view == btnA){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnAa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnAaa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }

        if(view == btnB){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnBa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnBaa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }

        if(view == btnC){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnCa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
        if(view == btnCaa){
            progressDialog.show();
            if (mRewardedVideoAd.isLoaded()) {
                mRewardedVideoAd.show();
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                    progressDialog.dismiss();
                }
            }else {
                progressDialog.dismiss();
                Toast.makeText(this,"there is no Avilabe video ads now",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
