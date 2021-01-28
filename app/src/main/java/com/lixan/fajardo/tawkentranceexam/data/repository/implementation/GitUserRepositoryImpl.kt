package com.lixan.fajardo.tawkentranceexam.data.repository.implementation

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import com.lixan.fajardo.tawkentranceexam.data.repository.source.GitUserRepository
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.network.remoterepository.source.GitUserRemoteRepository
import com.lixan.fajardo.tawkentranceexam.network.response.ErrorHandler
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import com.lixan.fajardo.tawkentranceexam.utils.KEY_GIT_USER_DATA
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject
import androidx.core.util.Pair

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

    override fun getUserProfileFromAPI(username: String): Single<RequestResult<Pair<GitUser, GitUserNote>>> {
        return remote.getUserProfile(username)
            .flatMap { result ->
                if (result.isSuccess) {
                    saveGitUser(
                        result.result()
                    ).flatMap { gitUser ->
                        getGitUserNote (
                            gitUser.id
                        ).map { gitUserNote ->
                            val pair = Pair.create(gitUser, gitUserNote)
                            RequestResult.success(pair)
                        }
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

    override fun getLocalGitUserProfile(username: String): Single<Pair<GitUser, GitUserNote>> {
        return local.getGitUserProfile(username).flatMap { gitUser ->
            local.getGitUserNote(gitUser.id).map { gitUserNote ->
                Pair.create(gitUser, gitUserNote)
            }
        }
    }

    override fun searchGitUsers(username: String): Single<List<GitUser>> {
        return local.searchGitUser(username)
    }

    override fun saveGitUserNote(gitUserNote: GitUserNote): Single<GitUserNote> {
        return local.saveGitUserNote(gitUserNote)
    }

    override fun getGitUserNote(id: Int): Single<GitUserNote> {
        return local.getGitUserNote(id)
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
                            RequestResult.success(it)
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

    override fun getUserNotes(): Single<List<GitUserNote>> {
        return local.getGitUsersNotes()
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