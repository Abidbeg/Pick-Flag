package com.am.pickflag.viewmodel

import com.am.pickflag.model.Data

data class FlagUiState(
    val currentViewFlags: List<Data> = listOf(),
    val selectFlag: Boolean = false,
    val showPopup: Boolean = false,
    val score: Int = 0,
    val currentRound: Int = 1
)
