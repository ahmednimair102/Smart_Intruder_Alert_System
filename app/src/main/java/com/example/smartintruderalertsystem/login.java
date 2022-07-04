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

public class login extends AppCompatActivity {

    EditText  mEmail, mPassword;
    Button mLogin;
    TextView registertxt,signupback;
    FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        mEmail= findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mLogin = findViewById(R.id.loginbtn);
        mAuth=FirebaseAuth.getInstance();
        registertxt = findViewById(R.id.signup);
        signupback=findViewById(R.id.signup);


        signupback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,registration.class));
            }
        });



        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email =mEmail.getText().toString();
                String password=mPassword.getText().toString();

                if (TextUtils.isEmpty(email))
                {
                    mEmail.setError("Email cannot be empty");
                    mEmail.requestFocus();
                }
                else if (TextUtils.isEmpty(password))
                {
                    mPassword.setError("password cannot be empty");
                    mPassword.requestFocus();
                }
                else
                {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(login.this, "user Login successfull", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(login.this,Home.class));

                            }
                            else
                            {
                                Toast.makeText(login.this, "Login Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            }
        });






    }
}