package edu.mssu.cis385.implicitintentsreceiver.chaoscontrol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    private Button button_notify;
    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;

    private static final int NOTIFICATION_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_notify = findViewById(R.id.notify);
        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });
        createNotificationChannel();
    }

    public void launchShoppingList(View view) {
        Log.d(LOG_TAG, "Button Clicked");
        Intent intent = new Intent(this, ShoppingList.class);
        startActivity(intent);
    }

    public void sendNotification(){
        NotificationCompat.Builder notifyBuilder = getNotificationBuilder();
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }

    public void createNotificationChannel(){
        mNotifyManager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(PRIMARY_CHANNEL_ID,
                    "Inspire Notification", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableVibration(true);
            notificationChannel.setDescription("Inspiration");
            mNotifyManager.createNotificationChannel(notificationChannel);
        }
    }
    private NotificationCompat.Builder getNotificationBuilder(){
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this,
                PRIMARY_CHANNEL_ID)
                .setContentTitle("Some Inspiration: ")
                .setContentText("If the world did not suck, we would all fall off")
                .setSmallIcon(R.drawable.ic_happy);
        return notifyBuilder;
    }
}