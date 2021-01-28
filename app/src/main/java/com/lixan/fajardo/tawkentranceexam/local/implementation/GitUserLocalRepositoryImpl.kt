package com.lixan.fajardo.tawkentranceexam.local.implementation

import androidx.room.EmptyResultSetException
import androidx.room.Transaction
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.data.models.GitUserNote
import com.lixan.fajardo.tawkentranceexam.local.database.AppDatabase
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUserNote
import com.lixan.fajardo.tawkentranceexam.local.source.GitUserLocalRepository
import com.lixan.fajardo.tawkentranceexam.utils.OnErrorResumeNext
import io.reactivex.Single
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
                DBGitUser.mapListToDomain(it)
            }
    }

    override fun getGitUserProfile(username: String): Single<GitUser> {
        return database
            .gitUserDao()
            .getGitUserProfile(username)
            .compose(
                OnErrorResumeNext<DBGitUser, EmptyResultSetException> (
                    DBGitUser.empty(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                DBGitUser.toDomain(it)
            }
    }

    override fun saveGitUser(gitUser: GitUser): Single<GitUser> {
        return Single.create{
            database.gitUserDao()
                .apply {
                    insertOrUpdate(DBGitUser.fromDomain(gitUser))
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

            emitter.onSuccess(gitUserList)
        }
    }

    override fun searchGitUser(username: String): Single<List<GitUser>> {
        return database
            .gitUserDao()
            .searchGitUsers(username)
            .compose(
                OnErrorResumeNext<List<DBGitUser>, EmptyResultSetException> (
                    emptyList(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                DBGitUser.mapListToDomain(it)
            }
    }

    override fun getGitUsersNotes(): Single<List<GitUserNote>> {
        return database
            .gitUserNotesDao()
            .getGitUserNotes()
            .compose(
                OnErrorResumeNext<List<DBGitUserNote>, EmptyResultSetException> (
                    emptyList(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                DBGitUserNote.mapListToDomain(it)
            }
    }

    override fun getGitUserNote(id: Int): Single<GitUserNote> {
        return database
            .gitUserNotesDao()
            .getGitUserNote(id)
            .compose(
                OnErrorResumeNext<DBGitUserNote, EmptyResultSetException> (
                    DBGitUserNote.empty(),
                    EmptyResultSetException::class.java
                )
            )
            .map {
                DBGitUserNote.toDomain(it)
            }
    }

    override fun saveGitUserNote(note: GitUserNote): Single<GitUserNote> {
        return Single.create {
            database.gitUserNotesDao()
                .apply {
                    insertOrUpdate(DBGitUserNote.fromDomain(note))
                }

            it.onSuccess(note)
        }
    }

}