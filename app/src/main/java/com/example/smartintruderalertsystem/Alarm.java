package com.example.smartintruderalertsystem;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.smartintruderalertsystem.databinding.ActivityAlarmBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Alarm extends AppCompatActivity {
    private static final String TAG = "Alarm";

    private ActivityAlarmBinding binding;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAlarmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reference = FirebaseDatabase.getInstance().getReference();

        binding.on.setOnClickListener(v -> {
            reference
                    .child("User")
                    .child("1")
                    .child("alarm")
                    .setValue("1")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: On");
                        }
                    });

        });

        binding.off.setOnClickListener(v -> {
            reference
                    .child("User")
                    .child("1")
                    .child("alarm")
                    .setValue("0")
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: Off");
                        }
                    });

        });
    }
}