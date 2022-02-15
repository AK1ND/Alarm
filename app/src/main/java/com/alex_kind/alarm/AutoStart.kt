package com.alex_kind.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AutoStart: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        CoroutineScope(Dispatchers.Main).launch {
            var intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context?.startActivity(intent)

            //roll up app when device was rebooted
            delay(50)
            intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            context?.startActivity(intent)
        }
    }
}