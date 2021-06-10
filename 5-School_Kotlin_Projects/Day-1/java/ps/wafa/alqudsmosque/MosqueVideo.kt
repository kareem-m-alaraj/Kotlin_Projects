package ps.wafa.alqudsmosque

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_mosque_video.*

class MosqueVideo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosque_video)

        videoView.setVideoURI(Uri.parse("android.resource://"+packageName+"/"+R.raw.file))
        val mediaController= MediaController(this)
        videoView.setMediaController(mediaController)
        videoView.start()
    }
}
