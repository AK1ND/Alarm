package com.alex_kind.alarm.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Alarms::class], version = 1)
abstract class DatabaseAlarms : RoomDatabase() {
    abstract fun alarmsDao(): DaoAlarms
}
