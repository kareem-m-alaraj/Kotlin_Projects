package com.example.unit2project

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.fragment_blank2.view.*

class BlankFragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_blank2, container, false)
        view.circle_Area_Btn.setOnClickListener {
            val n1 = view.num1.text.toString().toDouble()
            val area = 3.14 * n1 * n1

//            view.circle_Area_View.text = area.toString()

            val CHANNEL_ID = "channel_01"
            val id = 123456
            val importance = NotificationManager.IMPORTANCE_HIGH
            val mChannel = NotificationChannel(CHANNEL_ID, "اسم القناة", importance)
            val notification = NotificationCompat.Builder(activity!!, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle("Circle Area when radius : ${n1.toString()}")
                .setContentText("= ${area.toString()}")
                .build()
            val mNotificationManager= activity!!.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mNotificationManager.createNotificationChannel(mChannel)
            mNotificationManager.notify(1, notification)
        }
        return view
    }
}