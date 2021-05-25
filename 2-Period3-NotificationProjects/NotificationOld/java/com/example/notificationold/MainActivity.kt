package com.example.notificationold

import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id = 2222;

        notificationBtn.setOnClickListener {
            val notification = NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("My Notification")
                    .setContentText("This is a notification")
                    .build();

            val myNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            myNotificationManager.notify(id, notification);
        }
    }
}