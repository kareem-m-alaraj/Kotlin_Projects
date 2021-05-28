package com.example.sqlblog

import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService
import kotlinx.android.synthetic.main.blog.view.*
import kotlinx.android.synthetic.main.delete_dialog.view.*
import kotlinx.android.synthetic.main.update_dialog.view.*


@Suppress("LocalVariableName")
class CustomAdapter(context: Context, private val blog: ArrayList<BlogTemplate>)
    : ArrayAdapter<BlogTemplate>(context, R.layout.blog, blog){
    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater:LayoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.blog, parent, false)

        view.blogID.text = blog[position].id.toString();
        view.blogTitle.text = blog[position].title;
        view.blogDescription.text = blog[position].description;

        view.updateBlogButton.setOnClickListener {
            val layoutInflater = LayoutInflater.from(context)
            val view = layoutInflater.inflate(R.layout.update_dialog, null);

            val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setView(view)

            val dialog = dialogBuilder.create()
            dialog.show()

            view.updateIDInput.setText(blog[position].id.toString())
            view.updateTitleInput.setText(blog[position].title)
            view.updateDescriptionInput.setText(blog[position].description)

            view.updateOkayButton.setOnClickListener {
                if (view.updateTitleInput.text.toString() == "" || view.updateDescriptionInput.text.toString() == "")
                    return@setOnClickListener

                var myDatabase = MyDatabase(context);
                myDatabase.updateBlog(
                        BlogTemplate(blog[position].id,
                                view.updateTitleInput.text.toString(),
                                view.updateDescriptionInput.text.toString()))
                updateBlog(context as MainActivity)

                dialog.cancel()
            }

            view.updateCancelButton.setOnClickListener {
                dialog.cancel()
            }
        }

        view.deleteBlogButton.setOnClickListener {
            val layoutInflater = LayoutInflater.from(context);
            val view = layoutInflater.inflate(R.layout.delete_dialog, null);

            val dialogBuilder = AlertDialog.Builder(context);
            dialogBuilder.setView(view);

            val dialog = dialogBuilder.create();
            dialog.show();

            view.deleteOkayButton.setOnClickListener {
                var myDatabase = MyDatabase(context);
                myDatabase.deleteBlog(blog[position].id);
                deleteBlog(context as MainActivity);

                val CHANNEL_ID = "channel_id";
                val ID = 2468;

                val importance = NotificationManager.IMPORTANCE_HIGH;
                val myChannel = NotificationChannel(CHANNEL_ID, "Channel Name", importance)
                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Delete Blog")
                    .setContentText("The Blog is Deleted")
                    .build()

                val myNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                myNotificationManager.createNotificationChannel(myChannel)
                myNotificationManager.notify(ID, notification);

                dialog.cancel();
            }

            view.deleteCancelButton.setOnClickListener {
                dialog.cancel();
            }
        }
        return view;
    }
}