package com.example.horseracing.presentation.fragments.race

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.usecase.race.GetRaceUseCase
import com.example.horseracing.domain.usecase.race.SaveRaceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
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
                    _stateScreen.value = RaceState.ResultState(race)
                }
            } catch (e: Exception) {
                _stateScreen.value = RaceState.ErrorState("Ошибка загрузки: ${e.message}")
            }
        }
    }

    fun saveCurrentRace() {
        if (currentRace == null) {
            _stateScreen.value = RaceState.ErrorState("Нет данных гонки для сохранения")
            return
        }

        viewModelScope.launch {
            try {

                Log.d("SAVE_RACE", "Saving race: ${currentRace?.horses?.joinToString()}")

                currentRace?.let { race ->
                    saveRaceUseCase(race)
                    _stateScreen.value = RaceState.SavedState
                    Log.d("SAVE_RACE", "Race saved successfully")
                }
            } catch (e: Exception) {
                Log.e("SAVE_RACE", "Error saving race", e)
                _stateScreen.value = RaceState.ErrorState("Ошибка сохранения: ${e.message}")
            }
        }
    }

}