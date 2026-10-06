package com.example.firstfirebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.firstfirebaseproject.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    private String vertificationId;
    private FirebaseAuth mAuth;
    private boolean getOtpCliked = false;
    FirebaseUser user;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        binding.getOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOtpOnClick();
            }
        });
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginClicked();
            }
        });
        binding.btnLogin.setTextColor(Color.parseColor("#C0BEBE"));

    }

    public void onLoginClicked() {
        String number = binding.txtPhone.getText().toString().trim();
        String otp1 = binding.txtOtp.getText().toString().trim();

        if (number.length() == 10 && otp1.length() > 4) {
            InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
            dialog = new Dialog(MainActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_wait);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            vertifyCode(otp1);

        }else {
            if (number.isEmpty() || number.length() < 10) {
                binding.txtPhone.setError("Valid number is required");
                binding.txtPhone.requestFocus();
            }else if (otp1.isEmpty() || otp1.length() < 5) {
                binding.txtOtp.setError("Valid OTP is required");
                binding.txtOtp.requestFocus();
            }
        }
    }

    private void vertifyCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(vertificationId,code);
        FirebaseAuth.getInstance().getFirebaseAuthSettings().setAppVerificationDisabledForTesting(true);
        singInWithCredential(credential);
    }

    private void singInWithCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                startActivity(intent);
                                finish();
                            }else {
                                if (dialog != null) {
                                    dialog.dismiss();
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                });
    }

    public void getOtpOnClick() {
        if (!getOtpCliked) {
            String num = binding.txtPhone.getText().toString().trim();
            if (num.length() != 10) {
                binding.txtPhone.setError("Valid number is required");
                binding.txtPhone.requestFocus();
            }else {
                getOtpCliked = true;
                String phoneNumber = "+97"+ num;
                sendVertificationCode(phoneNumber);
                binding.getOtp.setTextColor(Color.parseColor("#C0BEBE"));
                dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_wait);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
            }

        }
    }

    private void sendVertificationCode(String phoneNumber) {
        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                .setPhoneNumber(phoneNumber)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(myCallBack)
                .build();

        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks myCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

            dialog.dismiss();
            binding.btnLogin.setTextColor(Color.parseColor("#000000"));
            binding.otpSent.setText("OTP has been sent to your phone number");
            binding.otpSent.setVisibility(View.VISIBLE);

            super.onCodeSent(s, forceResendingToken);
            vertificationId = s;
            binding.countdownTimer.setVisibility(View.VISIBLE);
            new CountDownTimer(60000,1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    binding.countdownTimer.setText(""+millisUntilFinished/1000);
                }

                @Override
                public void onFinish() {
                    binding.resendOtp.setVisibility(View.VISIBLE);
                    binding.countdownTimer.setVisibility(View.INVISIBLE);
                }
            }.start();
        }

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                binding.txtOtp.setText(code);
                vertifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            getOtpCliked = false;
            binding.getOtp.setTextColor(Color.parseColor("#00000FF"));
            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };


}