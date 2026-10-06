package com.myfirebaseminiproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText, passwordEditText;
    private CheckBox rememberMeCheckBox;
    private Button loginButton;
    private TextView registerRedirectText;
//    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        rememberMeCheckBox = findViewById(R.id.rememberMeCheckBox);
        loginButton = findViewById(R.id.loginButton);
        registerRedirectText = findViewById(R.id.registerRedirectText);

        // Load saved login info
        if (sharedPreferences.getBoolean("rememberMe", false)) {
            emailEditText.setText(sharedPreferences.getString("email", ""));
            passwordEditText.setText(sharedPreferences.getString("password", ""));
            rememberMeCheckBox.setChecked(true);
        }

        loginButton.setOnClickListener(v -> loginUser());
        registerRedirectText.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void loginUser() {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

//        mAuth.signInWithEmailAndPassword(email, password)
//            .addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    if (rememberMeCheckBox.isChecked()) {
//                        sharedPreferences.edit()
//                            .putBoolean("rememberMe", true)
//                            .putString("email", email)
//                            .putString("password", password)
//                            .apply();
//                    } else {
//                        sharedPreferences.edit().clear().apply();
//                    }
//                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                    finish();
//                } else {
//                    String message = "Login failed. Please check your credentials and try again.";
//                    if (task.getException() != null) {
//                        String exMsg = task.getException().getMessage();
//                        if (exMsg != null && exMsg.toLowerCase().contains("password")) {
//                            message = "Incorrect password.";
//                        } else if (exMsg != null && exMsg.toLowerCase().contains("no user record")) {
//                            message = "No account found with this email.";
//                        }
//                    }
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//            });
    }
} 