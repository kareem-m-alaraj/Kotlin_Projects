package com.example.notificationmodern

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("LocalVariableName")
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val CHANNEL_ID = "channel_id";
        val ID = 2468;

        notificationBtn.setOnClickListener {
            val importance = NotificationManager.IMPORTANCE_HIGH;
            val myChannel = NotificationChannel(CHANNEL_ID, "Channel Name", importance)
            val notification = NotificationCompat.Builder(this, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("My Notification")
                    .setContentText("This is a notification")
                    .build()

            val myNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            myNotificationManager.createNotificationChannel(myChannel)
            myNotificationManager.notify(ID, notification);
        }
    }
}