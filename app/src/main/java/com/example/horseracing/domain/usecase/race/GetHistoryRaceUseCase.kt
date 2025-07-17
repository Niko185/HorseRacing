package com.example.horseracing.domain.usecase.race

import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHistoryRaceUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    operator fun invoke(): Flow<List<Race>> = repository.getAllHistory()
}

