package com.example.horseracing.data.local.race.repository

import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.repository.HistoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
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


    override fun getAllHistory(): Flow<List<Race>> {
        return raceDao.getAllHistory().map { entities ->
            entities.mapNotNull { entity ->
                try {
                    Race(entity.horses.sortedBy { it.finishTime })
                } catch (e: Exception) {
                    println("Error mapping race entity: ${e.message}")
                    null
                }
            }
        }
    }
}



