package com.am.pickflag.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.pickflag.data.MAX_NO_OF_ROUND
import com.am.pickflag.data.SCORE_INCREASE
import com.am.pickflag.model.CountryFlagList
import com.am.pickflag.model.Data
import com.am.pickflag.repository.CountryFlagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryFlagViewModel @Inject constructor(
    private val repository: CountryFlagRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FlagUiState())
    val uiState: StateFlow<FlagUiState> = _uiState.asStateFlow()
    var currentFlag by mutableStateOf("")
        private set

    val flagList: StateFlow<CountryFlagList>
        get() = repository.countryFlag


    private fun pickRandomFlag(): List<Data> {
        val listOfFlag: MutableList<Data> = ArrayList(3)
        for (i in 0..3) {
            listOfFlag.add(i, flagList.value.data.random())
        }
        return listOfFlag
    }


    fun checkUserGuess(userGuess: String) {
        if (currentFlag.equals(userGuess, true)) {
            val score = _uiState.value.score.plus(SCORE_INCREASE)
            _uiState.update { currentState ->
                currentState.copy(
                    selectFlag = true,
                    showPopup = true,
                    score = score
                )
            }
        } else {
            val score = _uiState.value.score.plus(0)
            _uiState.update { currentState ->
                currentState.copy(
                    selectFlag = false,
                    showPopup = true,
                    score = score
                )
            }
        }
    }

    fun updateScore(updateScore: Int) {
        if (_uiState.value.currentRound == MAX_NO_OF_ROUND) {
            _uiState.update { currentState ->
                currentState.copy(selectFlag = true, showPopup = true, score = updateScore)
            }
        } else {

        }
    }

    fun nextRound() {
        _uiState.update { currentState ->
            currentState.copy(
                showPopup = false,
                currentRound = _uiState.value.currentRound.inc(),
                currentViewFlags = pickRandomFlag()
            )
        }
        currentFlag = _uiState.value.currentViewFlags[(0..2).random()].name
    }


    fun resetGame() {
        _uiState.value = FlagUiState(currentViewFlags = pickRandomFlag())
        currentFlag = _uiState.value.currentViewFlags[(0..2).random()].name
    }

    init {
        viewModelScope.launch {
            repository.getCountryFlag()
            resetGame()
        }
    }

}