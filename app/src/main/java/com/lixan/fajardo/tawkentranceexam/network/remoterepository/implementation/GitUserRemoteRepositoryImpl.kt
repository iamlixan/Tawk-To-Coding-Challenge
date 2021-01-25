package com.lixan.fajardo.tawkentranceexam.network.remoterepository.implementation

import com.google.gson.Gson
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.network.ApiService
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.network.response.*
import com.lixan.fajardo.tawkentranceexam.network.response.dto.GitUserDTO
import io.reactivex.Single
import javax.inject.Inject

class GitUserRemoteRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val gson: Gson
) : GitUserRemoteRepository {

    override fun getUsers(page: Int): Single<RequestResult<Map<String, Any>>> {

        return apiService.getUsers(page).map {
            if (it.isNotEmpty()) {
                RequestResult.success(
                    GitUserDTO.mapGitUserResponse(GitUsersResponseData(it))
                )
            } else {
                RequestResult.error(
                    ResultError(GitUsersResponseData(it).message.orEmpty())
                )
            }
        }
            .onErrorReturn {
                RequestResult.error(
                    ErrorHandler.handleError(it)
                )
            }
    }

    override fun getUserProfile(userName: String): Single<RequestResult<GitUser>> {

        return apiService.getUserProfile(userName).map {
                RequestResult.success(
                    GitUserDTO.mapGitUser(it)
                )
            }
            .onErrorReturn {
                RequestResult.error(
                    ErrorHandler.handleError(it)
                )
            }
        }
}
