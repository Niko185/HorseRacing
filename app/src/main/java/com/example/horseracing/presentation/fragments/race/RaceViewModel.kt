package com.example.horseracing.presentation.fragments.race

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseracing.domain.usecase.GetResultRaceInteractor
import com.example.horseracing.domain.usecase.GetTimeRaceHorseOneUseCase
import com.example.horseracing.domain.usecase.GetTimeRaceHorseThreeUseCase
import com.example.horseracing.domain.usecase.GetTimeRaceHorseTwoUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class RaceViewModel(
    // private val interactor = GetResultRaceInteractor()
) : ViewModel() {
    private val interactor = GetResultRaceInteractor()

    private val _stateScreen = MutableStateFlow<RaceState>(RaceState.Idle)
    val stateScreen: StateFlow<RaceState> = _stateScreen.asStateFlow()

    fun startRace() {
        _stateScreen.value = RaceState.ProgressState
        viewModelScope.launch {
            try {
                val results = interactor()
                _stateScreen.value = RaceState.ResultState(results)
            } catch (e: Exception) {
                _stateScreen.value = RaceState.ErrorState
            }
        }
    }




}