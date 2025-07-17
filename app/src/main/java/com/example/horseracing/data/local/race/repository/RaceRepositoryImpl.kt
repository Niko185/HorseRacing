package com.example.horseracing.data.local.race.repository

import com.example.horseracing.domain.model.Horse
import com.example.horseracing.domain.repository.RaceRepository
import com.example.horseracing.presentation.toRaceTimeString
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RaceRepositoryImpl : RaceRepository {
    override fun getFinishTimeHorseOne() = flow {
        emit(Horse(
            sportNumber = "Лошадь 1",
            finishTime = createRandomTime(),
        ))
    }.flowOn(Dispatchers.IO)

    override fun getFinishTimeHorseTwo() = flow {
        emit(Horse(
            sportNumber = "Лошадь 2",
            finishTime = createRandomTime(),
        ))
    }.flowOn(Dispatchers.IO)

    override fun getFinishTimeHorseThree() = flow {
        emit(Horse(
            sportNumber = "Лошадь 3",
            finishTime = createRandomTime(),
        ))
    }.flowOn(Dispatchers.IO)

    private suspend fun createRandomTime(): Int {
        val time = (2000..10000).random()
        delay(time.toLong())
        return time
    }
}
