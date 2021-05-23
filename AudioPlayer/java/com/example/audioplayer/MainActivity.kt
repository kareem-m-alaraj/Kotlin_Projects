package com.example.audioplayer

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var mediaPlayer : MediaPlayer ? = null;

        playBtn.setOnClickListener{

            if (mediaPlayer !== null){
                mediaPlayer!!.start()
                return@setOnClickListener
            }
            mediaPlayer = MediaPlayer.create(this, Uri.parse("https://server6.mp3quran.net/balilah/001.mp3"))
//            mediaPlayer = MediaPlayer.create(this, R.raw.boom)
            mediaPlayer!!.isLooping = true
            mediaPlayer!!.start();
        }

        pauseBtn.setOnClickListener{
            if (mediaPlayer == null && !mediaPlayer!!.isPlaying) return@setOnClickListener
            mediaPlayer!!.pause()
        }

        stopBtn.setOnClickListener {
            if (mediaPlayer == null) return@setOnClickListener
            mediaPlayer!!.stop()
            mediaPlayer!!.release()
            mediaPlayer = null;
        }
    }
}