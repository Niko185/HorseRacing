package com.example.horseracing.domain.usecase

import com.example.horseracing.domain.model.HorseResult

import kotlinx.coroutines.flow.first

class GetResultRaceInteractor(
   /* private val getHorseOne: GetTimeRaceHorseOneUseCase,
    private val getHorseTwo: GetTimeRaceHorseTwoUseCase,
    private val getHorseThree: GetTimeRaceHorseThreeUseCase*/
) {
    private val getHorseOne: GetTimeRaceHorseOneUseCase = GetTimeRaceHorseOneUseCase()
    private val getHorseTwo: GetTimeRaceHorseTwoUseCase = GetTimeRaceHorseTwoUseCase()
    private val getHorseThree: GetTimeRaceHorseThreeUseCase = GetTimeRaceHorseThreeUseCase()

    suspend operator fun invoke(): List<HorseResult> {
        val results = listOf(
            getHorseOne().first(),
            getHorseTwo().first(),
            getHorseThree().first()
        )

        return results
            .sortedBy { it.finishTime }
            .mapIndexed { index, horse -> HorseResult(horse, index + 1) }
    }
}