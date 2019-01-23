package com.coin06.mine.nbit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etxtEmail,etxtPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        etxtEmail = findViewById(R.id.etxtPassword);
        etxtPassword = findViewById(R.id.etxtPassword);

        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(this);
    }
    public void Login(){
        String email = etxtEmail.getText().toString().trim();
        String password = etxtPassword.getText().toString().trim();

        progressDialog.setMessage("Login...");
        progressDialog.show();
        if(TextUtils.isEmpty(email)){
            progressDialog.dismiss();
            Toast.makeText(this,"Some Fields are Missing",Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            progressDialog.dismiss();
            Toast.makeText(this,"Some Fields are Missing",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(LoginActivity.this,MainActivity.class));
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this,"Login not successful, Please try again..",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if(view == btnLogin){
            Login();
        }
    }
}
