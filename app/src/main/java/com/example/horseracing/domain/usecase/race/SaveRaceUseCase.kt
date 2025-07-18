package com.example.horseracing.domain.usecase.race

import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.HistoryRepository
import javax.inject.Inject

class SaveRaceUseCase @Inject constructor(
    private val repository: HistoryRepository
) {
    suspend operator fun invoke(race: Race) {
        repository.saveRace(race)
    }
}