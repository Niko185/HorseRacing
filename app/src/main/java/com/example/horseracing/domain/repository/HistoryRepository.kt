package com.example.horseracing.domain.repository

import com.example.horseracing.domain.model.Race
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun saveRace(race: Race)
    fun getAllHistory(): Flow<List<Race>>
}