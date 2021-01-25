package com.lixan.fajardo.tawkentranceexam.data.repository.implementation

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.network.response.ErrorHandler
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import com.lixan.fajardo.tawkentranceexam.network.response.ResultError
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import timber.log.Timber
import java.net.UnknownHostException
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

    override fun getUserProfileFromAPI(username: String): Single<RequestResult<GitUser>> {
        return remote.getUserProfile(username)
            .flatMap { result ->
                if (result.isSuccess) {
                    saveGitUser(result.result()).map { gitUser ->
                        RequestResult.success(gitUser)
                    }
                } else {
                    Single.just(
                        RequestResult.error(result.error())
                    )
                }
            }
            .onErrorReturn {
                RequestResult.error(
                    ErrorHandler.handleError(it)
                )
            }
    }

    override fun getUsersFromAPI(page: Int): Single<RequestResult<List<GitUser>>> {
        return remote
            .getUsers(page)
            .flatMap { result ->
                if (result.isSuccess) {
                    val gitUserList = result.result()[KEY_GIT_USER_DATA] as List<GitUser>
                    saveGitUsersInfo(gitUserList)
                        .singleOrError()
                        .map {
                            RequestResult.success(gitUserList)
                        }
                } else {
                    Single.just(
                        RequestResult.error(result.error())
                    )
                }
            }
    }

    private fun saveGitUsersInfo(gitUserList: List<GitUser>): Observable<List<GitUser>> {
        return Observable.zip(
            Observable.just(gitUserList), saveGitUserList(gitUserList)
                .toObservable(),
            BiFunction { t1, _ ->
                return@BiFunction t1
            }
        )
    }

}