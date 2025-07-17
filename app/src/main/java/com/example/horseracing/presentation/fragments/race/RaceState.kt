package com.example.horseracing.presentation.fragments.race

import com.example.horseracing.domain.model.Horse
import com.example.horseracing.domain.model.HorseResult


sealed class RaceState {
    object Idle : RaceState()
    object ErrorState : RaceState()
    object ProgressState : RaceState()
    data class ResultState(
        val horses: List<HorseResult>
    ) : RaceState()
}

