package com.example.mynotification;

import static android.app.PendingIntent.FLAG_MUTABLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.mynotification.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    public static final String CHANNEL_ID = "channel_id";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNotification();
            }
        });




    }

    private void displayNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"My channle display",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My channle description");
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Intent intent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_none_24)
                .setContentTitle("My Notification")
                .setContentText("Welcome Here!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Hello to the App #"))
                .addAction(R.drawable.ic_baseline_notifications_none_24,"Replay",pendingIntent);

        NotificationManagerCompat compat = NotificationManagerCompat.from(this);
        compat.notify(10,builder.build());

    }
}