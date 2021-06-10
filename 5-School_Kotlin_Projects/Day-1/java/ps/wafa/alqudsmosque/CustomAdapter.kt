package ps.wafa.alqudsmosque

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_layout.view.*

class CustomAdapter (context: Context, val names: Array<String>, val images: Array<Int>):
    ArrayAdapter<String>(context, R.layout.activity_mosque_show, names){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val l: LayoutInflater = LayoutInflater.from(context)
        val customView: View = l.inflate(R.layout.item_layout, parent, false)

        customView.mosqueTextView.text = names[position]
        customView.mosqueImageView.setImageResource(images[position])

        return customView
    }
}