package com.example.storedatarealtime;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.storedatarealtime.databinding.ActivityUploadBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UploadActivity extends AppCompatActivity {

    ActivityUploadBinding binding;
    Uri uri;
    String imageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {


                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    uri = data.getData();
                    binding.uploadImg.setImageURI(uri);
                } else {
                    Toast.makeText(UploadActivity.this, "No Image selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_GET_CONTENT);
                photoPicker.setType("image/*");
                launcher.launch(photoPicker);
            }
        });

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });


    }

    private void saveData() {
        FirebaseApp.initializeApp(UploadActivity.this);
        StorageReference reference = FirebaseStorage.getInstance().getReference().child("Android Images")
                .child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(UploadActivity.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_bar_layout);
        AlertDialog dialog = builder.create();
        dialog.show();


        reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri uriImage = uriTask.getResult();
                imageURL = uriImage.toString();

                dialog.dismiss();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }

    public void uploadData() {

        String title = binding.uploadTopic.getText().toString();
        String desc = binding.uploadDesc.getText().toString();
        String lang = binding.uploadLang.getText().toString();

        DataClass dataClass = new DataClass(title,desc,lang,imageURL);

        FirebaseDatabase.getInstance().getReference("Android Tutorials").child(title)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(UploadActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UploadActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


    }
}