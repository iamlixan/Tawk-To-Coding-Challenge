package com.lixan.fajardo.tawkentranceexam.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lixan.fajardo.tawkentranceexam.data.models.GitUser
import com.lixan.fajardo.tawkentranceexam.local.base.BaseDao
import com.lixan.fajardo.tawkentranceexam.local.models.DBGitUser
import io.reactivex.Single

@Dao
abstract class GitUserDao : BaseDao<DBGitUser> {

    @Query("SELECT * FROM ${DBGitUser.DB_GIT_USER_TABLE_NAME}")
    abstract fun getGitUsers(): Single<List<DBGitUser>>

    @Query("SELECT * FROM ${DBGitUser.DB_GIT_USER_TABLE_NAME} WHERE login = :username LIMIT 1")
    abstract fun getGitUserProfile(username: String): Single<DBGitUser>

    @Query("SELECT * FROM ${DBGitUser.DB_GIT_USER_TABLE_NAME} WHERE login LIKE '%' || :username || '%'")
    abstract fun searchGitUsers(username: String): Single<List<DBGitUser>>

}