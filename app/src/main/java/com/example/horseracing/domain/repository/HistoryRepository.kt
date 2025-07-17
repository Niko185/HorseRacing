package com.example.horseracing.domain.repository

import com.example.horseracing.domain.model.Race

interface HistoryRepository {
    suspend fun saveRace(race: Race)
}