package com.example.loginandregisterfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.loginandregisterfirebase.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        mAuth = FirebaseAuth.getInstance();

        binding.tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.progressPar.setVisibility(View.VISIBLE);
                String email = String.valueOf(binding.edEmail.getText());
                String password = String.valueOf(binding.edPassword.getText());

                if (TextUtils.isEmpty(email) && email.endsWith("@gmail.com")&& email.length() > 15){
                    Toast.makeText(Register.this, "Please enter valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Register.this, "Please enter valid password", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                binding.progressPar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account created", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Register.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                } else {

                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });






    }
}