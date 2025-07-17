package com.example.horseracing.domain.usecase

import com.example.horseracing.data.local.race.repository.RaceRepositoryImpl
import com.example.horseracing.domain.repository.RaceRepository
import kotlinx.coroutines.flow.flow

class GetTimeRaceHorseTwoUseCase(
    /*private val repository: RaceRepository*/
) {
    private val repository: RaceRepository = RaceRepositoryImpl()
    operator fun invoke() = flow {
        repository.getFinishTimeHorseTwo().collect {
            emit(it)
        }
    }
}