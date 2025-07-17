package com.example.horseracing.presentation.fragments.race

import com.example.horseracing.domain.model.Race

sealed class RaceState {
    object Idle : RaceState()
    object ProgressState : RaceState()
    data class ErrorState(val message: String) : RaceState()
    data class ResultState(val race: Race, val isSaved: Boolean = false) : RaceState()
}

