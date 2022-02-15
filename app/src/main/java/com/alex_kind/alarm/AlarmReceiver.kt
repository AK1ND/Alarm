package com.alex_kind.alarm

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.startActivity
import java.util.*


class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, p1: Intent?) {


        val intent = Intent(context, SoundActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        if (intent.action.equals(Intent.ACTION_BOOT_COMPLETED)){

        }


        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val builder = NotificationCompat.Builder(context!!, "alarm id")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("My Alarm Manager")
            .setContentText("Running Alarm")
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(123, builder.build())

        val resultIntent = Intent(
            context,
            SoundActivity::class.java
        )

        resultIntent.data = Uri.parse(
            "content://"
                    + Calendar.getInstance().timeInMillis
        )
        resultIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(context, resultIntent, null)

    }
}
