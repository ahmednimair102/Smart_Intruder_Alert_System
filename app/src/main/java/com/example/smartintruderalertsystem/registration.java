package com.example.smartintruderalertsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registration extends AppCompatActivity {

    EditText mUsername,mEmail,mPassword,mConfirm;
    Button mRegisterbtn;
    TextView mLogin;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        mUsername = findViewById(R.id.Name);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);
        mConfirm = findViewById(R.id.confirmpassword);
        mRegisterbtn =findViewById(R.id.register);
        mLogin = findViewById(R.id.signup);
        mAuth = FirebaseAuth.getInstance();




        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),login.class));
                Toast.makeText(registration.this, "Welcome to login", Toast.LENGTH_SHORT).show();
            }
        });



        mRegisterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String username =mUsername.getText().toString();
                String email =mEmail.getText().toString();
                String password=mPassword.getText().toString();
                String comfirmpassword=mConfirm.getText().toString();


                if (TextUtils.isEmpty(username)){
                    mUsername.setError("Name is required");
                    return;

                }
                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    mPassword.setError("Password is required");
                    return;
                }
                if(password.length()>6){
                    mPassword.setError("Password must be 6 chracter long");
                    return;

                }

                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(registration.this, "user register successfull", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(registration.this,OTP1.class));
                        }
                        else
                        {
                            Toast.makeText(registration.this, "Registration Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }
}