package com.example.notificationold

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CHANNEL_ID = "channel_id";
        val ID = 2468;
        val id = 2222;

        notificationBtn.setOnClickListener {

            if (Build.VERSION.SDK_INT >= 26) {

                val importance = NotificationManager.IMPORTANCE_HIGH;
                val myChannel = NotificationChannel(CHANNEL_ID, "Channel Name", importance)
                val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My Notification")
                        .setContentText("This is a notification1")
                        .build()

                val myNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                myNotificationManager.createNotificationChannel(myChannel)
                myNotificationManager.notify(ID, notification);

            }else{

                val notification = NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle("My Notification")
                        .setContentText("This is a notification2")
                        .build();

                val myNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                myNotificationManager.notify(id, notification);

            }
        }
    }
}