package com.example.horseracing.domain.usecase.race

import com.example.horseracing.data.local.race.repository.RaceRepositoryImpl
import com.example.horseracing.domain.repository.RaceRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRaceUseCase @Inject constructor(
    private val repository: RaceRepository
) {

    operator fun invoke() = flow {
        repository.getRace().collect {
            emit(it)
        }
    }
}