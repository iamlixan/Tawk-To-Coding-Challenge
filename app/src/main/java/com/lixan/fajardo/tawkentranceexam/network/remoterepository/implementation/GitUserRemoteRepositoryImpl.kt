package com.lixan.fajardo.tawkentranceexam.network.remoterepository.implementation

import com.google.gson.Gson
import com.lixan.fajardo.tawkentranceexam.network.ApiService
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.network.response.ErrorHandler
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import com.lixan.fajardo.tawkentranceexam.network.response.ResultError
import com.lixan.fajardo.tawkentranceexam.network.response.dto.GitUserDTO
import io.reactivex.Single
import javax.inject.Inject

class GitUserRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson
) : GitUserRemoteRepository {

    override fun getUsers(page: Int): Single<RequestResult<Map<String, Any>>> {
        return apiService.getUsers(page).map { response ->
            if (response.gitUsersList.isNotEmpty()) {
                RequestResult.success(
                    GitUserDTO.mapGitUserResponse(response)
                )
            } else {
                RequestResult.error(
                    ResultError(response.message)
                )
            }
        }
            .onErrorReturn {
                RequestResult.error(
                    ErrorHandler.handleError(it)
                )
            }
    }

}