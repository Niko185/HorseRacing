package com.example.horseracing.domain.repository

import com.example.horseracing.domain.model.Horse
import kotlinx.coroutines.flow.Flow

interface RaceRepository {
    fun getFinishTimeHorseOne(): Flow<Horse>
    fun getFinishTimeHorseTwo(): Flow<Horse>
    fun getFinishTimeHorseThree(): Flow<Horse>
}