package com.example.videoview

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.sawarekh))
//        videoView.setVideoURI(Uri.parse("https://www.cdc.gov/wcms/video/low-res/coronavirus/2021/1043010430improveMasks.mp4"))
        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        videoView.start()
        
    }
}