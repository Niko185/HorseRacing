package com.example.horseracing.data.local.race.repository

import com.example.horseracing.domain.model.Horse
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.RaceRepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RaceRepositoryImpl() : RaceRepository {

    override fun getRace(): Flow<Race> = flow {
        val horses = listOf(
            Horse("Лошадь 1", createRandomTime()),
            Horse("Лошадь 2", createRandomTime()),
            Horse("Лошадь 3", createRandomTime())
        ).sortedBy { it.finishTime }

        emit(Race(
            horses = horses
        ))
    }.flowOn(Dispatchers.IO)



    private suspend fun createRandomTime(): Int {
        val time = (2000..10000).random()
        delay(time.toLong())
        return time
    }
}
