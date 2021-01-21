package com.lixan.fajardo.tawkentranceexam.local.implementation

import androidx.room.Transaction
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.local.database.AppDatabase
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import io.reactivex.Single
import javax.inject.Inject

class GitUserLocalRepositoryImpl @Inject constructor(
    private val database: AppDatabase
): GitUserLocalRepository {

    @Transaction
    override fun getGitUsers(): Single<List<GitUser>> {
        return Single.create {
            val gitUsers = mutableListOf<GitUser>()
            database
                .gitUserDao()
                .getGitUsers().map { gitUserList ->
                    gitUserList.forEach { gitUser ->
                        gitUsers.add(DBGitUser.toDomain(gitUser))
                    }
                }

            it.onSuccess(gitUsers)
        }
    }

    override fun saveGitUser(gitUser: GitUser): Single<GitUser> {
        return Single.create {
            val dbGitUser = DBGitUser.fromDomain(gitUser)
            database.gitUserDao().insertOrUpdate(dbGitUser)
            it.onSuccess(gitUser)
        }
    }

    override fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>> {
        return Single.create { emitter ->
            gitUserList.forEach {
                saveGitUser(it)
                emitter.onSuccess(gitUserList)
            }
        }
    }

}