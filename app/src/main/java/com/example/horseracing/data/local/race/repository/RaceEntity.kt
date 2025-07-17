package com.example.horseracing.data.local.race.repository

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.horseracing.domain.model.Horse

@Entity(tableName = "races")
data class RaceEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,


    val timestamp: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "horses")
    @TypeConverters(HorseListConverter::class)
    val horses: List<Horse>
)