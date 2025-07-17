package com.example.horseracing.presentation.fragments.race

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.usecase.race.GetRaceUseCase
import com.example.horseracing.domain.usecase.race.SaveRaceUseCase
import com.example.horseracing.presentation.utils.RaceErrors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RaceViewModel @Inject constructor(
    private val saveRaceUseCase: SaveRaceUseCase,
    private val getRaceUseCase: GetRaceUseCase
) : ViewModel() {

    private val _stateScreen = MutableStateFlow<RaceState>(RaceState.Idle)
    val stateScreen: StateFlow<RaceState> = _stateScreen.asStateFlow()

    private var currentRace: Race? = null


    fun startRace() {
        _stateScreen.value = RaceState.ProgressState
        viewModelScope.launch {
            try {
                getRaceUseCase().collect { race ->
                    currentRace = race
                    _stateScreen.value = RaceState.ResultState(race, false)
                }
            } catch (e: Exception) {
                _stateScreen.value = RaceState.ErrorState(RaceErrors.LOAD_ERROR)
            }
        }
    }

    fun saveCurrentRace() {
        val raceToSave = currentRace
        if (raceToSave == null) {
            _stateScreen.value = RaceState.ErrorState(RaceErrors.NO_DATA_ERROR)
            return
        }
        viewModelScope.launch {
            try {
                saveRaceUseCase(raceToSave)
                _stateScreen.value = RaceState.ResultState(raceToSave, true)
            } catch (e: Exception) {
                _stateScreen.value = RaceState.ErrorState(RaceErrors.SAVE_ERROR)
            }
        }
    }
}