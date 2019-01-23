package com.coin06.mine.nbit;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static java.lang.String.valueOf;

public class WithdrawActivity extends AppCompatActivity{

    private EditText edAddress;
    private Button btnWithdraw ,btnSave;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    private TextView textBTCv;
    int btcvaluec;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        firebaseAuth = FirebaseAuth.getInstance();


        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, RegisterActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();
        textBTCv = findViewById(R.id.textBTCv);
        SharedPreferences preferences = getSharedPreferences("myperfs", Context.MODE_PRIVATE);
        btcvaluec = preferences.getInt("btc", 0);
        textBTCv.setText(btcvaluec / 6 + " KH/s");

        //Banner
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //InterstitialAd
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7584874423919070/3334783629");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
            edAddress = findViewById(R.id.edAddress);
            btnWithdraw = findViewById(R.id.btnWithdraww);
            btnSave = findViewById(R.id.btnSave);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String btcAddress = edAddress.getText().toString().trim();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    databaseReference.child("BTC Address").child(user.getUid()).setValue(btcAddress);
                    Toast.makeText(WithdrawActivity.this, "The btc Address saved", Toast.LENGTH_LONG).show();

                }
            });
            btnWithdraw.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (btcvaluec >= 35000000) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawActivity.this);
                        builder.setTitle(" Balance");
                        builder.setMessage("Your request for withdraw has been submit.  \nThe process will take 5 day to arrive");

                        //negative message
                        builder.setNegativeButton("Exit",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                    }
                                });
                        //positive message
                        builder.show();

                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(WithdrawActivity.this);
                        builder.setTitle(" Balance");
                        builder.setMessage("Minimum withdraw balance 350000000 KH/s \nThe withdraw will proceed manually Every 20th of month  ");

                        //negative message
                        builder.setNegativeButton("Exit",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        finish();
                                        startActivity(new Intent(getApplicationContext(),WithdrawActivity.class));
                                    }
                                });
                        //positive message
                        builder.show();
                    }
                }
            });

        }

}
