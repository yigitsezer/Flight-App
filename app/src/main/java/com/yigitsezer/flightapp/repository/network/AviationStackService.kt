package com.yigitsezer.flightapp.repository.network

import com.yigitsezer.flightapp.repository.model.RealtimeFlights
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface AviationStackService {
    @GET("flights")
    fun getRealtimeFlights(@Query("access_key") apiKey: String): Call<RealtimeFlights>
}