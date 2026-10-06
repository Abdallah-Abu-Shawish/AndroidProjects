package com.example.homework;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.homework.databinding.ActivityMainBinding;

import com.example.homework.databinding.DialogDesignBinding;

import java.util.ArrayList;

public class EmailListActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    AlertDialog.Builder builder;
    AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayList<Email> emails=new ArrayList<>();
        Email email1 = new Email("Sami","Ucas email", "the exam is not going to happened","10:50");
        Email email2 = new Email("Ali","AUG email", "the exam is not going to happened","10:30");
        Email email3 = new Email("Khalid","IUG email", "the exam is not going to happened","8:12");
        Email email4 = new Email("Osama","AL_Aqsa school email", "the qize is not going to happened","4:30");
        emails.add(email1);
        emails.add(email2);
        emails.add(email3);
        emails.add(email4);
        builder =new AlertDialog.Builder(this);
            DialogDesignBinding bindingForDialog = DialogDesignBinding.inflate(getLayoutInflater());
            builder.setView(bindingForDialog.getRoot());
        builder.setPositiveButton("Add an email", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Email email;
                if (bindingForDialog.edName.getText().toString().isEmpty()||bindingForDialog.edText.getText().toString().isEmpty()
                        ||bindingForDialog.edTitle.getText().toString().isEmpty()||bindingForDialog.edTime.getText().toString().isEmpty()){

                    Toast.makeText(EmailListActivity.this, "You should fill all the email data", Toast.LENGTH_SHORT).show();
                }else {
                    email=new Email(bindingForDialog.edName.getText().toString(),bindingForDialog.edTitle.getText().toString()
                            ,bindingForDialog.edText.getText().toString(),bindingForDialog.edTime.getText().toString());
                    bindingForDialog.edTitle.getText().clear();
                    bindingForDialog.edText.getText().clear();
                    bindingForDialog.edName.getText().clear();
                    bindingForDialog.edTime.getText().clear();
                    emails.add(email);

                }
            }
             });

            builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        dialog = builder.create();
        CoustemDialogAdapter coustemDialogAdapter = new CoustemDialogAdapter(this,emails);
        binding.list.setAdapter(coustemDialogAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflator = getMenuInflater();
        inflator.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_email:
        dialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}