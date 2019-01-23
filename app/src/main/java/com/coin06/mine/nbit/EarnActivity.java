package com.coin06.mine.nbit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class EarnActivity extends AppCompatActivity implements View.OnClickListener, RewardedVideoAdListener {
    private TextView txtBtc,textValue;
    private ImageView spin;
    private Button btnSpin;
    private Button btnOne,btnTwo,btnThree,btnFour,btnFive;
    private Button btnSix,btnSeven,btnEhgit,btnNine,btnTen;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;
    private SharedPreferences prefs;
    int counter = 0;
    int b;
    int c;
    Random r;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    int degree = 0, degree_old = 0;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("load video reward...");

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }
        //store value of counter

        databaseReference = FirebaseDatabase.getInstance().getReference();

        prefs = getSharedPreferences("myperfs", MODE_PRIVATE);
        counter = prefs.getInt("btc", 0);
        r = new Random();
//system buttons


        txtBtc = findViewById(R.id.txtBtc);
        textValue = findViewById(R.id.textValue);
        spin = findViewById(R.id.imageSpin);
        btnSpin = findViewById(R.id.btnSpin);
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnThree = findViewById(R.id.btnThree);
        btnFour = findViewById(R.id.btnFour);
        btnFive = findViewById(R.id.btnFive);

        btnSix = findViewById(R.id.btnSix);
        btnSeven = findViewById(R.id.btnSeven);
        btnEhgit = findViewById(R.id.btnEight);
        btnNine = findViewById(R.id.btnNine);
        btnTen = findViewById(R.id.btnTen);

        //Banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //InterstitialAd
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7584874423919070/3334783629");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-7584874423919070/6399930666",new AdRequest.Builder().build());


        btnSpin.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEhgit.setOnClickListener(this);
        btnNine.setOnClickListener(this);
        btnTen.setOnClickListener(this);

    }
    @Override
    protected void onPause(){
        super.onPause();
        //freez value of counter
        prefs.edit().putInt("btc",counter).commit();

    }
    public void Count(){
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            b = counter += 250;
            c = b / 6;
            txtBtc.setText("" + b);
            textValue.setText("" + c);

            FirebaseUser user = firebaseAuth.getCurrentUser();

            databaseReference.child("BTC Value").child(user.getUid()).setValue(c);
        }

    }
    public void OnSpin() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else{
            degree_old = degree % 360;
        degree = r.nextInt(3600) + 720;

        //Rotate
        RotateAnimation rotate = new RotateAnimation(degree_old, degree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(3600);
        rotate.setFillAfter(true);
        rotate.setInterpolator(new DecelerateInterpolator());
        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        spin.startAnimation(rotate);
    }
    }

    @Override
    public void onClick(View view) {
        if(btnSpin == view){
        Count();
        OnSpin();
        }
        if(view == btnOne){
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
        if(view == btnTwo){
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
        if(view == btnThree){
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
        if(view == btnFour){
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
        if(view == btnFive){
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
        if(view == btnSix){
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
        if(view == btnSeven){
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
        if(view == btnEhgit){
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
        if(view == btnNine){
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
        if(view == btnTen){
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
        if(rewardItem == btnOne){
            prefs.edit().putInt("btc",counter).commit();
            counter+=1000;
            Toast.makeText(EarnActivity.this,"1000",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnTwo){
            prefs.edit().putInt("btc",counter).commit();
            counter+=1500;
            Toast.makeText(EarnActivity.this,"1500",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnThree){
            prefs.edit().putInt("btc",counter).commit();
            counter+=2000;
            Toast.makeText(EarnActivity.this,"2000",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnFour){
            prefs.edit().putInt("btc",counter).commit();
            counter+=2500;
            Toast.makeText(EarnActivity.this,"2500",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnFive){
            prefs.edit().putInt("btc",counter).commit();
            counter+=3000;
            Toast.makeText(EarnActivity.this,"3000",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnSix){
            prefs.edit().putInt("btc",counter).commit();
            counter+=3500;
            Toast.makeText(EarnActivity.this,"3500",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnSeven){
            prefs.edit().putInt("btc",counter).commit();
            counter+=4000;
            Toast.makeText(EarnActivity.this,"4000",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnEhgit){
            prefs.edit().putInt("btc",counter).commit();
            counter+=4500;
            Toast.makeText(EarnActivity.this,"4500",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnNine){
            prefs.edit().putInt("btc",counter).commit();
            counter+=5000;
            Toast.makeText(EarnActivity.this,"5000",Toast.LENGTH_SHORT).show();
        }
        if(rewardItem == btnTen){
            prefs.edit().putInt("btc",counter).commit();
            counter+=5500;
            Toast.makeText(EarnActivity.this,"55000",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
