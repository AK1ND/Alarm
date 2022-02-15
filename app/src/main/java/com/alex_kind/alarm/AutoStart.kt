package com.alex_kind.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AutoStart : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val intent1 = Intent(context, MainActivity::class.java)
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context!!.startActivity(intent1)


//        if (intent?.action.equals("android.intent.action.BOOT_COMPLETED")) {
//            val alarmIntent = Intent(context, AlarmReceiver::class.java)
//            val pendingIntent: PendingIntent = PendingIntent.getBroadcast(
//                context, 0, alarmIntent, 0
//            )
//            val manager: AlarmManager =
//                context?.getSystemService(Context.ALARM_SERVICE) as AlarmManager
//            val interval = 8000
//            manager.setInexactRepeating(
//                AlarmManager.RTC_WAKEUP, System.currentTimeMillis(),
//                interval.toLong(), pendingIntent
//            )
//        }
    }
}