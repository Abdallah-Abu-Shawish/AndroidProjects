package com.myfirebaseminiproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText nameEditText, emailEditText, passwordEditText;
    private Spinner countrySpinner;
    private ImageView profileImageView;
    private Button selectImageButton, registerButton;
    private TextView loginRedirectText;
    private Uri imageUri;
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;
//    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        mAuth = FirebaseAuth.getInstance();
//        db = FirebaseFirestore.getInstance();
//        storage = FirebaseStorage.getInstance();

        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        countrySpinner = findViewById(R.id.countrySpinner);
        profileImageView = findViewById(R.id.profileImageView);
        selectImageButton = findViewById(R.id.selectImageButton);
        registerButton = findViewById(R.id.registerButton);
        loginRedirectText = findViewById(R.id.loginRedirectText);

        selectImageButton.setOnClickListener(v -> selectImage());
        registerButton.setOnClickListener(v -> registerUser());
        loginRedirectText.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            profileImageView.setImageURI(imageUri);
        }
    }

    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void registerUser() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        String country = countrySpinner.getSelectedItem().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty() || !isValidEmail(email)) {
            Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
            return;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        if (country.equals("Select Country") || country.isEmpty()) {
            Toast.makeText(this, "Please select your country", Toast.LENGTH_SHORT).show();
            return;
        }
        if (imageUri == null) {
            Toast.makeText(this, "Please select a profile image", Toast.LENGTH_SHORT).show();
            return;
        }

//        mAuth.createUserWithEmailAndPassword(email, password)
//            .addOnCompleteListener(task -> {
//                if (task.isSuccessful()) {
//                    String uid = mAuth.getCurrentUser().getUid();
//                    StorageReference ref = storage.getReference().child("profile_images/" + uid + ".jpg");
//                    ref.putFile(imageUri).addOnSuccessListener(taskSnapshot -> {
//                        ref.getDownloadUrl().addOnSuccessListener(uri -> {
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("uid", uid);
//                            user.put("name", name);
//                            user.put("email", email);
//                            user.put("country", country);
//                            user.put("imageUrl", uri.toString());
//                            db.collection("users").document(uid).set(user)
//                                .addOnSuccessListener(aVoid -> {
//                                    Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
//                                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
//                                    finish();
//                                });
//                        });
//                    });
//                } else {
//                    String message = "Registration failed. Please check your information and try again.";
//                    if (task.getException() != null) {
//                        String exMsg = task.getException().getMessage();
//                        if (exMsg != null && exMsg.toLowerCase().contains("email address is already in use")) {
//                            message = "This email is already registered.";
//                        }
//                    }
//                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//                }
//            });
    }
} 