package com.example.horseracing.data.local.race.repository

import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val raceDao: RaceDao
)  : HistoryRepository {
    override suspend fun saveRace(race: Race) {
        val raceEntity = RaceEntity(
            horses = race.horses
        )
        raceDao.insertRace(raceEntity)
    }
}