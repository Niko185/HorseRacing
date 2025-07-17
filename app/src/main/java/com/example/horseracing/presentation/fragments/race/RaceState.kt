package com.example.horseracing.presentation.fragments.race

import com.example.horseracing.domain.model.Horse
import com.example.horseracing.domain.model.Race


sealed class RaceState {
    object Idle : RaceState()
    data class ErrorState(val message: String) : RaceState()
    object ProgressState : RaceState()
    data class ResultState(val race: Race) : RaceState()
    object SavedState : RaceState()
}

