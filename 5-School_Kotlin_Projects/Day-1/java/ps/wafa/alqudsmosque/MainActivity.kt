package ps.wafa.alqudsmosque

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSecondActivity.setOnClickListener {
            val intent = Intent(this, MosqueShow::class.java)
            startActivity(intent)
        }

        btnThirdActivity.setOnClickListener {
            val intent = Intent(this, MosqueSite::class.java)
            startActivity(intent)
        }

        btnFourthActivity.setOnClickListener {
            val intent = Intent(this, MosqueVideo::class.java)
            startActivity(intent)
        }
    }
}
