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


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText txtEmail,txtPassword;
    private Button btnRegister;
    private TextView txtView;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);

        btnRegister = findViewById(R.id.btnRegister);
        txtView = findViewById(R.id.txtLoginHere);

        btnRegister.setOnClickListener(this);
        txtView.setOnClickListener(this);


    }
    public void Register(){
        String email = txtEmail.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();
        progressDialog.setMessage("Registering User...");
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

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(RegisterActivity.this, "Not Register the Email is already exist please try again... ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if(view == btnRegister){
            Register();
        }
        if(view == txtView){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
    }

}
