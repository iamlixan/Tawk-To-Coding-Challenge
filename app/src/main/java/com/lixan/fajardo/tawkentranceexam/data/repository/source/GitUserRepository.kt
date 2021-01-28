package com.lixan.fajardo.tawkentranceexam.data.repository.source

import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import com.lixan.fajardo.tawkentranceexam.network.response.RequestResult
import io.reactivex.Single
import androidx.core.util.Pair

interface GitUserRepository {

    fun getUsersFromAPI(page: Int = 0): Single<RequestResult<List<GitUser>>>

    fun getUserNotes(): Single<List<GitUserNote>>

    fun saveGitUser(gitUser: GitUser): Single<GitUser>

    fun getLocalGitUsers(): Single<List<GitUser>>

    fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>>

    fun getUserProfileFromAPI(username: String): Single<RequestResult<Pair<GitUser, GitUserNote>>>

    fun getLocalGitUserProfile(username: String): Single<Pair<GitUser, GitUserNote>>

    fun searchGitUsers(username: String): Single<List<GitUser>>

    fun saveGitUserNote(gitUserNote: GitUserNote): Single<GitUserNote>

    fun getGitUserNote(id: Int): Single<GitUserNote>
}