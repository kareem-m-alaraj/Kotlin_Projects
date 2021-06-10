package ps.school.vocationalschool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_show.*

var schools: ArrayList<SchoolModel>? = null
var schoolHelper: DatabaseHandler? = null
var schoolAdapter: SchoolAdapter? = null

class ShowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show)

        schoolHelper = DatabaseHandler(this)
        schools = schoolHelper!!.viewSchool()
        schoolAdapter = SchoolAdapter(this, schools!!)
        listview.adapter = schoolAdapter

    }
}