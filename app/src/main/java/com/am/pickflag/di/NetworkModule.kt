package com.am.pickflag.di

import com.am.pickflag.network.FlagApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://countriesnow.space/api/v0.1/")
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build()
    }

    @Singleton
    @Provides
    fun provideEmployeeApi(retrofit: Retrofit): FlagApi {
        return retrofit.create(FlagApi::class.java)
    }


}