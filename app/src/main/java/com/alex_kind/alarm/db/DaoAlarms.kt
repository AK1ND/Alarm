package com.alex_kind.alarm.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoAlarms {
    @Query("SELECT * FROM alarms ")
    suspend fun getAlarms(): List<Alarms>

    @Query("SELECT requestCode FROM alarms ")
    suspend fun getReqCodes(): List<Int>

    @Insert
    suspend fun addAlarm(newAlarms: Alarms)

    @Delete
    suspend fun delAlarm(alarm: Alarms)

    @Query("DELETE FROM alarms")
    suspend fun deleteAll()

    @Insert
    suspend fun insertAll(alarms: List<Alarms?>?)

}