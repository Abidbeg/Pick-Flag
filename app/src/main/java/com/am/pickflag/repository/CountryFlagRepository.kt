package com.am.pickflag.repository

import com.am.pickflag.model.CountryFlagList
import com.am.pickflag.network.FlagApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class CountryFlagRepository @Inject constructor(private val flagApi: FlagApi) {

    private val _countryFlag =
        MutableStateFlow<CountryFlagList>(CountryFlagList(emptyList(), false, "data"))
    val countryFlag: StateFlow<CountryFlagList>
        get() = _countryFlag

    suspend fun getCountryFlag() {
        val response = flagApi.getFlags()
        if (response.isSuccessful && response.body() != null) {
            _countryFlag.emit(response.body()!!)
        }
    }


}