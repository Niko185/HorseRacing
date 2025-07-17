package com.example.horseracing.data.local.repository

import com.example.horseracing.domain.model.Horse
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.RaceRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

const val HORSE_ONE =  "Лошадь 1"
const val HORSE_TWO =  "Лошадь 2"
const val HORSE_THREE =  "Лошадь 3"

class RaceRepositoryImpl() : RaceRepository {
    override fun getRace(): Flow<Race> = flow {
        val horses = listOf(
            Horse(HORSE_ONE, createRandomTime()),
            Horse(HORSE_TWO, createRandomTime()),
            Horse(HORSE_THREE, createRandomTime())
        ).sortedBy { it.finishTime }

        emit(Race(
            dateRace = System.currentTimeMillis(),
            horses = horses
        ))
    }.flowOn(Dispatchers.IO)

    private suspend fun createRandomTime(): Int {
        val time = (1000..3000).random()
        delay(time.toLong())
        return time
    }
}
