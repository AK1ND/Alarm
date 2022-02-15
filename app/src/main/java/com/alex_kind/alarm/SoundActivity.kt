package com.alex_kind.alarm

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.alex_kind.alarm.databinding.ActivitySoundBinding

class SoundActivity : AppCompatActivity() {


    private lateinit var binding: ActivitySoundBinding
    private lateinit var ringtoneManager: RingtoneManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySoundBinding.inflate(layoutInflater)
        setContentView(binding.root)

        screenUnlock()


        ringtoneManager = RingtoneManager(this)

        val ringtone = ringtoneManager.getRingtone(0)

        //for emulator without Ringtones
        val player = MediaPlayer.create(this, R.raw.best_alarm)
        player.isLooping = false

        try {
            ringtone.play()
        }catch (e:Exception){
            player.start()
        }

        val vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val canVibrate: Boolean = vibrator.hasVibrator()
        val milliseconds = 1000L

        if (canVibrate) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // API 26
                vibrator.vibrate(
                    VibrationEffect.createOneShot(
                        milliseconds,
                        VibrationEffect.DEFAULT_AMPLITUDE
                    )
                )
            } else {
                // This method was deprecated in API level 26
                vibrator.vibrate(milliseconds)
            }
        }

        binding.stopAlarmBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            player.stop()
            ringtone?.stop()
            startActivity(intent)
            onBackPressed()
        }

    }


    private fun screenUnlock() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            this.window.addFlags(
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD or
                    WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                    WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }
    }


}
