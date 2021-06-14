package ps.wafa.alqudsmosque

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.item_layout.view.*

class CustomAdapter(context: Context,
                    private val names:ArrayList<String>,
                    private val images:ArrayList<Int>)
    : ArrayAdapter<String>(context, R.layout.item_layout, names) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val custom_view : View = layoutInflater.inflate(R.layout.item_layout, parent, false)

        custom_view.item_name.text = names[position!!]
        custom_view.item_image.setImageResource(images[position!!])

        return custom_view
    }
}