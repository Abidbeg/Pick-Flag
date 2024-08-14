package com.am.pickflag.network

import com.am.pickflag.model.CountryFlagList
import retrofit2.Response
import retrofit2.http.GET


interface FlagApi {

    @GET("countries/flag/images")
    suspend fun getFlags(): Response<CountryFlagList>

}