package com.example.horseracing.data.local.repository

import com.example.horseracing.data.local.dao.RaceDao
import com.example.horseracing.data.local.entity.RaceEntity
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val raceDao: RaceDao
)  : HistoryRepository {
    override suspend fun saveRace(race: Race) {
        val raceEntity = RaceEntity(horses = race.horses)
        raceDao.insertRace(raceEntity)
    }

    override fun getAllHistory(): Flow<List<Race>> {
        return raceDao.getAllHistory().map { entities ->
            entities.mapNotNull { entity ->
                try {
                    Race(entity.timestamp, entity.horses.sortedBy { it.finishTime })
                } catch (e: Exception) {
                    null
                }
            }
        }
    }
}



