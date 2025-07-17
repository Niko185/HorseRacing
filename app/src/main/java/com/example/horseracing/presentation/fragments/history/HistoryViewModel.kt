package com.example.horseracing.presentation.fragments.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseracing.domain.model.Race
import com.example.horseracing.domain.usecase.race.GetHistoryRaceUseCase
import com.example.horseracing.presentation.fragments.race.RaceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val getHistoryRaceUseCase: GetHistoryRaceUseCase
) : ViewModel() {

    private val _races = MutableStateFlow<List<Race>>(emptyList())
    val races: StateFlow<List<Race>> = _races.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {
        viewModelScope.launch {
                getHistoryRaceUseCase()
                    .collect { races ->
                        _races.value = races
                }
        }
    }

}