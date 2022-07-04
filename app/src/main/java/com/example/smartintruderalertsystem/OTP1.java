package com.example.smartintruderalertsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OTP1 extends AppCompatActivity {
    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp1);
        getSupportActionBar().hide();
        enternumber=findViewById(R.id.inputmobilenumber);
        getotpbutton=findViewById(R.id.buttongetotp);

        final ProgressBar progressBar = findViewById(R.id.progressbarsending);

        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!enternumber.getText().toString().trim().isEmpty())
                {
                    if ((enternumber.getText().toString().trim()).length()==10)
                    {

                        progressBar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + enternumber.getText().toString(), 90, TimeUnit.SECONDS
                                , OTP1.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);
                                        Toast.makeText(OTP1.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.VISIBLE);
                                        getotpbutton.setVisibility(View.INVISIBLE);

                                        Intent intent= new Intent(getApplicationContext(),OTP2.class);
                                        intent.putExtra("Mobile",enternumber.getText().toString());
                                        intent.putExtra("backendotp",backendotp);
                                        startActivity(intent);
                                    }
                                }
                        );




                    }
                    else
                    {
                        Toast.makeText(OTP1.this, "Please enter correct number", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(OTP1.this, "Enter Mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}