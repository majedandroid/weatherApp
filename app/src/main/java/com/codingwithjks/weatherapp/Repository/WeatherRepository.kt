package com.codingwithjks.weatherapp.Repository

import com.codingwithjks.weatherapp.Model.City
import com.codingwithjks.weatherapp.Network.ApiServiceImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImp: ApiServiceImp) {

    fun getCityData(city:String):Flow<City> = flow {
        val response= apiServiceImp.getCity(city,"c1dc7fd7dcea31966ceedb116b0dfb0a")
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()
}