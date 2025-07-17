package com.example.horseracing.data.local.race.repository

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface RaceDao {
    @Insert
    suspend fun insertRace(raceEntity: RaceEntity)
}