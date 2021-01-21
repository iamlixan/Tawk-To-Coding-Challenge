package com.lixan.fajardo.tawkentranceexam.data.repository.implementation

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA
import io.reactivex.Single
import javax.inject.Inject


class GitUserRepositoryImpl @Inject constructor(
    private val remote: GitUserRemoteRepository,
    private val local: GitUserLocalRepository
) : GitUserRepository {

    override fun saveGitUser(gitUser: GitUser): Single<GitUser> {
        return local.saveGitUser(gitUser)
    }

    override fun getLocalGitUsers(): Single<List<GitUser>> {
        return local.getGitUsers()
    }

    override fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>> {
        return local.saveGitUserList(gitUserList)
    }

    override fun getUsersFromAPI(page: Int): Single<List<GitUser>> {
        return remote.getUsers(page).flatMap { result ->
            val gitUserList = result.result()[KEY_GIT_USER_DATA] as List<GitUser>
            if (result.isSuccess) {
                saveGitUserList(gitUserList).map { it }
            } else {
                Single.just(gitUserList)
            }
        }
    }

}