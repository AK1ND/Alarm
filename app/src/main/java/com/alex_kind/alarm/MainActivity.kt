package com.alex_kind.alarm

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.alex_kind.alarm.databinding.ActivityMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var picker: MaterialTimePicker
    private lateinit var calendar: Calendar
    private lateinit var alarmManager: AlarmManager
    private lateinit var pendingIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()

        binding.selectTimeBtn.setOnClickListener {
            showTimePicker()
        }

        binding.cancelAlarmBtn.setOnClickListener {
            cancelAlarm()
        }

    }


    private fun cancelAlarm() {
        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.cancel(pendingIntent)

        Toast.makeText(this, "Alarm Cancelled", Toast.LENGTH_SHORT)
            .show()

        binding.selectedTime.text = getString(R.string.no_alarm)

    }

    private fun setAlarm() {

        alarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
        val intent = Intent(this, AlarmReceiver::class.java)

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        alarmManager.setWindow(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, 1000, pendingIntent)

        Toast.makeText(this, "Alarm set Successfully", Toast.LENGTH_SHORT)
            .show()

    }

    private fun showTimePicker() {
        picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(8)
            .setTitleText("Select Alarm Time")
            .build()

        picker.show(supportFragmentManager, "alarm id")

        picker.addOnPositiveButtonClickListener {
            binding.selectedTime.text =
                String.format("%02d", picker.hour) + ":" + String.format(
                    "%02d",
                    picker.minute
                )

            calendar = Calendar.getInstance()

            if (calendar[Calendar.HOUR_OF_DAY] > picker.hour) {
                if (calendar[Calendar.MINUTE] > picker.minute) {
                    calendar[Calendar.DAY_OF_MONTH] = calendar[Calendar.DAY_OF_MONTH]
                }
            }
            calendar[Calendar.HOUR_OF_DAY] = picker.hour
            calendar[Calendar.MINUTE] = picker.minute
            calendar[Calendar.SECOND] = 0
            calendar[Calendar.MILLISECOND] = 0

            setAlarm()

        }

    }

    private fun createNotificationChannel() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val name: CharSequence = "MyReminderChannel"
            val description = "Channel for Alarm Manager"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("alarm id", name, importance)
            channel.description = description
            val notificationManager = getSystemService(
                NotificationManager::class.java
            )

            notificationManager.createNotificationChannel(channel)

        }
    }

}
