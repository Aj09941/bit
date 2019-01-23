package com.coin06.mine.nbit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEarn;
    private Button btnWithdraw;
    private Button btnMore;
    private Button btnHow;
    private Button btnLogout;
    private FirebaseAuth firebaseAuth;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }

        btnEarn = findViewById(R.id.btnPlay);
        btnWithdraw = findViewById(R.id.btnWithdraw);
        btnMore = findViewById(R.id.btnMore);
        btnHow = findViewById(R.id.btnHow);
        btnLogout = findViewById(R.id.btnLogout);
        //InterstitialAd
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7584874423919070/3334783629");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
            btnEarn.setOnClickListener(this);
            btnWithdraw.setOnClickListener(this);
            btnMore.setOnClickListener(this);
            btnHow.setOnClickListener(this);
            btnLogout.setOnClickListener(this);

            //Banner
            mAdView = findViewById(R.id.adView);
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);



    }
    @Override
    public void onClick(View view) {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }else {
            if (view == btnEarn) {
                startActivity(new Intent(this, EarnActivity.class));
            }
            if (view == btnWithdraw) {
                startActivity(new Intent(this, WithdrawActivity.class));
            }
            if (view == btnMore) {
                startActivity(new Intent(this, MoreActivity.class));
            }
            if (view == btnHow) {
                startActivity(new Intent(this, HowActivity.class));
            }
            if (view == btnLogout) {
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, RegisterActivity.class));
            }

        }
    }
}
