package com.alex_kind.alarm.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarms(
    @PrimaryKey val requestCode: Int,
    val hour: Int,
    val minute: Int
)
