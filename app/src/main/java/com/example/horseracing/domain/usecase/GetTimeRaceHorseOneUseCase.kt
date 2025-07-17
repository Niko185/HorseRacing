package com.example.horseracing.domain.usecase

import com.example.horseracing.data.local.race.repository.RaceRepositoryImpl
import com.example.horseracing.domain.repository.RaceRepository
import kotlinx.coroutines.flow.flow

class GetTimeRaceHorseOneUseCase(

) {
    private val repository: RaceRepository = RaceRepositoryImpl()

    operator fun invoke() = flow {
        repository.getFinishTimeHorseOne().collect {
            emit(it)
        }
    }
}