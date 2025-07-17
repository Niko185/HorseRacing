package com.example.horseracing.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.horseracing.data.local.entity.RaceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RaceDao {
    @Insert
    suspend fun insertRace(raceEntity: RaceEntity)

    @Query("SELECT * FROM races ORDER BY timestamp DESC")
    fun getAllHistory(): Flow<List<RaceEntity>>
}