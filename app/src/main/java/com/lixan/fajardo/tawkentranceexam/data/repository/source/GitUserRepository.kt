package com.lixan.fajardo.tawkentranceexam.data.repository.source

import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import io.reactivex.Single

interface GitUserRepository {

    fun getUsers(page: Int = 0): Single<RequestResult<Map<String, Any>>>

}