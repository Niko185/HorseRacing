package com.example.horseracing.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.horseracing.data.local.converter.HorseListConverter
import com.example.horseracing.data.local.dao.RaceDao
import com.example.horseracing.data.local.entity.RaceEntity

@Database(entities = [RaceEntity::class], version = 1)
@TypeConverters(HorseListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun raceDao(): RaceDao

    companion object {
        @Volatile
        var INSTANCE: AppDatabase? = null
        fun getDataBase(context: Context): AppDatabase {
            return INSTANCE ?:

            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "HorseRacing.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}