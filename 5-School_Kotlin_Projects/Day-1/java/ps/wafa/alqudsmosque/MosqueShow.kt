package ps.wafa.alqudsmosque

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_mosque_show.*

class MosqueShow : AppCompatActivity() {

    val names = arrayOf("جامع عمر بن الخطاب", "جامع القلعة", "مسجد الشيخ ريحان", "المسجد اليعقوبي")
    val images = arrayOf(R.drawable.one, R.drawable.two, R.drawable.three, R.drawable.four)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mosque_show)

        val myAdapter:CustomAdapter = CustomAdapter(this, names, images)
        gridView.adapter = myAdapter
    }
}
