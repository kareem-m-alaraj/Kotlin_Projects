package ps.school.vocationalschool

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val position = intent.getIntExtra("id", Int.MAX_VALUE)

        shcoolname_tv.text ="اسم المدرسة: " + schools?.get(position)?.name
        shcooladdress_tv.text ="عنوان المدرسة: " +  schools?.get(position)?.address
        phone_tv.text ="رقم الهاتف: " +  schools?.get(position)?.phone.toString()
        number_tv.text ="عدد الطلاب: " +  schools?.get(position)?.number


        back_details_btn.setOnClickListener {
            val intent = Intent(this, ShowActivity::class.java)
            startActivity(intent)
        }
    }
}