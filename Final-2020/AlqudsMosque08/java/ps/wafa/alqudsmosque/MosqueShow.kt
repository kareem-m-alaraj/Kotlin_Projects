package ps.wafa.alqudsmosque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_mosque_show.*

class MosqueShow : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosque_show)

        val names = arrayListOf("مسجد قبة الصخرة", "المسجد الأقصى", "المسجد القبلي")
        val images = arrayListOf(R.drawable.mosque, R.drawable.mosque, R.drawable.mosque)

        val myAdapter:CustomAdapter = CustomAdapter(this, names, images)
        gridView.adapter = myAdapter;

    }
}