package com.am.pickflag.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.am.pickflag.model.CountryFlagList
import com.am.pickflag.repository.CountryFlagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryFlagViewModel @Inject constructor(
    private val repository: CountryFlagRepository
) : ViewModel() {

    val flagList: StateFlow<CountryFlagList>
        get() = repository.countryFlag

    init {
        viewModelScope.launch {
            repository.getCountryFlag()
        }
    }

}