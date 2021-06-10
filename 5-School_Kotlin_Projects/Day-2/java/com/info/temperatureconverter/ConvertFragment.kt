package com.info.temperatureconverter


import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.custom_dialog.*
import kotlinx.android.synthetic.main.custom_dialog.view.*
import kotlinx.android.synthetic.main.fragment_convert.view.*

/**
 * A simple [Fragment] subclass.
 */
class ConvertFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_convert, container, false)

        view.button.setOnClickListener {

            // Inflating the dialog
            val layoutInflater = LayoutInflater.from(context);
            val dialogView = layoutInflater.inflate(R.layout.custom_dialog, null);

            // Building the dialog
            val dialogBuilder = AlertDialog.Builder(context);
            dialogBuilder.setView(dialogView);

            // Displaying the dialog
            val dialog = dialogBuilder.create();
            dialog.show();

            // Convert Button
            dialogView.convertBtn.setOnClickListener {
                val temp = dialogView.tempEditText.text.toString().toDouble()
                var convertResult = 0.0

                // Equation
                convertResult = (temp * 9 / 5) + 32

                // View Notification
                if (convertResult == 32.0){
                    viewNotification("معلومة", "هذه درجة حرارة تجمد الماء في نظام الفهرنهايت", R.drawable.ic_launcher_background)
                }
                // View Result
                dialogView.resultTextView.text = convertResult.toString()
            }
            // Cancel Button
            dialogView.cancelBtn.setOnClickListener {
                dialog.dismiss()
            }
        }
        return view
    }

    fun viewNotification(title:String, text:String, icon:Int){

        val CHANNEL_ID = "channel_id";
        val importance = NotificationManager.IMPORTANCE_HIGH;
        val myNotificationManager = getActivity()?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val myChannel = NotificationChannel(CHANNEL_ID, "Channel Name", importance);
            myNotificationManager.createNotificationChannel(myChannel);
        }
        val notification = NotificationCompat.Builder(getActivity()!!.applicationContext, CHANNEL_ID)
            .setSmallIcon(icon)
            .setContentTitle(title)
            .setContentText(text)
            .build();
        myNotificationManager.notify(2468, notification);
    }
}
