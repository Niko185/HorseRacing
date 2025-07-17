package com.example.horseracing.domain.repository

import com.example.horseracing.domain.model.Race
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun getRace(): Flow<Race>
}