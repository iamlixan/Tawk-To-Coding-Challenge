package com.lixan.fajardo.tawkentranceexam.network.remoterepository.source

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import io.reactivex.Single

interface GitUserRemoteRepository {

    fun getUsers(page: Int = 0): Single<RequestResult<Map<String, Any>>>

    fun getUserProfile(userName: String): Single<RequestResult<GitUser>>
}