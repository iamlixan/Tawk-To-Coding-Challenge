package com.lixan.fajardo.tawkentranceexam.local.implementation

import androidx.room.EmptyResultSetException
import androidx.room.Transaction
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.local.database.AppDatabase
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.utils.OnErrorResumeNext
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject

class GitUserLocalRepositoryImpl @Inject constructor(
    private val database: AppDatabase
): GitUserLocalRepository {

    @Transaction
    override fun getGitUsers(): Single<List<GitUser>> {
        return database
            .gitUserDao()
            .getGitUsers()
            .compose(
                OnErrorResumeNext<List<DBGitUser>, EmptyResultSetException> (
                    emptyList(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                Timber.d("HERE SIZE LOCAL: ${it.size}")
                DBGitUser.mapListToDomain(it)
            }
    }

    override fun saveGitUser(gitUser: GitUser): Single<GitUser> {
        return Single.create{
            database.gitUserDao()
                .apply {
                    insert(DBGitUser.fromDomain(gitUser))
                }

            it.onSuccess(gitUser)
        }
    }

    override fun saveGitUserList(gitUserList: List<GitUser>): Single<List<GitUser>> {
        return Single.create { emitter ->
            database.gitUserDao()
                .apply {
                    insert(DBGitUser.mapListFromDomain(gitUserList).toMutableList())
                }
        }
    }

}