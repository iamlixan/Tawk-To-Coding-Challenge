package com.lixan.fajardo.tawkentranceexam.data.repository.implementation

import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import io.reactivex.Single
import javax.inject.Inject


class GitUserRepositoryImpl @Inject constructor(
    private val remote: GitUserRemoteRepository
) : GitUserRepository {

    override fun getUsers(page: Int): Single<RequestResult<Map<String, Any>>> {
        return remote.getUsers(page)
    }

}