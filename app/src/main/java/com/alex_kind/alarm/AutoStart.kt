package com.alex_kind.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AutoStart : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {


        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
            val alarmIntent = Intent(context, AlarmReceiver::class.java)
            val pendingIntent: PendingIntent = PendingIntent.getBroadcast(
                context, 0, alarmIntent, 0
            )
            val manager: AlarmManager =
                context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val interval = 8000
            manager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
                interval.toLong(), pendingIntent
            )
        }
    }
}