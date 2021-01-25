package com.lixan.fajardo.tawkentranceexam.network

import com.lixan.fajardo.tawkentranceexam.network.response.dto.GitUserDTO
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("users")
    fun getUsers(
        @Query("since")  page: Int = 0
    ): Single<List<GitUserDTO>>

    @GET("users/{userName}")
    fun getUserProfile(
        @Path("userName") userName: String
    ): Single<GitUserDTO>
}