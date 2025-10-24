package com.responsi.h1d023018.data.network

import com.responsi.h1d023018.data.model.TeamResponse
import com.responsi.h1d023018.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header


interface ApiService {

    @GET("v4/teams/563")
    suspend fun getTeamDetails(
        @Header("X-Auth-Token") token: String = Constants.API_TOKEN
    ): Response<TeamResponse>
}

